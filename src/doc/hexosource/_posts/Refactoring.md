---
title: Refactoring
date: 2019-11-28 11:06:59
tags:
---

## 使用模板模式来重构代码

```java
public class GiftOrderListener extends AbstractOrderMessageListener {
  @Autowired
  private RedisTemplate<String, String> redisTemplate;

  @Autowired
  private IGiftWealthService giftWealthService;

  @Autowired
  private IChatService chatService;

  @Autowired
  private IRemoteSyncRemoteUserInfoQueryService userInfoQueryService;

  @Autowired
  private IRemoteSyncNobleService nobleService;

  @Autowired
  private LiveRoomInfoStrategyService liveRoomInfoStrategyService;

  @Autowired
  @Qualifier("rabbitTemplate")
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private MedalService medalService;

  @Value("${floatTemplateId}")
  private Integer floatTemplateId;

  @Value("${floatLevel}")
  private Integer floatLevel;

  @Value("${highLevel}")
  private Integer highLevel;

  @Value("${topLevel}")
  private Integer topLevel;

  @Override
  public void handleMessage(GiftOrderMessageNew orderMessage) throws Exception {
    LOG.info("handle wealth contribution message {}", orderMessage);
    GiftOrderContext orderContext = JSON.parseObject(orderMessage.getContext(), GiftOrderContext.class);

    // 如果是背包物品的话，不增加财富等级，直接返回
    if (orderContext.getPackageGift() != null && orderContext.getPackageGift()) {
      LOG.info("package gift:{}", orderMessage);
      return;
    }

    String wealthKey = KeyGen.buildWealthKey(orderMessage.getBuyerId());
    BigDecimal giftUnitPrice = orderMessage.getDraftOrderItems().get(0).getUnitPrice();
    Long quantity = orderMessage.getDraftOrderItems().get(0).getQuantity();

    double contribution = giftUnitPrice.multiply(BigDecimal.valueOf(quantity)).multiply(Constants.CONTRIBUTION_RATE)
                                       .doubleValue();
    // 如果是开宝箱的话，不能直接取价格了。
    if (orderContext.getBoxPrice() != null && orderContext.getBoxPrice() > 0) {
      contribution = orderContext.getBoxPrice();
    }

    try {
      CheckInfo checkInfo = nobleService.checkPermisson(orderMessage.getBuyerId(),
          BenefitTypeEnum.GROWTH_FAST.getType());
      if (checkInfo.getCheckRes() != null && checkInfo.getCheckRes().equals(CheckResEnum.HAVE_OPEN.getType())) {
        contribution = contribution * (checkInfo.getTemplateId().doubleValue() / 100);
        LOG.info("noble check grow fast. checkInfo:{}, actualContribution:{}", checkInfo, contribution);
      }
    } catch (Exception e) {
      LOG.error("fail to checkPermission from noble.", e);
    }

    // codis 增加贡献值
    Long actualLongContribution = new Double(contribution).longValue();
    LOG.info("actual long contribution:{}", actualLongContribution);
    Long currentContribution = redisTemplate.opsForValue().increment(wealthKey, actualLongContribution);
    LOG.info("incre contribution:{} current contribution:{} uid:{}", contribution, currentContribution,
        orderMessage.getBuyerId());
    int grade = 0;
    try {
      Map<Long, BasicUserInfo> idToUserInfo = Collections.emptyMap();

      int businessType = orderMessage.getBusinessTypeId().intValue();
      grade = giftWealthService.checkIfUpGrade(actualLongContribution, currentContribution);

      // 如果有等级提升的话，就发送等级通知消息
      if (grade > 0) {
        // 解析出uid
        LiveRoomInfo liveRoomInfo = liveRoomInfoStrategyService.getLiveRoomInfo(businessType, orderContext);
        if (liveRoomInfo.getInRoom()) {
          // 如果在直播间内的话，就发送广播消息和用户更新消息
          idToUserInfo = userInfoQueryService.multiGetBasicUserInfo(
              Arrays.asList(liveRoomInfo.getUid(), orderMessage.getBuyerId()));
          UpgradeNoticeType upgradeNoticeType = liveRoomInfoStrategyService.getUpgradeNoticeTypeByBusinessType(
              businessType);
          try {
            if (upgradeNoticeType.equals(UpgradeNoticeType.ANCHOR_NOTICE)) {
              // 发送主播口吻的广播消息
              chatService.sendWealthLevelUpdateMessage(Converter
                  .assembleWealthLevelParam(liveRoomInfo, orderMessage.getBuyerId(), grade, idToUserInfo, highLevel));
            } else if (upgradeNoticeType.equals(UpgradeNoticeType.SYSTEM_NOTICE)) {
              // 发送系统广播消息
              chatService.sendWealthLevelUpdateMessageBySystemNotice(Converter
                  .assembleWealthLevelParamOfSystemNotice(liveRoomInfo, grade,
                      idToUserInfo.get(orderMessage.getBuyerId()), highLevel));
            } else {
              LOG.error("unknown upgrade notice type:{}", upgradeNoticeType);
            }
          } catch (Exception e) {
            LOG.error("fail to send wealth level update message.", e);
          }

          try {
            // 发送用户信息更新的消息
            chatService.sendUserInfoUpdateMessage(
                Converter.assembleUserInfoParam(liveRoomInfo, orderMessage.getBuyerId()));
          } catch (Exception e) {
            LOG.error("fail to send userInfo update message.", e);
          }
        } else {
          // 不在直播间，不发送这些消息
        }

        // 发送飘屏
        if (grade >= floatLevel) {
          // 1.发送飘屏
          try {
            BasicUserInfo userInfo = idToUserInfo.get(orderMessage.getBuyerId());
            if (userInfo == null) {
              userInfo = userInfoQueryService.getBasicUserInfo(orderMessage.getBuyerId());
            }
            if (userInfo != null) {
              FloatParam floatParam = new FloatParam();
              floatParam.setSrc(userInfo.getNickname());
              Integer distLevel = grade;
              if (grade >= highLevel) {
                distLevel = grade - highLevel + 1;
                floatParam.setDst("神豪V" + distLevel);
              } else {
                floatParam.setDst(distLevel + "级");
              }
              floatParam.setTmpId(floatTemplateId);
              // 如果是在房间的话，那么就跳转到指定直播间。否则就不填入roomId
              if (liveRoomInfo.getInRoom()) {
                floatParam.setRid(liveRoomInfo.getId());
              }
              floatParam.setGift("");
              chatService.sendFloatMessage(floatParam);
            }
          } catch (Exception e) {
            LOG.error("fail to send floatMessage.", e);
          }
        } else {
          // 没有达到飘屏，就不发送飘屏
        }

        // 勋章
        if (grade >= topLevel) {
          medalService.decorateTopMedalAndAvatar(orderMessage.getBuyerId());
        }
      }
    } catch (Exception e) {
      // 如果发送升级消息失败，不进入dead-letter队列
      LOG.error("send wealth level update message failed. uid:{}, grade:{}", orderMessage.getBuyerId(), grade, e);
    }
  }
}

```

```java
@Service
public class GrowWealthServiceImpl implements IGrowWealthService {
	protected Logger LOG = LoggerFactory.getLogger(getClass());
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private IGiftWealthService giftWealthService;

	@Autowired
	private IChatService chatService;

	@Autowired
	private IRemoteSyncRemoteUserInfoQueryService userInfoQueryService;

	@Autowired
	private IRemoteSyncNobleService nobleService;

	@Autowired
	private MedalService medalService;

	@Value("${floatTemplateId}")
	private Integer floatTemplateId;

	@Value("${floatLevel}")
	private Integer floatLevel;

	@Value("${highLevel}")
	private Integer highLevel;

	@Value("${topLevel}")
	private Integer topLevel;

	@Autowired
	@Qualifier("lamia")
	private ILiveService lamiaService;


	@Override
	public void growWealth(Long buyerId, Double contribution, Long anchorUid,String orderNo) {
		String wealthKey = KeyGen.buildWealthKey(buyerId);
		String wealthExistKey = KeyGen.buildWealthOrderNoKey(orderNo);
		if (!redisTemplate.hasKey(wealthExistKey)) {
			try {
				CheckInfo checkInfo = nobleService.checkPermisson(buyerId, BenefitTypeEnum.GROWTH_FAST.getType());
				if (checkInfo.getCheckRes() != null && checkInfo.getCheckRes().equals(CheckResEnum.HAVE_OPEN.getType())) {
					contribution = contribution * (checkInfo.getTemplateId().doubleValue() / 100);
					LOG.info("noble check grow fast. checkInfo:{}, actualContribution:{}", checkInfo, contribution);
				}
			} catch (Exception e) {
				LOG.error("fail to checkPermission from noble.", e);
			}
			// codis 增加贡献值
			Long actualLongContribution = contribution.longValue();
			LOG.info("actual long contribution:{}", actualLongContribution);
			Long currentContribution = redisTemplate.opsForValue().increment(wealthKey, actualLongContribution);
			LOG.info("incre contribution:{} current contribution:{} uid:{}", contribution, currentContribution, buyerId);
			int grade = 0;
			try {
				Map<Long, BasicUserInfo> idToUserInfo = Collections.emptyMap();
				grade = giftWealthService.checkIfUpGrade(actualLongContribution, currentContribution);
				// 如果有等级提升的话，就发送等级通知消息
				if (grade > 0) {
					// 发送飘屏
					if (grade >= floatLevel) {
						// 1.发送飘屏
						try {
							BasicUserInfo userInfo = idToUserInfo.get(buyerId);
							if (userInfo == null) {
								userInfo = userInfoQueryService.getBasicUserInfo(buyerId);
							}
							if (userInfo != null) {
								FloatParam floatParam = new FloatParam();
								floatParam.setSrc(userInfo.getNickname());
								Integer distLevel = grade;
								if (grade >= highLevel) {
									distLevel = grade - highLevel + 1;
									floatParam.setDst("神豪V" + distLevel);
								} else {
									floatParam.setDst(distLevel + "级");
								}
								floatParam.setTmpId(floatTemplateId);
								floatParam.setGift("");
								chatService.sendFloatMessage(floatParam);
							}
						} catch (Exception e) {
							LOG.error("fail to send floatMessage.", e);
						}
					}
					// 勋章
					if (grade >= topLevel) {
						medalService.decorateTopMedalAndAvatar(buyerId);
					}
				}
				redisTemplate.opsForValue().set(wealthExistKey,"1",1,TimeUnit.DAYS);
			} catch (Exception e) {
				// 如果发送升级消息失败，不进入dead-letter队列
				LOG.error("send wealth level update message failed. uid:{}, grade:{}", buyerId, grade, e);
			}
		}
	}
}

```

```java
/**
 * @author wangql
 * @since 2019/11/7 2:16 下午
 */
public abstract class AbstractWealthGrowthTemplate {

    protected Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    protected RedisTemplate<String, String> redisTemplate;

    @Autowired
    protected IGiftWealthService giftWealthService;

    @Autowired
    protected IChatService chatService;

    @Autowired
    protected IRemoteSyncRemoteUserInfoQueryService userInfoQueryService;

    @Autowired
    protected IRemoteSyncNobleService nobleService;

    @Autowired
    protected MedalService medalService;

    @Autowired
    protected LiveRoomInfoStrategyService liveRoomInfoStrategyService;

    @Value("${floatTemplateId}")
    protected Integer floatTemplateId;

    @Value("${hallTemplateId}")
    protected Integer hallFloatTemplateId;

    @Value("${floatLevel}")
    protected Integer floatLevel;

    @Value("${highLevel}")
    protected Integer highLevel;

    @Value("${topLevel}")
    protected Integer topLevel;

    public void doWealthGrowth(WealthGrowthParam param) {
        // pre检测，检测是否符合执行条件
        if (preFilter(param)) {return;}

        //对数据进行预先处理
        param = preProcessor(param);

        //有权限并开通需要重新计算contribution
        Double contribution = checkHaveOpen(param);

        Long actualLongContribution = contribution.longValue();
        Long currentContribution = increaseContributionValue(actualLongContribution, param.getBuyerId());

        int grade = 0;
        try {
            grade = giftWealthService.checkIfUpGrade(actualLongContribution, currentContribution);

            gradePostProcessor(grade, param);

        } catch (Exception e) {
            // 如果发送升级消息失败，不进入dead-letter队列
            LOG.error("send wealth level update message failed. uid:{}, grade:{}", param.getBuyerId(), grade, e);
        }
    }

    /**
     * 有权限并开通需要修改contribution
     * @param param
     */
    private Double checkHaveOpen(WealthGrowthParam param) {
        LOG.info("checkPerMission param is :{}", param);
        Long buyerId = param.getBuyerId();
        Double contribution = param.getContribution();

        try {
            CheckInfo checkInfo = nobleService.checkPermisson(buyerId, BenefitTypeEnum.GROWTH_FAST.getType());
            if (checkInfo.getCheckRes() != null && checkInfo.getCheckRes().equals(CheckResEnum.HAVE_OPEN.getType())) {
                contribution = contribution * (checkInfo.getTemplateId().doubleValue() / 100);
                LOG.info("noble check grow fast. checkInfo:{}, actualContribution:{}", checkInfo, contribution);
            }
        } catch (Exception e) {
            LOG.error("fail to checkPermission from noble. WealthGrowthParam is {}", param);
        }
        return contribution;
    }

    private Long increaseContributionValue(Long actualLongContribution, Long buyerId) {
        String wealthKey = KeyGen.buildWealthKey(buyerId);
        // codis 增加贡献值
        LOG.info("actual long contribution:{}", actualLongContribution);
        Long currentContribution = redisTemplate.opsForValue().increment(wealthKey, actualLongContribution);
        LOG.info("incre contribution:{} current contribution:{} uid:{}", actualLongContribution, currentContribution, buyerId);
        return currentContribution;
    }

    private void gradePostProcessor(Integer grade, WealthGrowthParam param) {
        Long buyerId = param.getBuyerId();
        LOG.info("gradePostProcessor, userId is : {} grade is: {} wealthGrowthParam is: {}", buyerId, grade, param);
        if (grade <= 0) {
            // 如果没有等级提升的话，不发送等级通知消息
            return;
        }

        WealthGrowthBusinessParam businessParam = prepareData(param);

        //主流程放在最上面，避免前面流程失败导致佩戴勋章失败
        if (grade >= topLevel) {
            // 勋章
            medalService.decorateTopMedalAndAvatar(buyerId);
        }

        if (grade >= floatLevel) {
            // 发送飘屏
            floatScreen(grade, businessParam, buyerId);
        }

        //不同业务的实现
        postProcess(grade, buyerId, param, businessParam);
    }

    /**
     * 发送飘屏
     * @param grade
     * @param businessParam
     * @param buyerId
     */
    private void floatScreen(Integer grade, WealthGrowthBusinessParam businessParam, Long buyerId) {
        try {
            BasicUserInfo userInfo = businessParam.idToUserInfo.get(buyerId);

            if (userInfo != null) {
                FloatParam floatParam = new FloatParam();
                floatParam.setSrc(userInfo.getNickname());
                Integer distLevel = grade;
                if (grade >= highLevel) {
                    distLevel = grade - highLevel + 1;
                    floatParam.setDst("神豪V" + distLevel);
                } else {
                    floatParam.setDst(distLevel + "级");
                }
                floatParam.setGift("");
                floatParam = buildFloatParam(floatParam, businessParam.businessType, businessParam.liveRoomInfo);
                chatService.sendFloatMessage(floatParam);
            }
        } catch (Exception e) {
            LOG.error("fail to send floatMessage.", e);
        }
    }

    /**
     * 将枚举转化为对应的id
     * @param floatTypeEnum
     * @return
     */
    Integer convertFromEnum(FloatTypeEnum floatTypeEnum) {
        if (FloatTypeEnum.LIVE_FLOAT.equals(floatTypeEnum)) {return floatTemplateId;}
        if (FloatTypeEnum.HALL_FLOAT.equals(floatTypeEnum)) {return hallFloatTemplateId;}
        throw new RuntimeException("floatTypeEnum invalid");
    }

    /**
     * 参数提前校验
     * @param param
     * @return
     */
    protected abstract boolean preFilter(WealthGrowthParam param);

    /**
     * 对数据进行预先处理
     * @param param
     * @return
     */
    protected abstract WealthGrowthParam preProcessor(WealthGrowthParam param);


    /**
     * 为后面的主播消息 && 飘屏幕准备需要的数据
     * @param param
     * @return
     */
    protected abstract WealthGrowthBusinessParam prepareData(WealthGrowthParam param);


    /**
     * 设置FloatParam的appId以及tmpId等信息
     * @param liveRoomInfo
     * @param floatParam
     * @param businessType
     * @return
     */
    protected abstract FloatParam buildFloatParam(FloatParam floatParam, Integer businessType, LiveRoomInfo liveRoomInfo);


    /**
     *  发送直播消息
     * @param grade
     * @param buyerId
     * @param param
     * @param businessParam
     */
    protected abstract void postProcess(Integer grade, Long buyerId, WealthGrowthParam param, WealthGrowthBusinessParam businessParam);
}

```

```java
/**
 * @author wangql
 * @since 2019/11/7 4:26 下午
 */
@Service
public class GiftOrderWealthGrowth extends AbstractWealthGrowthTemplate {

    /**
     * 前置过滤， 背包中的礼物不会增加财富值
     * @param param
     * @return
     */
    @Override
    protected boolean preFilter(WealthGrowthParam param) {
        // 如果为背包中的礼物不会增加财富值
        GiftOrderContext orderContext = param.getOrderContext();
        GiftOrderMessageNew orderMessage = param.getOrderMessage();

        if (orderContext.getPackageGift() != null && orderContext.getPackageGift()) {
            LOG.info("package gift:{}", orderMessage);
            return true;
        }
        return false;
    }

    @Override
    protected WealthGrowthParam preProcessor(WealthGrowthParam param) {

        GiftOrderMessageNew orderMessage = param.getOrderMessage();
        GiftOrderContext orderContext = param.getOrderContext();

        BigDecimal giftUnitPrice = orderMessage.getDraftOrderItems().get(0).getUnitPrice();
        Long quantity = orderMessage.getDraftOrderItems().get(0).getQuantity();

        double contribution = giftUnitPrice.multiply(BigDecimal.valueOf(quantity)).multiply(Constants.CONTRIBUTION_RATE)
                .doubleValue();
        // 如果是开宝箱的话，不能直接取价格了。
        if (orderContext.getBoxPrice() != null && orderContext.getBoxPrice() > 0) {
            contribution = orderContext.getBoxPrice();
        }

        param.setContribution(contribution);
        return param;
    }

    @Override
    protected WealthGrowthBusinessParam prepareData(WealthGrowthParam param) {

        WealthGrowthBusinessParam businessParam = new WealthGrowthBusinessParam();

        GiftOrderMessageNew orderMessage = param.getOrderMessage();

        GiftOrderContext orderContext = JSON.parseObject(orderMessage.getContext(), GiftOrderContext.class);

        businessParam.businessType = orderMessage.getBusinessTypeId();

        businessParam.liveRoomInfo = liveRoomInfoStrategyService.getLiveRoomInfo(businessParam.businessType, orderContext);

        businessParam.idToUserInfo = userInfoQueryService.multiGetBasicUserInfo(Arrays
                .asList(businessParam.liveRoomInfo.getUid(), param.getBuyerId()));

        return businessParam;
    }

    @Override
    protected FloatParam buildFloatParam(FloatParam floatParam, Integer businessType, LiveRoomInfo liveRoomInfo) {
        // 如果是在房间的话，那么就跳转到指定直播间。否则就不填入roomId
        if (liveRoomInfo.getInRoom()) {
            floatParam.setRid(liveRoomInfo.getId());
        }
        //设置对应的飘屏模板id和对应的直播间
        BusinessTypeEnum businessTypeEnum = BusinessTypeEnum.findByBusiness(businessType);
        floatParam.setAppIds(businessTypeEnum.getFloatAppIds());
        floatParam.setTmpId(convertFromEnum(businessTypeEnum.getFloatTypeEnum()));
        return floatParam;
    }

    /**
     * 发送直播间消息
     * @param grade
     * @param buyerId
     * @param param
     * @param businessParam
     */
    @Override
    protected void postProcess(Integer grade, Long buyerId, WealthGrowthParam param, WealthGrowthBusinessParam businessParam) {
        Map<Long, BasicUserInfo> idToUserInfo = businessParam.idToUserInfo;
        LiveRoomInfo liveRoomInfo = businessParam.liveRoomInfo;
        Integer businessType = businessParam.businessType;

        //不在直播间直接返回不发送直播间消息
        if (!liveRoomInfo.getInRoom()) {return;}

        // 如果在直播间内的话，就发送广播消息和用户更新消息
        UpgradeNoticeType upgradeNoticeType = liveRoomInfoStrategyService.getUpgradeNoticeTypeByBusinessType(businessType);

        try {
            if (upgradeNoticeType.equals(UpgradeNoticeType.ANCHOR_NOTICE)) {
                // 发送主播口吻的广播消息
                chatService.sendWealthLevelUpdateMessage(Converter
                        .assembleWealthLevelParam(liveRoomInfo, buyerId, grade, idToUserInfo, highLevel, businessType));

            } else if (upgradeNoticeType.equals(UpgradeNoticeType.SYSTEM_NOTICE)) {
                // 发送系统广播消息
                chatService.sendWealthLevelUpdateMessageBySystemNotice(Converter
                        .assembleWealthLevelParamOfSystemNotice(liveRoomInfo, grade, idToUserInfo.get(buyerId), highLevel, businessType));

            } else {
                LOG.error("unknown upgrade notice type:{}", upgradeNoticeType);
            }
        } catch (Exception e) {
            LOG.error("fail to send wealth level update message.", e);
        }

        try {
            // 发送用户信息更新的消息
            chatService.sendUserInfoUpdateMessage(Converter.assembleUserInfoParam(liveRoomInfo, buyerId, businessType));
        } catch (Exception e) {
            LOG.error("fail to send userInfo update message.", e);
        }
    }

    /**
     * 由于之前有!liveRoomInfo.getInRoom()判断，所以businessType只存在于
     * 400，606，605， 609之间
     * @param businessType
     * @return
     */
    public static Integer convertBusinessTypeToAppId(Integer businessType) {
        return BusinessTypeEnum.findAppIdByBusiness(businessType);
    }
}

```

```java
/**
 * @author wangql
 * @since 2019/11/7 4:26 下午
 */
@Service
public class VirtualCoinOrderWealthGrowth extends AbstractWealthGrowthTemplate {

    /**
     * 虚拟币订单会重复发送，幂等性保证
     * @param param
     * @return
     */
    @Override
    protected boolean preFilter(WealthGrowthParam param) {
        return !redisTemplate.hasKey(KeyGen.buildWealthOrderNoKey(param.getOrderNo()));
    }

    @Override
    protected WealthGrowthParam preProcessor(WealthGrowthParam param) {
        return param;
    }

    @Override
    protected WealthGrowthBusinessParam prepareData(WealthGrowthParam param) {
        WealthGrowthBusinessParam businessParam = new WealthGrowthBusinessParam();
        businessParam.idToUserInfo = userInfoQueryService.multiGetBasicUserInfo(Arrays.asList(param.getBuyerId()));
        return businessParam;
    }

    @Override
    protected FloatParam buildFloatParam(FloatParam floatParam, Integer businessType, LiveRoomInfo liveRoomInfo) {
        BusinessTypeEnum defaultType = BusinessTypeEnum.DEFAULT_TYPE;

        floatParam.setTmpId(convertFromEnum(defaultType.getFloatTypeEnum()));
        floatParam.setAppIds(defaultType.getFloatAppIds());
        return floatParam;
    }

    /**
     * 幂等性保证
     * @param param
     */
    @Override
    protected void postProcess(Integer grade, Long buyerId, WealthGrowthParam param, WealthGrowthBusinessParam businessParam) {
        redisTemplate.opsForValue().set(KeyGen.buildWealthOrderNoKey(param.getOrderNo()),"1",1, TimeUnit.DAYS);
    }
}

```

```java
/**
 * 用于财富等级的listener，businessType对应的 appId 以及 飘屏的 appId
 * @author wangql
 * @since 2019/11/18 7:59 下午
 */
public enum BusinessTypeEnum {

    /**
     * 直播礼物
     */
    LIVE_GIFT(1, 400, "直播礼物", FloatTypeEnum.LIVE_FLOAT,
            buildSet(FloatTypeEnum.LIVE_FLOAT.getAppId(), FloatTypeEnum.HALL_FLOAT.getAppId())),
    /**
     * 直播弹幕
     */
    LIVE_BARRAGE(1, 606, "直播弹幕", FloatTypeEnum.LIVE_FLOAT,
            buildSet(FloatTypeEnum.LIVE_FLOAT.getAppId(), FloatTypeEnum.HALL_FLOAT.getAppId())),
    /**
     * 娱乐厅礼物
     */
    HALL_GIFT(5, 609, "娱乐厅礼物", FloatTypeEnum.HALL_FLOAT,
            buildSet(FloatTypeEnum.LIVE_FLOAT.getAppId(), FloatTypeEnum.HALL_FLOAT.getAppId())),
    /**
     * KTV礼物
     */
    KTV_GIFT(3, 605, "K歌房送礼", FloatTypeEnum.LIVE_FLOAT,
            buildSet(FloatTypeEnum.HALL_FLOAT.getAppId())),
    /**
     * 默认飘屏参数设置
     */
    DEFAULT_TYPE(1, -1, "默认飘屏配置", FloatTypeEnum.LIVE_FLOAT,
            buildSet(FloatTypeEnum.LIVE_FLOAT.getAppId(), FloatTypeEnum.HALL_FLOAT.getAppId()));

    private Integer appId;

    private Integer businessTypeId;

    private String desc;

    private Set<Integer> floatAppIds;

    private FloatTypeEnum floatTypeEnum;

    BusinessTypeEnum(Integer appId, Integer businessTypeId, String desc,
                     FloatTypeEnum floatTypeEnum, Set<Integer> floatAppIds) {
        this.appId = appId;
        this.businessTypeId = businessTypeId;
        this.desc = desc;
        this.floatAppIds = floatAppIds;
        this.floatTypeEnum = floatTypeEnum;
    }

    public static BusinessTypeEnum findByBusiness(Integer businessTypeId) {
        return Arrays.stream(values())
                .filter(businessTypeEnum -> Objects.equals(businessTypeEnum.getBusinessTypeId(), businessTypeId))
                .findFirst()
                .orElse(DEFAULT_TYPE);
    }

    public static Integer findAppIdByBusiness(Integer businessTypeId) {
        return findByBusiness(businessTypeId).getAppId();
    }

    public Integer getAppId() {
        return appId;
    }

    public Set<Integer> getFloatAppIds() {
        return floatAppIds;
    }

    public Integer getBusinessTypeId() {
        return businessTypeId;
    }

    public FloatTypeEnum getFloatTypeEnum() {
        return floatTypeEnum;
    }

    @SafeVarargs
    @SuppressWarnings("varargs")
    private static <T>Set<T> buildSet(T... a) {
        return new HashSet<>(Arrays.asList(a));
    }
}

```

```java
/**
 * @author wangql
 * @since 2019/11/18 7:59 下午
 */
public enum FloatTypeEnum {

    /**
     * 直播飘屏
     */
    LIVE_FLOAT(1),
    /**
     * 娱乐厅飘屏
     */
    HALL_FLOAT(5);

    private Integer appId;

    FloatTypeEnum(Integer appId) {
        this.appId = appId;
    }

    public Integer getAppId() {
        return appId;
    }
}

```


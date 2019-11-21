package test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 用于
 * @author wangql
 * @since 2019/11/18 7:59 下午
 */
public enum BusinessTypeEnum {

    /**
     * 直播礼物
     */
    LIVE_GIFT(1, 400, "直播礼物", FloatTypeEnum.LIVE_FLOAT,
            new HashSet<>(Arrays.asList(FloatTypeEnum.LIVE_FLOAT.getAppId(), FloatTypeEnum.HALL_FLOAT.getAppId()))),
    /**
     * 直播弹幕
     */
    LIVE_BARRAGE(1, 606, "直播弹幕", FloatTypeEnum.LIVE_FLOAT,
            new HashSet<>(Arrays.asList(FloatTypeEnum.LIVE_FLOAT.getAppId(), FloatTypeEnum.HALL_FLOAT.getAppId()))),
    /**
     * 娱乐厅礼物
     */
    HALL_GIFT(5, 609, "娱乐厅礼物", FloatTypeEnum.HALL_FLOAT,
            new HashSet<>(Arrays.asList(FloatTypeEnum.LIVE_FLOAT.getAppId(), FloatTypeEnum.HALL_FLOAT.getAppId()))),
    /**
     * KTV礼物
     */
    KTV_GIFT(3, 605, "K歌房送礼", FloatTypeEnum.LIVE_FLOAT,
            new HashSet<>(Arrays.asList(FloatTypeEnum.HALL_FLOAT.getAppId()))),
    /**
     * 默认飘屏参数设置
     */
    DEFAULT_TYPE(1, -1, "默认飘屏配置", FloatTypeEnum.LIVE_FLOAT,
            new HashSet<>(Arrays.asList(FloatTypeEnum.LIVE_FLOAT.getAppId(), FloatTypeEnum.HALL_FLOAT.getAppId())));

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
//        for (BusinessTypeEnum businessTypeEnum : values()) {
//            if (businessTypeEnum.businessTypeId.equals(businessTypeId)) {
//                return businessTypeEnum;
//            }
//        }
//        return DEFAULT_TYPE;

        return Arrays.stream(values())
                .filter(businessTypeEnum -> Objects.equals(businessTypeEnum.getBusinessTypeId(), businessTypeId))
                .findFirst()
                .orElse(DEFAULT_TYPE);
    }

    public static Integer findAppIdByBusiness(Integer businessTypeId) {
        for (BusinessTypeEnum businessTypeEnum : values()) {
            if (businessTypeEnum.businessTypeId.equals(businessTypeId)) {
                return businessTypeEnum.getAppId();
            }
        }
        return DEFAULT_TYPE.getAppId();
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

    public String getDesc() {
        return desc;
    }

    public FloatTypeEnum getFloatTypeEnum() {
        return floatTypeEnum;
    }
}

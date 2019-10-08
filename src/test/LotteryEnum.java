package test;

/**
 * @Author alan
 * @Date 2018/12/10
 */
public enum LotteryEnum {
	/**
	 * 一连抽
	 */
	ONE_TIME(1,10,"一连抽","-10个幸运星"),
	/**
	 * 十连抽
	 */
	TEN_TIMES(10,100,"十连抽","-100个幸运星"),
	/**
	 * 百连抽
	 */
	ONE_HUNDRED_TIMES(100,1000,"百连抽","-1000个幸运星"),
	/**
	 * 不支持的连抽
	 */
	DEFAULT(-1,-1,"不支持的连抽","不支持的幸运星消费");

	private int value;
	private int cost;
	private String lotteryTimes;
	private String couponCost;

	LotteryEnum(int value, int cost, String lotteryTimes, String couponCost) {
		this.value = value;
		this.cost = cost;
		this.lotteryTimes = lotteryTimes;
		this.couponCost = couponCost;
	}

	public int getValue() {
		return value;
	}

	public int getCost() {
		return cost;
	}

	public String getLotteryTimes() {
		return lotteryTimes;
	}

	public String getCouponCost() {
		return couponCost;
	}

	/**
	 * 通过value获取LotteryEnum
	 *
	 * @param val value
	 * @return LotteryEnum枚举
	 */
	public static LotteryEnum getByValue(int val) {
		LotteryEnum[] values = LotteryEnum.values();
		for (LotteryEnum value : values) {
			if (val == value.value) {
				return value;
			}
		}
		return DEFAULT;
	}

	/**
	 * 通过cost获取LotteryEnum
	 * @param val
	 * @return
	 */
	public static LotteryEnum getByCost(int val) {
		LotteryEnum[] values = LotteryEnum.values();
		for (LotteryEnum value : values) {
			if (val == value.cost) {
				return value;
			}
		}
		return DEFAULT;
	}
}

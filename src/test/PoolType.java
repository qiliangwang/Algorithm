package test;

/**
 * Created by paul.liu on 2019/7/10.
 */
public enum PoolType {
  DEFAULT(1, "默认奖池"), ACTIVITY_SPECIAL(2, "活动特殊奖池"), ASSIGNMENT(3, "个人奖池");

  private int code;
  private String description;

  PoolType(int code, String description) {
    this.code = code;
    this.description = description;
  }

  public int getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }

  public static PoolType findByCode(int code) {
    for (PoolType poolType : PoolType.values()) {
      if (poolType.getCode() == code) {
        return poolType;
      }
    }
    return null;
//    throw new IllegalArgumentException("invalid poolType argument. code:" + code);
  }
}

package test;

import java.util.Arrays;

/**
 * @author wangql
 * @since 2020-04-22 14:10
 */
public class LotteryLoss {


  private Integer type;
  private Integer pickCount;
  private Integer winCount;

  public LotteryLoss(Integer type, Integer pickCount, Integer winCount) {
    this.type = type;
    this.pickCount = pickCount;
    this.winCount = winCount;
  }

  /**
   * 计算失去翻牌卷
   * @return
   */
  public Integer calLoss() {
    LotteryType lotteryType = LotteryType.findByType(this.type);
    return pickCount * lotteryType.getCostPerCount();
  }

  /**
   * 计算获得喜钻
   * @return
   */
  public Integer calWin() {
    LotteryType lotteryType = LotteryType.findByType(this.type);
    return winCount * lotteryType.getGainPerCount();
  }


  public enum LotteryType {
    /**
     * 低级抽奖
     */
    LOW(1, 1, 100),
    MIDDLE(2, 10, 1000),
    HIGH(3, 100, 10000);
    ;

    private Integer type;
    private Integer costPerCount;
    private Integer gainPerCount;

    LotteryType(Integer type, Integer costPerCount, Integer gainPerCount) {
      this.type = type;
      this.costPerCount = costPerCount;
      this.gainPerCount = gainPerCount;
    }

    public static LotteryType findByType(Integer type) {

      return Arrays.stream(values())
          .filter(x -> type.equals(x.getType()))
          .findFirst().orElse(null);
    }

    public Integer getType() {
      return type;
    }

    public void setType(Integer type) {
      this.type = type;
    }

    public Integer getCostPerCount() {
      return costPerCount;
    }

    public void setCostPerCount(Integer costPerCount) {
      this.costPerCount = costPerCount;
    }

    public Integer getGainPerCount() {
      return gainPerCount;
    }

    public void setGainPerCount(Integer gainPerCount) {
      this.gainPerCount = gainPerCount;
    }
  }
}

package test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangql
 * @since 2020-04-22 14:10
 */
public class LossTest {

  public static void main(String[] args) {
    LotteryLoss low = new LotteryLoss(LotteryLoss.LotteryType.LOW.getType(), 1051, 112);
    LotteryLoss mid = new LotteryLoss(LotteryLoss.LotteryType.MIDDLE.getType(), 296, 30);
    LotteryLoss high = new LotteryLoss(LotteryLoss.LotteryType.HIGH.getType(), 115, 11);

    List<LotteryLoss> lotteryLosses = Arrays.asList(low, mid, high);

    Integer totalLoss = 0;
    Integer totalWin = 0;

    for (LotteryLoss lotteryLoss : lotteryLosses) {
      totalLoss += lotteryLoss.calLoss();
      totalWin  += lotteryLoss.calWin();
    }

    System.out.println("totalLoss is " + totalLoss + " \ntotalWin is " + totalWin);
  }
}

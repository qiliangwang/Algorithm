package test;

/**
 * @author wangql
 * @since 2019-10-08 14:09
 */
public class hello {

    public static void main(String[] args) {
        LotteryEnum lotteryEnum = LotteryEnum.ONE_TIME;

        lotteryEnum = LotteryEnum.DEFAULT;


        switch (lotteryEnum) {
            case ONE_TIME:
            case TEN_TIMES:
            case ONE_HUNDRED_TIMES:
                System.out.println(lotteryEnum + " doLottery " + " successfully! ");
                break;
            default:
                throw new RuntimeException("unsupported lottery type = " + lotteryEnum);
        }
    }
}

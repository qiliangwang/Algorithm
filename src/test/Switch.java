package test;

import test.gc.GiftNew;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangql
 * @since 2020/1/14 6:34 下午
 */
public class Switch {
  public static void switchSimpleTest() {
//    List<Integer> list = Arrays.asList(1, 2, 3, 1, 2, 3, 1, 2, 3);
//    for (Integer i : list) {
//      switch (i){
//        case 3:
//        case 1:
//          System.out.println("1" + i);
//          break;
//        case 2:
//          System.out.println("2" + i);
//          break;
//      }
//    }
  }

    public static void main(String[] args) {
      GiftNew giftNew = new GiftNew();
      System.out.println("price" + giftNew.getPrice().intValue());
//      switchSimpleTest();
//      clearPool(1, 1);
//      clearPool(2, 1);
//      clearPool(3, 1);
//      clearPool(4, 1);
    }

    private void testSwitch(Integer code) {
        switch (code) {
            case 20: case 30: case 50:{
                // do something
            }
            case 60: {
                // do other things
            }

        }
    }

  public static void clearPool(Integer poolType, Integer businessType) {
    PoolType poolTypeEnum = PoolType.findByCode(poolType);
    String poolKey = "";
    switch (poolTypeEnum) {
      case DEFAULT:
        poolKey = "1";
        break;
      case ACTIVITY_SPECIAL:
        poolKey = "2";
        break;
//      case ASSIGNMENT:
//        poolKey = "3";
//        break;
      default:
        return;
    }
    System.out.println(poolKey);
  }
}

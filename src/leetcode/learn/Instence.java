package leetcode.learn;

/**
 * @author wangql
 * @since 2021-08-30 20:21
 */
public class Instence {
  private static volatile  Instence INSTENCE;

  public static Instence getInstence() {
    if (INSTENCE == null) {
      synchronized (Instence.class) {
        if (INSTENCE == null) {
          INSTENCE = new Instence();
        }
      }
    }
    return INSTENCE;
  }

  private Instence() {
  }

}

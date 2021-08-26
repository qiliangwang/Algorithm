package basic;

/**
 * @author wangql
 * @since 2021-02-20 10:54
 */
public class SQL {

  public static void main(String[] args) {
    for (int i = 0; i < 128; i ++) {
      System.out.println("ALTER TABLE `tb_red_packet" + i +"` ADD COLUMN `context` varchar(1000) NOT NULL DEFAULT '' " +
          "COMMENT '额外业务字段';");
    }
  }
}

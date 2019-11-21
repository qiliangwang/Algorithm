package test;

/**
 * @author wangql
 * @since 2019/11/21 9:35 上午
 */
public class EnumTest {
    public static void main(String[] args) {
        Integer appIdByBusiness = BusinessTypeEnum.findAppIdByBusiness(-1);
        BusinessTypeEnum byBusiness = BusinessTypeEnum.findByBusiness(-1);
        System.out.println(appIdByBusiness);
        System.out.println(byBusiness);
    }
}

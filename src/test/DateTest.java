package test;

import java.util.Date;

/**
 * @author wangql
 * @since 2019/11/20 1:58 下午
 */
public class DateTest {
    public static void main(String[] args) {
        Date effectiveTime = new Date(1575216000000L);
        System.out.println(effectiveTime);
//        boolean res = effectiveTime.before(new Date());
//        boolean res = new Date().after(effectiveTime);
//        System.out.println(res);
    }
}

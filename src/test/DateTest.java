package test;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wangql
 * @since 2019/11/20 1:58 下午
 */
public class DateTest {
    public static void main(String[] args) {
//        int time = 10;
//        System.out.println(time * 10L);
//
//        System.out.println(new Date(1584001378000L));

//      String hello = "1";
//      BigDecimal bigDecimal = new BigDecimal(hello);
//      System.out.println(bigDecimal);

      String format = LocalDate.now().minusDays(1).format(DateTimeFormatter.ISO_DATE);
      System.out.println(format);

      String format1 = LocalDate.now().minusDays(0).format(DateTimeFormatter.ISO_DATE);
      System.out.println(format1);

      String format2 = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
      System.out.println(format2);

//      BigDecimal discountRateBD = new BigDecimal("80");
//      Integer integer = null;
//      System.out.println(BigDecimal.valueOf(integer).multiply(discountRateBD));
//        Date effectiveTime = new Date(1582720691872L);
//        System.out.println(effectiveTime);
//
//        Date effectiveTime1 = new Date(1582545908006L);
//        System.out.println(effectiveTime1);
//
//        Date effectiveTime2 = new Date(1582462637211L);
//        System.out.println(effectiveTime2);
//
//        Date now = new Date();
//        System.out.println(now);
//        boolean res = effectiveTime.before(new Date());
//        boolean res = new Date().after(effectiveTime);
//        System.out.println(res);

//      List<Integer> list = Arrays.asList(null, null);
//      List<Integer> collect = list.stream().filter(Objects::nonNull).collect(Collectors.toList());
//      System.out.println(collect);

      List<Long> collect = Stream.of(null, 1L)
          .filter(Objects::nonNull)
          .collect(Collectors.toList());
      System.out.println(collect);
//      List<Long> uidList = collect;
    }
}

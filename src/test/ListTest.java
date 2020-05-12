package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * @author wangql
 * @since 2020-05-12 14:32
 */
public class ListTest {

  public static void main(String[] args) {

    HashMap<Long, String> uidWealthValueMap = new HashMap<>();
    uidWealthValueMap.put(1L, "12345");
    uidWealthValueMap.put(2L, "12345");
    uidWealthValueMap.put(3L, "12345");
    uidWealthValueMap.put(4L, "12345");
    uidWealthValueMap.put(5L, "12345");
    uidWealthValueMap.put(6L, "12345");
    uidWealthValueMap.put(7L, "12345");
    uidWealthValueMap.put(8L, "12345");
    uidWealthValueMap.put(9L, "12345");
//    uidWealthValueMap.put(10L, null);

//    Map<Long, String> collect = uidWealthValueMap.entrySet().stream()
//        .collect(toMap(Map.Entry::getKey, value -> {convert((String) value.getValue(););}));
//
//    System.out.println(collect);


    Stream<String> s = Stream.of("apple", "banana", "orange");
    Map<Character, String> m = s.collect(
        Collectors.toMap(s1 -> s1.charAt(0),
            s1 -> s1));
    System.out.println(m);
  }

  private static Long convert(String value) {
    return 0L;
  }
}

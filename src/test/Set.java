package test;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author wangql
 * @since 2020-01-28 11:41
 */
public class Set {
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < 10; i ++) {
            set.add(1);
        }
        System.out.println(set);
    }
}

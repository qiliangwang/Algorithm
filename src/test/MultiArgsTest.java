package test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wangql
 * @since 2019/11/25 6:22 下午
 */
public class MultiArgsTest {
    @SafeVarargs
    private static <T> Set<T> buildSet(T... a) {
        return new HashSet<>(Arrays.asList(a));
    }

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> set1 = buildSet(1, 2, 3);
        System.out.println(set);
        System.out.println(set1);
    }
}

package leetcode.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayUtil {

    public static void printArray(int[] nums) {
        List<Integer> collect = Arrays.stream(nums).boxed().collect(Collectors.toList());
        System.out.println(collect);
    }
}

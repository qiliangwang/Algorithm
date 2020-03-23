package test;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangql
 * @since 2020-03-16 14:54
 */
public class ArrayTest {
    public static void main(String[] args) {
        int[] list = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int[] res = Arrays.copyOfRange(list, 0, 5);

        System.out.println(res);
    }
}

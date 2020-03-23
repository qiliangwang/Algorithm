package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author wangql
 * @since 2020-03-13 16:28
 */
public class RandomTest {
    private static Random random = new Random();
    public static void main(String[] args) {
        List<Integer> cardNos = new ArrayList<>();
        for (int i = 1; i < 100; i ++) {
            cardNos.add(i);
            // Obtain a number between [0 - 49]. nextInt(50)
            int n = randomGetFromList(cardNos);
            System.out.println(n);
        }


    }

    private static Integer randomGetFromList(List<Integer> list) {
        return list.get(random.nextInt(list.size()));
    }
}

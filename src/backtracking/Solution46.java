package backtracking;

import java.util.ArrayList;
import java.util.List;

public class Solution46 {
    private ArrayList<List<Integer>> results;
    private boolean[] used;

    private void generatePermutation(int[] numbers, int index, List<Integer> permutation) {
        if (numbers.length == index) {
            results.add(permutation);
            return;
        }

        for (int i = 0; i < numbers.length; i ++) {
            if (!used[index]) {
                permutation.add(numbers[i]);
                used[i] = true;
                generatePermutation(numbers, index+1, permutation);
            }
        }
    }

    public List<List<Integer>> permute(int[] numbers) {
        return null;
    }

    public static void main(String[] args) {

    }
}
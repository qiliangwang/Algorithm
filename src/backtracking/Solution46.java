package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution46 {

    private ArrayList<List<Integer>> results;
    private boolean[] used;

    private void generatePermutation(int[] numbers, int index, LinkedList<Integer> permutation) {
        if (numbers.length == index) {
            results.add((List<Integer>)permutation.clone());
            return;
        }

        for (int i = 0; i < numbers.length; i ++) {
            if (!used[i]) {
                permutation.addLast(numbers[i]);
                used[i] = true;
                generatePermutation(numbers, index+1, permutation);
                permutation.removeLast();
                used[i] = false;
            }
        }
    }

    public List<List<Integer>> permute(int[] numbers) {
        results = new ArrayList<List<Integer>>();
        used = new boolean[numbers.length];
        LinkedList<Integer> permutation = new LinkedList<Integer>();
        generatePermutation(numbers, 0, permutation);
        return results;
    }

    public static void main(String[] args) {
        int[] numbers = {1,2,3};
        Solution46 solution = new Solution46();
        solution.permute(numbers);
        for (List<Integer> res: solution.results) {
            System.out.println(res);
        }
    }
}
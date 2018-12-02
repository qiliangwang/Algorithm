package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution47 {

    private ArrayList<List<Integer>> results;
    private boolean[] used;

    private void generateUniquePermutation(int[] numbers, int index, LinkedList<Integer> permutation) {
        if (numbers.length == index) {
            if (results.contains(permutation)){
                return;
            }
            results.add((List<Integer>)permutation.clone());
            return;
        }

        for (int i = 0; i < numbers.length; i ++) {
            if (!used[i]) {
                permutation.addLast(numbers[i]);
                used[i] = true;
                generateUniquePermutation(numbers, index+1, permutation);
                permutation.removeLast();
                used[i] = false;
            }
        }
    }

    public List<List<Integer>> permuteUnique(int[] numbers) {
        results = new ArrayList<List<Integer>>();
        used = new boolean[numbers.length];
        LinkedList<Integer> permutation = new LinkedList<Integer>();
        generateUniquePermutation(numbers, 0, permutation);
        return results;
    }

    public static void main(String[] args) {
        int[] numbers = {1,1,2};
        Solution47 solution = new Solution47();
        List<List<Integer>> results = solution.permuteUnique(numbers);
        for (List<Integer> res: results) {
            System.out.println(res);
        }
    }
}
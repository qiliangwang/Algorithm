package leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

public class Solution39 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        helper(candidates, target, 0, res, new ArrayList<>());
        return res;
    }

    public void helper(int[] candidates, int target, int start, List<List<Integer>> res, List<Integer> list) {
        if (target < 0 ) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i ++) {
            list.add(candidates[i]);
            helper(candidates, target - candidates[i], i, res, list);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        List<List<Integer>> result = new Solution39().combinationSum(candidates, 7);
        System.out.println(result);
    }
}

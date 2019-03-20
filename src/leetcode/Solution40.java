package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution40 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
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
            if (i != start && candidates[i] == candidates[i - 1]) continue;
            list.add(candidates[i]);
            helper(candidates, target - candidates[i], i + 1, res, list);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {10,1,2,7,6,1,5};
        List<List<Integer>> result = new Solution40().combinationSum2(candidates, 8);
        System.out.println(result);
    }
}

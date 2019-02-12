package leetcode;

import java.util.HashSet;

/**
 * @author Vader Wang
 */
public class Solution217 {

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (!set.add(i)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3};
        boolean res = new Solution217().containsDuplicate(nums);
        System.out.println(res);
    }
}

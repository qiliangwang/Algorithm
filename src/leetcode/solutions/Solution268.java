package leetcode.solutions;

import java.util.HashSet;

/**
 * @author wangql
 * @since 2019/11/18 1:53 下午
 */
public class Solution268 {
    public int missingNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int i =0; i <= nums.length; i ++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return -1;
    }
}

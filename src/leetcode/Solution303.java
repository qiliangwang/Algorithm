package leetcode;

import java.util.ArrayList;
import java.util.List;

class NumArray {

    private int[] sum;

    public NumArray(int[] nums) {
        sum = new int[nums.length + 1];

        for (int i = 1; i < sum.length; i ++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
//        for (int i = 0; i < nums.length; i ++) {
//            sum[i + 1] = sum[i] + nums[i];
//        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums);
        int result = numArray.sumRange(0, 5);
        System.out.println(result);
    }
}

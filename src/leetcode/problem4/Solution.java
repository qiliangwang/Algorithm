package leetcode.problem4;

import java.util.Arrays;

public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int lengthTotal = nums1.length + nums2.length;
        if (lengthTotal % 2 == 0) {
            //平均数为lengthTotal/2, lengthTotal/2 + 1
            int i = 0, j =0;
            int result1 = 0, result2 = 0;
            for (int k = 0; k < lengthTotal/2; k ++) {
                if (k == lengthTotal / 2 - 1){
                    result1 = Math.max(nums1[i], nums2[j]);
                }
                int num1 = nums1[i];
                int num2 = nums2[j];
                if (i >= nums1.length - 1) {
                    j ++;
                }
                if (j >= nums2.length - 1){
                    i ++;
                } else {
                    if (num1 > num2) {
                        j ++;
                    }else {
                        i ++;
                    }
                }
            }
            result2 = Math.max(nums1[i], nums2[j]);
            System.out.println(result1 + "-----" + result2);
        }else {
            //平均数为lengthTotal/2 + 1
            int i = 0, j =0;
            int result1 = 0;
            for (int k = 0; k < lengthTotal/2 +1; k ++) {
                int num1 = nums1[i];
                int num2 = nums2[j];
                if (i >= nums1.length - 1) {
                    j ++;
                }
                if (j >= nums2.length - 1){
                    i ++;
                } else {
                    if (num1 > num2) {
                        j ++;
                    }else {
                        i ++;
                    }
                }
                if (k == lengthTotal / 2){
                    result1 = Math.max(nums1[i], nums2[j]);
                }
            }
            System.out.println(result1);
        }

        return 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 2, 3, 7, 8, 9};
        int[] nums2 = {1, 2, 3, 7, 8, 9};
        solution.findMedianSortedArrays(nums1, nums2);
    }
}

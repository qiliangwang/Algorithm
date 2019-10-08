package leetcode.solutions;

public class Solution88 {

    /**
     * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
     *
     * Note:
     *
     * The number of elements initialized in nums1 and nums2 are m and n respectively.
     * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
     * Example:
     *
     * Input:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     *
     * Output: [1,2,2,3,5,6]
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1, index2 = n - 1, totalLen = m + n - 1;
        while (index1 >= 0 && index2 >=0){
            if (nums1[index1] > nums2[index2]) {
                nums1[totalLen] = nums1[index1];
                index1 --;
            } else {
                nums1[totalLen] = nums2[index2];
                index2 --;
            }
            totalLen--;
        }
        while (index2 >= 0) {
            nums1[totalLen--] = nums2[index2--];
        }
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1, index2 = n - 1, totalIndex = m + n - 1;
        while (index1 >= 0 && index2 >=0){
            nums1[totalIndex--] = nums1[index1] > nums2[index2] ? nums1[index1--] : nums2[index2--];
        }
        // index1 > 1 does not matter 1
        while (index2 >= 0) {
            nums1[totalIndex--] = nums2[index2--];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        new Solution88().merge(nums1, 3, nums2, 3);
        for (int i : nums1) {
            System.out.print(i + " ");
        }
    }
}

package leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class Solution239 {

    /**
     * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
     *
     * Example:
     *
     * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
     * Output: [3,3,5,5,6,7]
     * Explanation:
     *
     * Window position                Max    Deque
     * ---------------               -----  -------
     * [1  3  -1] -3  5  3  6  7       3        0 -> 1 -> 1, 2
     *  1 [3  -1  -3] 5  3  6  7       3        1, 2, 3
     *  1  3 [-1  -3  5] 3  6  7       5        2, 3 -> 2 -> none, 4
     *  1  3  -1 [-3  5  3] 6  7       5        4, 5
     *  1  3  -1  -3 [5  3  6] 7       6        4, 5 -> none, 6
     *  1  3  -1  -3  5 [3  6  7]      7        6 -> none , 7
     * Note:
     * You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.
     *
     * Follow up:
     * Could you solve it in linear time?
     *
     * what deque remains is the index of max value
     * @param nums [1,3,-1,-3,5,3,6,7]
     * @param k 3
     * @return [3,3,5,5,6,7]
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];

        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length + 1 - k];

        for (int i = 0; i < nums.length; i++) {
            if (!deque.isEmpty() && deque.peekFirst() == i - k) {
                deque.poll();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.removeLast();
            }
            deque.offerLast(i);
            if ((i + 1) >= k) {
                res[i + 1 - k] = nums[deque.peek()];
            }
        }

        return res;
    }


    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int[] result = new Solution239().maxSlidingWindow(nums, 3);
        for (int res : result) {
            System.out.print(res + " ");
        }
    }
}

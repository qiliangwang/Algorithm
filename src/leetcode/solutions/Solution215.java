package leetcode.solutions;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution215 {

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;

        int left = 0, right = nums.length - 1;
        while (true) {
            int partition = partition(nums, left, right);
            if ((partition + 1) == k) {
                return nums[partition];
            } else if ((partition + 1) <= k) {
                left = partition + 1;
            } else {
                right = partition - 1;
            }
        }
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];

        int L = left + 1;
        int R = right;

        while (L <= R) {
            if (nums[L] < pivot && nums[R] > pivot) {
                swap(nums, L++, R--);
            }
            if (nums[L] >= pivot) L++;
            if (nums[R] <= pivot) R--;
        }
        swap(nums, left, R);
        return R;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /**
     * 找到第k大的数 利用heap
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1 - o2);
        for (int i = 0; i < nums.length; i ++) {
            pq.offer(nums[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }


    public int findKthLargest3(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1 - o2);
        for (int i = 0; i < nums.length; i ++) {
            if (pq.size() < k) {
                pq.offer(nums[i]);
            } else if (pq.peek() < nums[i]) {
                pq.offer(nums[i]);
                pq.poll();
            }
        }
        return pq.peek();
    }


    public static void main(String[] args) {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        int result = new Solution215().findKthLargest(nums, 4);
        System.out.println(result);
    }
}

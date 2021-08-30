package leetcode.solutions;

import java.util.*;

public class Solution347 {
    public int[] topKFrequent(int[] nums, int k) {
        //cal freq
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < nums.length; i ++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }
        //k size min heap to filter low freq element
        PriorityQueue<Node> minHeap = new PriorityQueue<>((o1, o2) -> o1.freq == o2.freq ? o1.val - o2.val : o1.freq - o2.freq);
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            minHeap.add(new Node(entry.getKey(), entry.getValue()));
            if (minHeap.size() > k) {minHeap.poll();}
        }
        //get remain k element
        int[] ans = new int[k];
        int i = 0;
        for (Node node : minHeap) {
            ans[i] = node.val;
            i ++;
        }
        return ans;
    }

    private class Node {
        int val;
        int freq;

        public Node(int val, int freq) {
            this.val = val;
            this.freq = freq;
        }
    }

    public static void main(String[] args) {
        int[] data = {1, 1, 1, 2, 2, 3};
        new Solution347().topKFrequent(data, 2);
    }
}

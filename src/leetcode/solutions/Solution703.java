package leetcode.solutions;

import java.awt.event.KeyAdapter;
import java.util.PriorityQueue;

class KthLargest {

    private int k;
    private PriorityQueue<Integer> minHeap;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        minHeap = new PriorityQueue<>((a, b) -> a - b);
        for (int num : nums) {
            add(num);
        }
    }

    /**
     * S使用堆来实现topk的求解 最小堆，经常pop不需要的元素
     * @param val
     * @return
     */
    public int add(int val) {
        minHeap.add(val);
        if (minHeap.size() > k) {
            minHeap.poll();
        }
        return minHeap.peek();
    }

    /**
     * 小优化
     * @param val
     * @return
     */
    public int add2(int val) {
        if (minHeap.size() < k) {
            minHeap.add(val);
        } else {
            if (val > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(val);
            }
        }
        return minHeap.peek();
    }




    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        kthLargest.add(3);
        kthLargest.add(5);
        kthLargest.add(10);
        kthLargest.add(9);
        kthLargest.add(4);
    }

}

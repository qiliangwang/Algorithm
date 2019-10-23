package leetcode.solutions;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Vader Wang
 */
public class MedianFinder {
    PriorityQueue<Integer> large;
    PriorityQueue<Integer> small;
    /** initialize your data structure here. */
    public MedianFinder() {
        large = new PriorityQueue<>();
        small = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void addNum(int num) {
        large.add(num);
        small.add(large.poll());
        if (large.size() < small.size()) {
            large.add(small.poll());
        }
    }

    public double findMedian() {
        return large.size() == small.size() ? ((double)large.peek() + (double)small.peek()) / 2 : large.peek();
    }
}

package leetcode.solutions;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 295. 数据流的中位数
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * 进阶:
 *
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 通过次数63,625提交次数121,659
 * @author Vader Wang
 */
class MedianFinder {

    PriorityQueue<Integer> large;
    PriorityQueue<Integer> small;
    /** initialize your data structure here. */
    public MedianFinder() {
        large = new PriorityQueue<>();
        small = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void addNum(int num) {
        //这2步操作其实就算出了中位数 不需要通过和smaller的比较来确定到底和现在的中位数的大小关系了
        large.add(num);
        small.add(large.poll());
        if (large.size() < small.size()) {
            large.add(small.poll());
        }
    }

    public double findMedian() {
        return large.size() == small.size() ? ((double)large.peek() + (double)small.peek()) / 2 : large.peek();
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        medianFinder.addNum(3);
        medianFinder.addNum(0);
        medianFinder.addNum(4);
        medianFinder.addNum(1);
        double result = medianFinder.findMedian();
        System.out.println(result);
    }
}


/**
 * 还是使用2个heap来实现 一个heap lessHeap 是一个maxHeap 放入比中间位置小的元素 还有一个largeHeap放入比中间位置大的元素
 * 保持 lessHeap >= large就可以了
 */
class MedianFinder2 {

    PriorityQueue<Integer> lessHeap;
    PriorityQueue<Integer> greaterHeap;

    public MedianFinder2() {
        //maxHeap
        lessHeap = new PriorityQueue<>((a,b) -> b-a);
        //minHeap
        greaterHeap = new PriorityQueue<>((a,b) -> a-b);
    }

    public void addNum(int num) {
        if (lessHeap.isEmpty()) {
            lessHeap.offer(num);
        } else {
            if (num > lessHeap.peek()) {
                greaterHeap.offer(num);
                if (lessHeap.size() < greaterHeap.size()) {
                    lessHeap.offer(greaterHeap.poll());
                }
            } else {
                lessHeap.offer(num);
                if (lessHeap.size() > greaterHeap.size() + 1) {
                    greaterHeap.offer(lessHeap.poll());
                }
            }
        }
    }

    public void addNum2(int num) {
        lessHeap.offer(num);
        greaterHeap.offer(lessHeap.poll());
        if (lessHeap.size() < greaterHeap.size()) {
            lessHeap.offer(greaterHeap.poll());
        }
    }

    public double findMedian() {
        if (lessHeap.size() == greaterHeap.size()) {
            return (double)(lessHeap.peek() + greaterHeap.peek()) / 2;
        } else {
            return lessHeap.peek();
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        medianFinder.addNum(3);
        medianFinder.addNum(0);
        medianFinder.addNum(4);
        medianFinder.addNum(1);
        double result = medianFinder.findMedian();
        System.out.println(result);
    }
}

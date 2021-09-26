package leetcode.offer;

import java.util.Deque;
import java.util.LinkedList;

class MovingAverage {

    int windowSize;
    int sum;
    Deque<Integer> deque;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        deque = new LinkedList<>();
        sum = 0;
        windowSize = size;
    }

    public double next(int val) {
        deque.offerLast(val);
        sum += val;
        if (deque.size() > windowSize) {
            sum -=  deque.removeFirst();
        }
        return (double)sum / deque.size();
    }

    public static void main(String[] args) {
        MovingAverage movingAverage = new MovingAverage(3);
        System.out.println(movingAverage.next(1));
        System.out.println(movingAverage.next(10));
        System.out.println(movingAverage.next(3));
        System.out.println(movingAverage.next(5));
        System.out.println(movingAverage.next(1));

    }
}

package leetcode.solutions;

/**
 * @author Vader Wang
 */
public class Solution295 {
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

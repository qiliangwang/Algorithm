package leetcode;

/**
 * @author Vader Wang
 */
public class Solution295 {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        double result = medianFinder.findMedian();
        System.out.println(result);
    }
}

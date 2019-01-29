package leetcode;

import java.util.HashSet;

/**
 *
 *
 Write an algorithm to determine if a number is "happy".

 A happy number is a number defined by the following process:
 Starting with any positive integer,
 replace the number by the sum of the squares of its digits,
 and repeat the process until the number equals 1 (where it will stay),
 or it loops endlessly in a cycle which does not include 1.
 Those numbers for which this process ends in 1 are happy numbers.

 Example:

 Input: 19
 Output: true
 Explanation:
 1^2 + 9^2 = 82
 8^2 + 2^2 = 68
 6^2 + 8^2 = 100
 1^2 + 0^2 + 0^2 = 1

 * @author Vader Wang
 */
public class Solution202 {

    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        int squareSum , temp;
        while (set.add(n)) {
            squareSum = 0;
            while (n != 0) {
                temp = (n % 10);
                squareSum += temp * temp;
                n = n / 10 ;
            }
            if (squareSum == 1) {
                return true;
            }
            n = squareSum;
        }
        return false;
    }

    public static void main(String[] args) {
        boolean result = new Solution202().isHappy(19);
        System.out.println(result);
    }
}

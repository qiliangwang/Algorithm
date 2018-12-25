package leetcode;

public class Solution69 {

    /**
     * Implement int sqrt(int x).
     *
     * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
     *
     * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
     *
     * Input: 4
     * Output: 2
     *
     * Input: 8
     * Output: 2
     * Explanation: The square root of 8 is 2.82842..., and since
     *              the decimal part is truncated, 2 is returned.
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int left = 0, right = x;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            //mid * mid > target ? right = mid left = mid
            if (mid * mid == x) return (int) mid;
            if (mid * mid > x) {
                right = (int)mid - 1;
            } else {
                left = (int)mid + 1;
            }
        }
        if (right * right < x) {
            return right;
        } else {
            return left;
        }
    }

    public static void main(String[] args) {
        int result = new Solution69().mySqrt(8);
        System.out.println(result);
    }
}

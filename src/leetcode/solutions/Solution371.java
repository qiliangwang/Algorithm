package leetcode.solutions;

public class Solution371 {

    /**
     * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

     Example:
     Given a = 1 and b = 2, return 3.

     time : O(1) / O(n)
     space : O(1)

     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;

        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }

        return a;
    }

    public static void main(String[] args) {
        int result = new Solution371().getSum(1, 2);
        System.out.println(result);
    }
}

package leetcode.solutions;

class Solution343 {
    private int[] memo;

    private int calcMax(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }
    private int calcIntegerBreak(int n) {
        memo[1] = 1;
        for (int i = 2; i <= n; i ++) {
            for (int j = 1; j <= i-1; j ++) {
                //j + (i - j)
                memo[i] = calcMax(j * memo[i - j], j * (i - j), memo[i]);
            }
        }
        return memo[n];
    }

    public int integerBreak(int n) {
        memo = new int[n + 1];
        return calcIntegerBreak(n);
    }

    public static void main(String[] args) {

        int result = new Solution343().integerBreak(10);
        System.out.println(result);
    }
}

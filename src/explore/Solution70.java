package explore;

public class Solution70 {
    private int[] memo;

    private int calcStairs(int n) {
        if (n == 1 || n == 0) {
            return 1;
        }
        if (memo[n] == 0) {
            memo[n] = calcStairs(n - 1) + calcStairs(n - 2);
        }
        return memo[n];
    }

    public int climbStairsRecursive(int n) {
        memo = new int[n + 1];
        return calcStairs(n);
    }

    public int climbStairs(int n) {
        int[] memo = new int[n + 1];
        memo[0] = 1;
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i-1] + memo[i -2];
        }
        return memo[n];
    }

    public static void main(String[] args) {
        int result = new Solution70().climbStairs(3);
        System.out.println(result);
    }
}

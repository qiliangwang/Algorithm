package leetcode;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.Arrays;

public class Solution322 {

    /**
     * You are given coins of different denominations and a total amount of money amount.
     * Write a function to compute the fewest number of coins that you need to make up that amount.
     * If that amount of money cannot be made up by any combination of the coins, return -1.
     *
     * Example 1:
     *
     * Input: coins = [1, 2, 5], amount = 11
     * Output: 3
     * Explanation: 11 = 5 + 5 + 1
     * Example 2:
     *
     * Input: coins = [2], amount = 3
     * Output: -1
     *
     * dp[i][j] = min(dp[i + 1][j], dp[i+1][j-coins[i]] + 1, dp[i+1][j-2*coins[i]] + 2, dp[i+1][3*coins[i]] + 3, ...)
     *
     * dp[i][j] = dp[i + 1][j] + dp[i][j - coins[i]] + 1;
     */

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = coins.length - 1; i >= 0; i --) {
            for (int j = 0; j <= amount; j ++) {
                if (j - coins[i] >= 0 ) {
                    if (dp[j - coins[i]] < Integer.MAX_VALUE) {
                        dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                    }
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {4, 3, 5};
        int result = new Solution322().coinChange(coins, 2);
        System.out.println(result);
    }
}

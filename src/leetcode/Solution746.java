package leetcode;

public class Solution746 {

    /**
     * Input: cost = [10, 15, 20]
     * Output: 15
     * Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
     *
     * Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
     * Output: 6
     * dp[n] = min(dp[n-1], dp[n-2]) + cost[n]
     * dp[1] = cost[1] = 1
     * dp[2] = cost[2] = 100
     * dp[3] = cost[3] + min(dp[1], dp[2]) = 2
     * dp[4] = cost[4] + min(dp[3], dp[2]) =
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i ++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }

    public static void main(String[] args) {
        int[] cost = {1, 100, 1};
        int result = new Solution746().minCostClimbingStairs(cost);
        System.out.println(result);
    }
}

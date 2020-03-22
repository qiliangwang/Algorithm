package leetcode.solutions;

import visualization.algo.Main;

/**
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the i-th job, you have to finish all the jobs j where 0 <= j < i).
 *
 * You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done in that day.
 *
 * Given an array of integers jobDifficulty and an integer d. The difficulty of the i-th job is jobDifficulty[i].
 *
 * Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
 *
 * Example 1:
 * Input: jobDifficulty = [6,5,4,3,2,1], d = 2
 * Output: 7
 * Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
 * Second day you can finish the last job, total difficulty = 1.
 * The difficulty of the schedule = 6 + 1 = 7
 *
 *
 * using dp to solve the problem
 * dp[i][k] means finish i jobs in k days in this case dp[6][2] is the solution &&
 * dp[0][0] means finish 0 jobs in 0 days obviously dp[0][0] is 0
 *
 * dp[1][1] = dp[0][0] + max(0, 1]
 * dp[2][1] = dp[0][0] + max(0, 2] || dp[1][1] + max(1, 2]
 * dp[2][2] = dp[1][1] + max(1, 2]
 * dp matrix:
 *
 * 0
 * C++ impl from huahua
 * // Author: Huahua
 * class Solution {
 * public:
 *   int minDifficulty(vector<int>& jobs, int d) {
 *     const int n = jobs.size();
 *     if (d > n) return -1;
 *     vector<vector<int>> dp(n + 1, vector<int>(d + 1, INT_MAX / 2));
 *
 *     dp[0][0] = 0;
 *     for (int i = 1; i <= n; ++i)
 *       for (int k = 1; k <= d; ++k) {
 *         int md = 0;
 *         for (int j = i - 1; j >= k - 1; --j) {
 *           md = max(md, jobs[j]);
 *           dp[i][k] = min(dp[i][k], dp[j][k - 1] + md);
 *         }
 *       }
 *
 *     return dp[n][d];
 *   }
 * };
 */
public class Solution1335 {

    public int minDifficulty(int[] jobDifficulty, int d) {

        int n = jobDifficulty.length;
        if (d > n) return -1;

        int[][] dp = new int[n + 1][d + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= n; i ++) {
            for (int k = 1; k <= d; k ++) {
                int md = 0;
                for (int j = i -1; j >=k; j --) {
                    md = Math.max(md, jobDifficulty[j]);
                    dp[j][k] = Math.min(dp[i][k], dp[j][k-1] + md);
                }
            }
        }
        return dp[n][d];
    }

    public static void main(String[] args) {
        int[] jobDifficulty = {1, 2};
        int d = 2;
        int result = new Solution1335().minDifficulty(jobDifficulty, d);
        System.out.println(result);
    }
}

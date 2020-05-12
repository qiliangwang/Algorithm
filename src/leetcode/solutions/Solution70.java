package leetcode.solutions;

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

  /**
   * 1 or 2 or 3（只能一次）
   * 一个数组dp存储这只能1 or 2
   *
   * 一个数组dp1存着1 or 2  0r 3（只能一次）
   *  从dp1[n] = dp[n - 1] + dp[n-2] + dp[n -3]
   *
   *  int f(int n, int k) {
   *
   *     if(n<2) return 1;
   *     if(n==2) return 2;
   *
   *     // dp is a 2D array of n+1 rows and k+1 cols
   *     // dp[i][j] stores the result f(i,j)
   *
   *     vector<vector<int> > dp(n+1, vector<int>(k+1,0));
   *
   *     for(int j=0; j<=k; j++){
   *         dp[0][j] = dp[1][j] = 1;
   *         dp[2][j] = 2;
   *     }
   *
   *     for(int i=3; i<=n; i++){
   *         for(int j=0; j<=k; j++){
   *
   *             dp[i][j] = dp[i-1][j] + dp[i-2][j];
   *             if(j>0) {
   *                 dp[i][j] += dp[i-3][j-1];
   *             }
   *         }
   *     }
   *
   *     return dp[n][k];
   * }
   *
   * Given n and k, we have three possibilities:
   *
   * First step is a 1-step. We can climb remaining in f(n-1,k) ways.
   * First step is a 2-step. We can climb remaining in f(n-2,k) ways.
   * First step is a 3-step. We can climb remaining in f(n-3,k-1) ways.
   * So, we obtain the recurrence f(n,k) = f(n-1,k) + f(n-2,k) + f(n-3,k-1) for all n>=3.
   *
   * The base cases will be f(0,j) = f(1,j) = 1 and f(2,j) = 2 for all 0<=j<=k.
   * @param n
   * @return
   */

  // A recursive function used by countWays
  public static int climbStairsPlus(int n, int k) {
    int[][] dp = new int[n + 1][k + 1];
    for (int j = 0; j <= k; j ++) {
      dp[0][j] = dp[1][j] = 1;
      dp[2][j] = 2;
    }

    for (int i = 3; i <= n; i++) {
      for (int j=0; j <=k; j ++) {
        dp[i][j] = dp[i - 1][j] + dp[i - 2][j];
        if (j > 0) {
          dp[i][j] += dp[i - 3][j - 1];
        }
      }
    }
      for (int i = 0; i <= n; i++) {
        for (int j=0; j <=k; j ++) {
          System.out.print(dp[i][j] + " ");
        }
        System.out.println("  " + i + " 台阶走法");
      }
    return dp[n][k];
  }

//  // Driver function
//  public static void main(String argc[])
//  {
//    int n = 4;
//    System.out.println(countWays(n));
//  }

  public static void main(String[] args) {
        int result = new Solution70().climbStairs(3);

    int i = climbStairsPlus(10, 1);
//    System.out.println(i);
//    System.out.println(result);
    }
}

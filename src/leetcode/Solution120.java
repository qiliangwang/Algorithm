package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution120 {

    /**
     * dp[i][j] : the minimum path sum from top to [i][j]
     * dp[i][j] = min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle[i][j]
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {

        int n = triangle.size();
        for (int i = 1; i < n; i++) {
            triangle.get(i).set(0, triangle.get(i).get(0) + triangle.get(i - 1).get(0));
            triangle.get(i).set(i, triangle.get(i).get(i) + triangle.get(i - 1).get(i - 1));
            for (int j = 1; j < i; j++) {
                int tempMinimum = triangle.get(i).get(j) + Math.min(triangle.get(i - 1).get(j), triangle.get(i - 1).get(j - 1));
                triangle.get(i).set(j, tempMinimum);
            }
        }
        //find minimum value
        int res = triangle.get(n - 1).get(0);
        for (int i = 1; i < triangle.get(n - 1).size(); i++) {
            if (res > triangle.get(n - 1).get(i)) res = triangle.get(n - 1).get(i);
        }
        return res;
    }

    /**
     [
     [2],
     [3,4],
     [6,5,7],
     [4,1,8,3]
     ]

     i : j
     i + 1 : j, j + 1

     res = [4, 1, 8, 3, 0]
     res = [7, 6, 10]
     res = [9, 10]
     res = [2]
     * @param triangle
     * @return
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }



    public static void main(String[] args) {
        ArrayList<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));
        System.out.println(triangle);
        int result = new Solution120().minimumTotal(triangle);
        System.out.println(result);
    }
}

package leetcode.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Solution435 {
  /**
   * 找到最小移除的区间数量 同时也是找到最多的区间
   * 这题用dp n*n
   * @param intervals
   * @return
   */
  public int eraseOverlapIntervals(int[][] intervals) {
    if (intervals.length == 0) {
      return 0;
    }
    //start 从小到大 （升序）
    Arrays.sort(intervals, (x1, x2) -> x1[0] - x2[0]);
    int[] dp = new int[intervals.length];
    Arrays.fill(dp, 1);
    for (int i = 0; i < intervals.length; i++) {
      for (int j = 0; j < i; j++) {
        if (intervals[i][0] >= intervals[j][1]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }
    int maxLength = 1;
    for (int i : dp) {
      maxLength = Math.max(maxLength, i);
    }
    return intervals.length - maxLength;
  }

  /**
   * 使用贪心来fix nlogn
   * @param intervals
   * @return
   */
  public int eraseOverlapIntervals2(int[][] intervals) {
    return 0;
  }

  public static void main(String[] args) {
//    int[][] intervals = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}};
//    int i = new Solution435().eraseOverlapIntervals(intervals);

    ArrayList<Integer> demo = new ArrayList<>();
    demo.add(1);
    demo.add(2);
    System.out.println(demo);
    Collections.shuffle(demo);
    System.out.println(demo);

  }
}

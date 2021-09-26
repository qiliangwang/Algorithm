package leetcode.solutions;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author wangql
 * @since 2021-09-17 12:27
 */
public class Solution57 {
  public int[][] insert(int[][] intervals, int[] newInterval) {
    int left = newInterval[0], right = newInterval[1];
    ArrayList<int[]> ans = new ArrayList<>();
    boolean placed = false;
    for (int[] interval : intervals) {
      if (interval[1] < left) {
        ans.add(interval);
      } else if (interval[0] > right) {
        if (!placed) {
          ans.add(new int[] {left, right});
          placed = true;
        }
        ans.add(interval);
      } else {
        left = Math.min(left, interval[0]);
        right = Math.max(right, interval[1]);
      }
    }
    if (!placed) {
      ans.add(new int[] {left, right});
    }

    return ans.toArray(new int[ans.size()][]);
  }
}

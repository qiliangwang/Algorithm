package leetcode.solutions;

import leetcode.base.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution56 {

    /**
     * Given a collection of intervals, merge all overlapping intervals.
     *
     * Example 1:
     *
     * Input: [[1,3],[2,6],[8,10],[15,18]]
     * Output: [[1,6],[8,10],[15,18]]
     * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
     * Example 2:
     *
     * Input: [[1,4],[4,5]]
     * Output: [[1,5]]
     * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
     * @param intervals
     * @return
     */
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) return intervals;
        Collections.sort(intervals, (a, b) -> a.start - b.start);
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        List<Interval> res = new ArrayList<>();
        for (Interval interval : intervals) {
            if (interval.start > end) {
               res.add(new Interval(start, end));
               start = interval.start;
               end = interval.end;
            } else {
                end = Math.max(end, interval.end);
            }
        }
        res.add(new Interval(start, end));
        return res;
    }









































    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (x1, x2) -> x1[0] - x2[0]);

        int start = intervals[0][0], end = intervals[0][1];
        ArrayList<int[]> ans = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[0] > end) {
                ans.add(new int[]{start, end});
                start = interval[0];
                end = interval[1];
            } else {
                end = Math.max(end, interval[1]);
            }
        }
        ans.add(new int[]{start, end});
        return ans.toArray(new int[ans.size()][]);
    }

    public static void main(String[] args) {
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(8, 10));
        intervals.add(new Interval(15, 18));
        List<Interval> result = new Solution56().merge(intervals);
        for (Interval interval : result) {
            System.out.print("[" +interval.start + "," + interval.end + "]");
        }
    }
}

package leetcode.solutions;

import leetcode.base.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Vader Wang
 */
public class Solution253 {

    /**
     * @return
     */
    public int minMeetingRooms(Interval[] intervals) {

      int n=intervals.length;

      Arrays.sort(intervals, new Comparator<Interval>(){
        @Override
        public int compare(Interval a, Interval b) {
          return a.start - b.start;
        }
      });

      PriorityQueue<Integer> pq=new PriorityQueue<>();

      for (int i=0; i<n; i++) {
        if (i > 0 && intervals[i].start >= pq.peek()) {pq.poll();}
        pq.add(intervals[i].end);
      }

      return pq.size();

    }

  public static void main(String[] args) {

    Interval[] intervals = {new Interval(0, 30), new Interval(5, 10), new Interval(8, 10), new Interval(15, 20)};

    int result = new Solution253().minMeetingRooms(intervals);

    System.out.println(result);
    }
}

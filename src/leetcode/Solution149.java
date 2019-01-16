package leetcode;

import java.util.HashMap;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 *
 * Example 1:
 *
 * Input: [[1,1],[2,2],[3,3]]
 * Output: 3
 * Explanation:
 * ^
 * |
 * |        o
 * |     o
 * |  o
 * +------------->
 * 0  1  2  3  4
 * Example 2:
 *                 -           -     -     -     -        -
 * Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4],[4, 1], [3, 2]]
 * Output: 4
 * Explanation:
 * ^
 * |
 * |  o
 * |     o        o
 * |        o
 * |  o        o
 * +------------------->
 * 0  1  2  3  4  5  6
 *
 * @author Vader Wang
 */
public class Solution149 {

    public int maxPoints(Point[] points) {
        int res = 0;
        for (int i = 0; i < points.length; i ++) {
            HashMap<String, Integer> map = new HashMap<>();
            int dump = 1;
            int maxCount = 0;
            for (int j = i + 1; j < points.length; j ++) {
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    dump ++;
                    continue;
                }
                String slope = getSlope(points[i], points[j]);
                map.put(slope, map.getOrDefault(slope, 0) + 1);
                maxCount = Math.max(maxCount, map.get(slope));
            }
            res = Math.max(res, maxCount + dump);
        }
        return res;
    }

    private String getSlope(Point point1, Point point2) {
        int dy = point2.y - point1.y;
        int dx = point2.x - point1.x;
        if (dx == 0) {
            return "INF";
        }
        if (dy == 0) {
            return "Zero";
        }
        int d = gcd(dy, dx);
        return dy / d + " / " + dx / d;
    }

    private int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }

    public static void main(String[] args) {
        Point[] points = new Point[] {new Point(1, 1), new Point(3, 2), new Point(5, 3),
                new Point(4, 1), new Point(2, 3), new Point(1, 4), new Point(4, 1),
                new Point(3, 2)};

        Point[] points2 = new Point[] {new Point(0, 0), new Point(0, 0)};
        int result = new Solution149().maxPoints(points);
        System.out.println(result);
    }
}

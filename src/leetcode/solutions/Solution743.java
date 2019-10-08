package leetcode.solutions;

/**
 * There are N network nodes, labelled 1 to N.
 *
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node,
 * v is the target node, and w is the time it takes for a signal to travel from source to target.
 *
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal?
 * If it is impossible, return -1.
 *
 * Note:
 *
 * N will be in the range [1, 100].
 * K will be in the range [1, N].
 * The length of times will be in the range [1, 6000].
 * All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100.
 *
 *
 * Accepted
 * 23,179
 * Submissions
 * 58,901
 * @author Vader Wang
 */
public class Solution743 {

    public int networkDelayTime(int[][] times, int N, int K) {
        return 0;
    }

    public static void main(String[] args) {
        int result = new Solution743().networkDelayTime(null, 0, 0);
        System.out.println(result);
    }
}

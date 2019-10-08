package leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vader Wang
 */
public class Solution118 {

    /**
     * In Pascal's triangle, each number is the sum of the two numbers directly above it.
     *
     * Example:
     *
     * Input: 5
     * Output:
     * [
     *      [1],
     *     [1,1],
     *    [1,2,1],
     *   [1,3,3,1],
     *  [1,4,6,4,1]
     * ]
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> row = new ArrayList<>();
        for (int i = 0; i < numRows; i ++) {
            row.add(0,1);
            for (int j = 1; j < row.size() - 1; j ++) {
                row.set(j, row.get(j) + row.get(j + 1));
            }
            res.add(new ArrayList<>(row));
        }
        return res;
    }


    public static void main(String[] args) {
        List<List<Integer>> result = new Solution118().generate(5);
        System.out.println(result);
    }
}

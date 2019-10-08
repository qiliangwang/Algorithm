package leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

public class Solution54 {

    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();

        if (matrix == null || matrix.length <= 0 || matrix[0].length <=0) return res;

        int rowBegin = 0, rowEnd = matrix.length - 1, colBegin = 0, colEnd = matrix[0].length - 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            for (int i = colBegin; i <= colEnd; i ++) {
                res.add(matrix[rowBegin][i]);
            }
            rowBegin ++;

            for (int i = rowBegin; i <= rowEnd; i ++) {
                res.add(matrix[i][colEnd]);
            }
            colEnd --;

            if (rowBegin <= rowEnd) {
                for (int i = colEnd; i >= colBegin; i --) {
                    res.add(matrix[rowEnd][i]);
                }
            }
            rowEnd --;

            if (colBegin <= colEnd) {
                for (int i = rowEnd; i >= rowBegin; i --) {
                    res.add(matrix[i][colBegin]);
                }
            }
            colBegin ++;
        }
        return res;
    }


    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};

        List<Integer> result = new Solution54().spiralOrder(matrix);
        System.out.println(result);
    }
}

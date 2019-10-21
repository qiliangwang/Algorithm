package leetcode.solutions;

/**
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * Example:
 *
 * Consider the following matrix:
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 *
 * Given target = 20, return false.
 *
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 *
 * @author wangql
 */
public class Solution240_2 {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        return doSearchMatrix(matrix, target);
    }

    public boolean doSearchMatrix(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length - 1;

        while (col >= 0 && row <= matrix.length - 1) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            }else {
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {

//        int[][] matrix = {
//                {1,   4,  7, 11, 15},
//                {2,   5,  8, 12, 19},
//                {3,   6,  9, 16, 22},
//                {10, 13, 14, 17, 24},
//                {18, 21, 23, 26, 30}};

//        [[1,3,5,7,9],[2,4,6,8,10],[11,13,15,17,19],[12,14,16,18,20],[21,22,23,24,25]]

//        int[][] matrix = {{1, 1}};
        int[][] matrix = {};

        boolean res = new Solution240_2().searchMatrix(matrix, 2);
        System.out.println(res);
    }

}

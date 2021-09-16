package leetcode.solutions;

import java.util.List;

public class Solution59 {

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int left = 0, right = n -1, top = 0, button = n - 1, num = 1;
        while (left <= right && top <= button) {
            //top
            for (int col = left; col <= right; col ++) {
                matrix[top][col] = num;
                num ++;
            }
            //right
            for (int row = top + 1; row <= button; row ++) {
                matrix[row][right] = num;
                num ++;
            }
            if (left < right && top < button) {
                //button
                for (int col = right -1; col > left; col --) {
                    matrix[button][col] = num;
                    num ++;
                }
                //left
                for (int row = button; row > top; row --) {
                    matrix[row][left] = num;
                    num ++;
                }
            }
            left ++;
            right --;
            top ++;
            button --;
        }
        return matrix;
    }

    public static void main(String[] args) {


        int[][] matrix = new Solution59().generateMatrix(3);
        System.out.println(matrix);

    }
}

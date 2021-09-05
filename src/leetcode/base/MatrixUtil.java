package leetcode.base;

public class MatrixUtil {

    public static void printMatrix(char[][] board) {
        int m = board.length - 1;
        int n = board[0].length - 1;
        for (int i = 0; i <= m; i ++) {
            for (int j = 0; j <= n; j ++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

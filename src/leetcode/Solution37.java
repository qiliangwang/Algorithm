package leetcode;

import java.util.HashSet;

public class Solution37 {


    /**
     Write a program to solve a Sudoku puzzle by filling the empty cells.

     A sudoku solution must satisfy all of the following rules:

     Each of the digits 1-9 must occur exactly once in each row.
     Each of the digits 1-9 must occur exactly once in each column.
     Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
     Empty cells are indicated by the character '.'.
     */
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        solve(board);
    }

    private boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                if (board[i][j] != '.') continue;

                for (char c = '1'; c <= '9'; c++) {
                    if (isValid(board, i, j, c)) {
                        board[i][j] = c;
                        if (solve(board)) return true;
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i ++) {
            if (board[row][i] != '.' && board[row][i] == c) return false;
            if (board[i][col] != '.' && board[i][col] == c) return false;
//            int cubeX = 3 * (row / 3) + i / 3;
//            int cubeY = 3 * (col / 3) + i / 3;
//            if (board[cubeX][cubeY] != '.' && board[cubeX][cubeY] == c) return false;
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i / 3] != '.'
                    && board[3 * (row / 3) + i / 3][3 * (col / 3) + i / 3] == c) {
                return false;
            }
        }
        return true;
    }

    private static void printSudoku(char[][] board) {
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        new Solution37().solveSudoku(board);

        printSudoku(board);
    }
}
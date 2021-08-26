package leetcode.solutions;

import java.util.HashSet;

public class Solution36 {

  private static volatile Solution36 INSTENCE;

  public static Solution36 getInstance() {
    if (INSTENCE == null) {
      synchronized (Solution36.class) {
        if (INSTENCE == null) {
          INSTENCE = new Solution36();
        }
      }
    }
    return INSTENCE;
  }

  static {
    System.out.println("static java block");
  }

  {
    System.out.println("java block");
  }
  private Solution36() {
    System.out.println("constructor");
  }

  /**
     * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
     *
     * Each row must contain the digits 1-9 without repetition.
     * Each column must contain the digits 1-9 without repetition.
     * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
     *
     * [
     *   ["5","3",".",".","7",".",".",".","."],
     *   ["6",".",".","1","9","5",".",".","."],
     *   [".","9","8",".",".",".",".","6","."],
     *   ["8",".",".",".","6",".",".",".","3"],
     *   ["4",".",".","8",".","3",".",".","1"],
     *   ["7",".",".",".","2",".",".",".","6"],
     *   [".","6",".",".",".",".","2","8","."],
     *   [".",".",".","4","1","9",".",".","5"],
     *   [".",".",".",".","8",".",".","7","9"]
     * ]
     *
     * true
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < board.length; i ++) {
            HashSet<Character> row = new HashSet<>();
            HashSet<Character> column = new HashSet<>();
            HashSet<Character> cube = new HashSet<>();
            for (int j = 0; j < board[0].length; j ++) {
                // row valid ,row.add() is false means repeat
                if (board[i][j] != '.' && !row.add(board[i][j]))return false;
                // column valid
                if (board[j][i] != '.' && !column.add(board[j][i])) return false;
                //cube valid
                System.out.println(i + " " + j + ":" + calRowIndex(i, j) + " " + calColIndex(i, j));
                if (board[calRowIndex(i, j)][calColIndex(i, j)] != '.' && !cube.add(board[calRowIndex(i, j)][calColIndex(i, j)])) return false;
            }
        }
        return true;
    }

    private int calRowIndex(int i, int j) {
        int startRow = 3 * (i / 3); // 0, 0, 0, 3, 3, 3, 6, 6, 6
        int cubeRow = j / 3; // 0, 0, 0, 1, 1, 1, 2, 2, 2
        return startRow + cubeRow;
    }

    private int calColIndex(int i, int j) {
        int startCol = 3 * (i % 3); // 0, 3, 6, 0, 3, 6, 0, 3, 6
        int cubeCol = j % 3; // 0, 1, 2, 0, 1, 2, 0, 1, 2
        return startCol + cubeCol;
    }


    public static void main(String[] args) {

//      Solution36 solution36 = new Solution36();

//      char[][] board = {
//                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
//                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
//                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
//                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
//                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
//                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
//                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
//                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
//                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
//        boolean result = new Solution36().isValidSudoku(board);
//
//        System.out.println(result);
    }
}
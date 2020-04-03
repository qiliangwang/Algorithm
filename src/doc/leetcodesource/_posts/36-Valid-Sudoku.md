---
title: 36 Valid Sudoku
date: 2019-02-18 10:24:42
tags:
---

## Valid Sudoku

Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated **according to the following rules**:

1. Each row must contain the digits `1-9` without repetition.
2. Each column must contain the digits `1-9` without repetition.
3. Each of the 9 `3x3` sub-boxes of the grid must contain the digits `1-9` without repetition.

![img](https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png)
A partially filled sudoku which is valid.

The Sudoku board could be partially filled, where empty cells are filled with the character `'.'`.

**Example 1:**

```
Input:
[
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: true
```

**Example 2:**

```
Input:
[
  ["8","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being 
    modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
```

**Note:**

- A Sudoku board (partially filled) could be valid but is not necessarily solvable.
- Only the filled cells need to be validated according to the mentioned rules.
- The given board contain only digits `1-9` and the character `'.'`.
- The given board size is always `9x9`.

## solution

```java
class Solution {
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
}
```


---
title: 51 N-Queens
date: 2019-02-18 13:20:58
tags:
---

## N-Queens

The *n*-queens puzzle is the problem of placing *n* queens on an *n*×*n* chessboard such that no two queens attack each other.

![img](https://assets.leetcode.com/uploads/2018/10/12/8-queens.png)

Given an integer *n*, return all distinct solutions to the *n*-queens puzzle.

Each solution contains a distinct board configuration of the *n*-queens' placement, where `'Q'` and `'.'` both indicate a queen and an empty space respectively.

**Example:**

```
Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
```

solution

```c++
class Solution {
private:

    vector<vector<string>> res;
    vector<bool> col, dia1, dia2;

    void putQueen(int n, int index, vector<int> &row){

        if (index == n){
            res.push_back(generateBoard(n, row));
            return;
        }

        for (int i = 0; i < n; i++) {
            if(!col[i] && !dia1[index+i] && !dia2[index-i+n-1]){
                row.push_back(i);
                col[i] = true;
                dia1[index+i] = true;
                dia2[index-i+n-1] = true;
                putQueen(n, index+1, row);
                col[i] = false;
                dia1[index+i] = false;
                dia2[index-i+n-1] = false;
                row.pop_back();
            }
        }
        return;
    }

    vector<string> generateBoard(int n, vector<int> &row){
        vector<string> board(n, string(n, '.'));
        for (int i = 0; i < n; i++) {
            board[i][row[i]] = 'Q';
        }
        return board;
    }
public:
    vector<vector<string>> solveNQueens(int n) {
        res.clear();

        col = vector<bool>(n, false);
        dia1 = vector<bool>(2*n-1, false);
        dia2 = vector<bool>(2*n-1, false);

        vector<int> row;
        putQueen(n, 0, row);
        return res;
    }
    
    
};
```


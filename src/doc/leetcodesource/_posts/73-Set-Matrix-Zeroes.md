---
title: 73. Set Matrix Zeroes
date: 2019-02-18 15:31:12
tags:
---

## Set Matrix Zeroes

Given a *m* x *n* matrix, if an element is 0, set its entire row and column to 0. Do it [**in-place**](https://en.wikipedia.org/wiki/In-place_algorithm).

**Example 1:**

```
Input: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
```

**Example 2:**

```
Input: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
Output: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
```

**Follow up:**

- A straight forward solution using O(*m**n*) space is probably a bad idea.
- A simple improvement uses O(*m* + *n*) space, but still not the best solution.
- Could you devise a constant space solution?

solution

```java
class Solution {
    public void setZeroes(int[][] matrix) {

        int m = matrix.length, n =matrix[0].length;
        boolean row = false, col = false;

        // init the matrix
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] =0;
                    if (i == 0) row = true;
                    if (j == 0) col = true;
                }
            }
        }

        //scan first col set zero start row to zeros
        for (int i = 1; i < m; i ++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < n; j ++) {
                    matrix[i][j] = 0;
                }
            }
        }
        //scan first row set zero start col to zeros
        for (int j = 1; j < n; j ++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < m; i ++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (row) {
            for (int j = 0; j < n; j ++) {
                matrix[0][j] = 0;
            }
        }
        if (col) {
            for (int i = 0; i < m; i ++) {
                matrix[i][0] = 0;
            }
        }

    }
}
```


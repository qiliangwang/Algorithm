package leetcode.solutions;

public class Solution48 {
    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length; i ++) {
            for (int j = i + 1; j < matrix[0].length; j ++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[0].length / 2; j ++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix[0].length - 1 - j];
                matrix[i][matrix[0].length - 1 - j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Solution48 solution = new Solution48();

//        for (int i = 0; i < matrix.length; i ++) {
//            for (int j = 0; j < matrix[0].length; j ++) {
//                System.out.print(matrix[i][j]);
//            }
//            System.out.println();
//        }

        solution.rotate(matrix);

        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[0].length; j ++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
//        for (List<Integer> res: solution.results) {
//            System.out.println(res);
//        }
    }
}
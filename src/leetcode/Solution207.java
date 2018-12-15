package leetcode;

import java.util.ArrayList;
import java.util.List;

class Solution207 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return false;
    }


    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};

        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}, {0, 1}};

        boolean result = new Solution207().canFinish(numCourses, prerequisites);
        System.out.print(result);
    }
}

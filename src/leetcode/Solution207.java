package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution207 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        int result = numCourses;
        //cal into the degree
        for (int[] pair : prerequisites) {
            indegree[pair[0]]++;
        }
        //find node where into the degree is zero
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0)
                queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int pre = queue.poll();
            result--;
            for (int[] pair : prerequisites) {
                if (pair[1] == pre) {
                    indegree[pair[0]]--;
                    if (indegree[pair[0]] == 0)
                        queue.offer(pair[0]);
                }
            }
        }
        return result == 0;
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

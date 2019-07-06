package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution210 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        int[] res = new int[numCourses];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            inDegree[prerequisite[0]] ++;
            if (graph.containsKey(prerequisite[1])) {
                graph.get(prerequisite[1]).add(prerequisite[0]);
            } else {
                List<Integer> courses = new ArrayList<>();
                courses.add(prerequisite[0]);
                graph.put(prerequisite[1], courses);
            }
        }
        //get results
        int first = 0, last = 0;
        for (int i = 0; i < numCourses; i ++) {
            if (inDegree[i] == 0) res[last++] = i;
        }
        while (first < last) {
            List<Integer> subCourses = graph.get(res[first]);
            if (subCourses != null) {
                for (int sub : subCourses) {
                    if (--inDegree[sub] == 0) res[last++] = sub;
                }
            }
            first++;
        }
        if (last != numCourses) return new int[0];
        return res;
    }

    public static void main(String[] args) {
        int[][] prerequisites = new int[][] {{1, 0}};
        int[] result = new Solution210().findOrder(2, prerequisites);
        System.out.println(result);
    }
}

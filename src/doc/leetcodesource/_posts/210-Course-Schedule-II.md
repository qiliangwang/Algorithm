---
title: 210. Course Schedule II
date: 2019-03-27 09:46:26
tags:
---

https://leetcode.com/problems/course-schedule-ii/

There are a total of *n* courses you have to take, labeled from `0` to `n-1`.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: `[0,1]`

Given the total number of courses and a list of prerequisite **pairs**, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

**Example 1:**

```
Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .
```

**Example 2:**

```
Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
```

**Note:**

1. The input prerequisites is a graph represented by **a list of edges**, not adjacency matrices. Read more about [how a graph is represented](https://www.khanacademy.org/computing/computer-science/algorithms/graph-representation/a/representing-graphs).
2. You may assume that there are no duplicate edges in the input prerequisites.

## Solution

```java
class Solution {
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
}
```


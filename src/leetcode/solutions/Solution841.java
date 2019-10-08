package leetcode.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution841 {

    private boolean[] visited;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        visited = new boolean[rooms.size()];
        return dfs(rooms, 0) == rooms.size();
    }

    private int dfs(List<List<Integer>> rooms, int v) {
        visited[v] = true;
        int res = 1;
        for (int next: rooms.get(v)) {
            if (!visited[next]) {
                res += dfs(rooms, next);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(Arrays.asList(1));
        rooms.add(Arrays.asList(2));
        rooms.add(Arrays.asList(3));
        rooms.add(new ArrayList<>());
        boolean result = new Solution841().canVisitAllRooms(rooms);
        System.out.println(result);
    }
}

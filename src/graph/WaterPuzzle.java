package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class WaterPuzzle {

    private int[] pre;
    private int end = -1;

    public WaterPuzzle(){

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[100];
        pre = new int[100];

        queue.add(0);
        visited[0] = true;
        while (!queue.isEmpty()){
            int cur = queue.remove();
            int a = cur / 10, b = cur % 10;
            // max a = 5, max b = 3

            ArrayList<Integer> nexts = new ArrayList<>();
            //6种状态
            //将A灌满水
            nexts.add(5 * 10 + b);
            //B灌满水
            nexts.add(a * 10 + 3);
            //倒掉A桶
            nexts.add(a * 10 + 0);
            //倒掉B桶
            nexts.add(0 * 10 + b);
            //A倒入B
            int x = Math.min(a, 3 - b);
            nexts.add((a - x) * 10 + (b + x));
            //B倒入A
            int y = Math.min(b, 5 - a);
            nexts.add((a + y) * 10 + (b - y));
            //

            for(int next: nexts)
                if(!visited[next]){
                    queue.add(next);
                    visited[next] = true;
                    pre[next] = cur;

                    if(next / 10 == 4 || next % 10 == 4) {
                        end = next;
                        return;
                    }
                }
        }
    }

    public Iterable<Integer> result(){

        ArrayList<Integer> res = new ArrayList<>();
        if(end == -1) return res;

        int cur = end;
        while(cur != 0){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args){
        System.out.println((new WaterPuzzle()).result());
    }
}

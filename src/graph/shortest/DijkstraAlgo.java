package graph.shortest;

import graph.basics.WeightedGraph;

import java.util.Arrays;

public class DijkstraAlgo {

    private WeightedGraph G;
    private int s;
    private int[] dis;
    private boolean[] visited;

    public DijkstraAlgo(WeightedGraph G, int s){

        this.G = G;

        G.validateVertex(s);
        this.s = s;


        // init
        int v = G.V();
        dis = new int[v];
        visited = new boolean[v];
        Arrays.fill(dis, Integer.MAX_VALUE);
        //dis[s] = 0
        dis[s] = 0;

        while (true) {
            int curDis = Integer.MAX_VALUE;
            int cur = -1;
            for (int i =0; i < v; i ++) {
                if (!visited[i] && curDis > dis[i]) {
                    curDis = dis[i];
                    cur = i;
                }
            }

            if (cur == -1) {
                break;
            }

            visited[cur] = true;
            for (int i : G.adj(cur)) {
                if (!visited[i]) {
                    if (dis[cur] + G.getWeight(cur, i) < dis[i]) {
                        dis[i] = dis[cur] + G.getWeight(cur, i);
                    }
                }
            }

        }
    }

    public boolean isConnectedTo(int v){

        G.validateVertex(v);
        return visited[v];
    }

    public int distTo(int v){

        G.validateVertex(v);
        return dis[v];
    }

    static public void main(String[] args){

        WeightedGraph g = new WeightedGraph("g4.txt");
        DijkstraAlgo dij = new DijkstraAlgo(g, 0);
        for(int v = 0; v < g.V(); v ++)
            System.out.print(dij.distTo(v) + " ");
        System.out.println();
    }
}

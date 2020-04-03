---
title: Graph One
date: 2018-09-18 22:41:54
tags:
---

## 如何表现图

我们需要用一种数据结构来表现图，那么什么样的数据结构适合表现图呢？

关于图的表现，有2种方式一种是邻接表另一种是邻接矩阵

首先我们说说邻接表，对于一个图而言，其本质其实是节点和边的关系。所以我们只需要可以表示出节点和边的关系就好了。对于邻接表（Adjacency Lists）与某一个节点相邻的节点用一个List来表示，如下面所示：与0相邻的有 1，2，5，6这四个节点。

```
vertex 0:       1       2       5       6
vertex 1:       0
vertex 2:       0
vertex 3:       4       5
vertex 4:       3       5       6
vertex 5:       0       3       4
vertex 6:       0       4

```

接下来我们看邻接矩阵（Adjacency Matrix），邻接矩阵也是表示节点与边的关系，邻接矩阵很容易理解，想知道0和1这2个节点是否相连只需要看Matrix的第0行和第2列的位置上的值是不是1。在这里我们可以发现第0行第2列的位置为1所以节点0和节点2是相邻的。

```
0       1       1       0       0       1       1
1       0       0       0       0       0       0
1       0       0       0       0       0       0
0       0       0       0       1       1       0
0       0       0       1       0       1       1
1       0       0       1       1       0       0
1       0       0       0       1       0       0
```

## 稀疏图和稠密图

既然可以用2种不同的数据结构来表示图，那么这2种不同的数据结构有什么优缺点呢。

对于邻接表，由于只需要表示边的关系，也就是说当边越少的时候，邻接表需要的空间是越少的，而对于邻接矩阵而言，不管有多少条边，空间都是一样的。所以邻接表适合表示稀疏图，而邻接矩阵适合表示稠密图。

话不多说用code来实现图。

我们用n，m分别表示图的点数和边数，用一个名为directed的变量来表示是有向图还是无向图。对于图而言，构造方法需要传入参数n代表有n个节点。用一个for循环创造一个n x n的矩阵。

在这里我们还实现了一个迭代器，对于这个迭代器，传入某个节点迭代出相邻的全部节点。

```c++
class DenseGraph{

private:
    int n, m;
    bool directed;
    vector<vector<bool>> g;

public:
    DenseGraph(int n, bool directed){
        this->n = n;
        this->m = 0;
        this->directed = directed;
        for (int i = 0; i < n; ++i) {
            g.push_back(vector<bool>(n, false));
        }
    }

    ~DenseGraph(){

    }

    int V(){ return n;}

    int E(){ return m;}

    bool hasEdge(int v, int w){
        assert( v >= 0 && v < n);
        assert( w >= 0 && w < n);
        return g[v][w];
    }

    void addEdge(int v, int w){
        assert( v >= 0 && v < n);
        assert( w >= 0 && w < n);
        if (hasEdge(v, w)){
            return;
        }
        g[v][w] = true;
        if (!directed)
            g[w][v] = true;
        m ++;
    }

    void show(){

        for( int i = 0 ; i < n ; i ++ ){
            for( int j = 0 ; j < n ; j ++ )
                cout<<g[i][j]<<"\t";
            cout<<endl;
        }
    }

    class adjIterator{

    private:
        DenseGraph &G;
        int v;
        int index;
    public:
        adjIterator(DenseGraph &graph, int v): G(graph){
            this->v = v;
            this->index = -1;
        }

        int begin(){
            index = -1;
            return next();
        }

        int next(){
            for (index += 1; index < G.V(); index ++) {
                if (G.g[v][index])
                    return index;
            }
            return -1;
        }

        bool end(){
            return index >= G.V();
        }
    };

};
```

接下来我们使用邻接表来实现图。

```c++
class SparseGraph{

private:
    int n, m;
    bool directed;
    vector<vector<int>> g;

public:
    SparseGraph(int n, bool directed){
        this->n = n;
        this->m = m;
        this->directed = directed;
        for (int i = 0; i < n; ++i) {
            g.push_back(vector<int>());
        }
    }
    
    ~SparseGraph(){

    }

    int V(){ return n;}

    int E(){ return m;}

    bool hasEdge(int v, int w){
        assert( v >= 0 && v < n);
        assert( w >= 0 && w < n);
        for (int i = 0; i < g[v].size(); i++) {
            if (g[v][i] == w)
                return true;
        }
        return false;
    }

    void addEdge(int v, int w){
        assert( v >= 0 && v < n);
        assert( w >= 0 && w < n);
        if (hasEdge(v, w)){
            return;
        }
        g[v].push_back(w);
        if (v != w && !directed)
            g[w].push_back(v);
        m++;
    }

    void show(){

        for( int i = 0 ; i < n ; i ++ ){
            cout<<"vertex "<<i<<":\t";
            for( int j = 0 ; j < g[i].size() ; j ++ )
                cout<<g[i][j]<<"\t";
            cout<<endl;
        }
    }

    class adjIterator{
        
    private:
        SparseGraph &G;
        int v;
        int index;
    public:
        adjIterator(SparseGraph &graph, int v): G(graph){
            this->v = v;
            this->index = 0;
        }

        int begin(){
            index = 0;
            if (G.g[v].size())
                return G.g[v][index];
            return -1;
        }

        int next(){
            index++;
            if (index < G.g[v].size())
                return G.g[v][index];
            return -1;
        }

        bool end(){
            return index >= G.g[v].size();
        }
    };

};
```

## 读取文件来构造图

既然图表示的是节点和边的关系，要是节点和边的数量比较多的话，构造一张图是一件非常困难的事情，所以这里我们通过读取文件来构造一张图。

```c++
class ReadGraph{

public:
    ReadGraph(Graph &graph, const string &filename){

        ifstream file(filename);
        string line;
        int V, E;

        assert( file.is_open() );
        assert( getline(file, line) );
        
        stringstream ss(line);
        ss>>V>>E;
        assert( V == graph.V() );

        for( int i = 0 ; i < E ; i ++ ){
            assert( getline(file, line) );
            stringstream ss(line);

            int a,b;
            ss>>a>>b;
            assert( a >= 0 && a < V );
            assert( b >= 0 && b < V );
            graph.addEdge( a , b );
        }
    }
    
};
```

## 深度优先遍历

```c++
void dfs(int v){
    visited[v] = true;
    typename Graph::adjIterator adjIter(G, v);
    for (int i = adjIter.begin(); ! adjIter.end(); i = adjIter.next()) {
        if (!visited[i])
            dfs(i);
    }
}
```

深度优先遍历的一个用途是可以用来求一张图里面的连通分量。

下面我们来实现如何求连通分量

```c++
class Component{

private:
    Graph &G;
    bool *visited;
    int ccount;
    int *id;

    void dfs(int v){
        visited[v] = true;
        id[v] = ccount;
        typename Graph::adjIterator adjIter(G, v);
        for (int i = adjIter.begin(); ! adjIter.end(); i = adjIter.next()) {
            if (!visited[i])
                dfs(i);
        }
    }

public:
    Component(Graph &graph): G(graph){

        visited = new bool[G.V()];
        id = new int[G.V()];
        ccount = 0;
        for( int i = 0 ; i < G.V() ; i ++ ){
            visited[i] = false;
            id[i] = -1;
        }

        for( int i = 0 ; i < G.V() ; i ++ )
            if( !visited[i] ){
                dfs(i);
                ccount ++;
            }
    }

    ~Component(){
        delete[] visited;
        delete[] id;
    }

    int count(){
        return ccount;
    }

    bool isConnected( int v , int w ){
        assert( v >= 0 && v < G.V() );
        assert( w >= 0 && w < G.V() );
        return id[v] == id[w];
    }

};
```

## 广度优先遍历

要实现广度优先遍历，我们需要使用一个队列，思路是先从队列里面取出一个元素，然后对这个元素相邻的元素进行判断，要是没有遍历的话，就加入队列中。重复这个操作就是广度优先遍历。

```c++
queue<int> q;

q.push(s);
visited[s] = true;

while (!q.empty()){

    int v = q.front();
    q.pop();

    typename Graph::adjIterator adj(G, v);
    for (int i = adj.begin(); !adj.end(); i = adj.next()){
        if (!visited[i]){
            q.push(i);
            visited[i] = true;
        }
    }
}
```

对于广度优先遍历，最基本的应用就是寻找无权图的最短路径。

```c++
class ShortestPath{
    Graph &G;
    int s;
    bool *visited;
    int *from;
    int *ord;

public:
    ShortestPath(Graph &graph, int s):G(graph){

        assert( s >= 0 && s < graph.V() );

        visited = new bool[graph.V()];
        from = new int[graph.V()];
        ord = new int[graph.V()];

        for( int i = 0 ; i < graph.V() ; i ++ ){
            visited[i] = false;
            from[i] = -1;
            ord[i] = -1;
        }
        this->s = s;

        queue<int> q;

        q.push(s);
        visited[s] = true;
        ord[s] = 0;
        while (!q.empty()){

            int v = q.front();
            q.pop();

            typename Graph::adjIterator adj(G, v);
            for (int i = adj.begin(); !adj.end(); i = adj.next()){
                if (!visited[i]){
                    q.push(i);
                    visited[i] = true;
                    from[i] = v;
                    ord[i] = ord[v] + 1;
                }
            }
        }

    }

    ~ShortestPath(){
        delete[] visited;
        delete[] from;
        delete[] ord;
    }

    bool hasPath(int w){
        assert( w >= 0 && w < G.V() );
        return visited[w];
    }

    void path(int w, vector<int> &vec){

        assert( w >= 0 && w < G.V() );

        stack<int> s;

        int p = w;
        while( p != -1 ){
            s.push(p);
            p = from[p];
        }

        vec.clear();
        while( !s.empty() ){
            vec.push_back( s.top() );
            s.pop();
        }
    }

    void showPath(int w){

        assert( w >= 0 && w < G.V() );

        vector<int> vec;
        path(w, vec);
        for( int i = 0 ; i < vec.size() ; i ++ ){
            cout<<vec[i];
            if( i == vec.size()-1 )
                cout<<endl;
            else
                cout<<" -> ";
        }
    }

    int length(int w){
        assert( w >= 0 && w < G.V() );
        return ord[w];
    }

};
```


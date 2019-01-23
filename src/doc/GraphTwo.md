---
title: Graph Two
date: 2018-09-19 00:55:01
tags:
---

## 带权图

之前我们的图都是没有权重的，而图的权重是很实用的一个功能。那么我们要如何实现带权重的图。其实也是很简单的。思路上邻接矩阵实现的图只需要把之前的bool变量换成表示权重的浮点型就好了，邻接表的存取的就是一个个键值对。

对应的邻接表就变成了

```
vertex 0: ( to:7,wt:0.16) ( to:4,wt:0.38) ( to:2,wt:0.26) ( to:6,wt:0.58)
vertex 1: ( to:2,wt:0.36) ( to:3,wt:0.29)
vertex 2: ( to:0,wt:0.26) ( to:1,wt:0.36) ( to:7,wt:0.34)
vertex 3: ( to:1,wt:0.29) ( to:6,wt:0.52)
vertex 4: ( to:5,wt:0.35) ( to:7,wt:0.37) ( to:0,wt:0.38) ( to:6,wt:0.93)
vertex 5: ( to:4,wt:0.35) ( to:7,wt:0.28)
vertex 6: ( to:3,wt:0.52) ( to:0,wt:0.58) ( to:4,wt:0.93)
vertex 7: ( to:4,wt:0.37) ( to:5,wt:0.28) ( to:0,wt:0.16) ( to:2,wt:0.34)
```

邻接矩阵就变成了

```
NULL    NULL    0.26    NULL    0.38    NULL    0.58    0.16
NULL    NULL    0.36    0.29    NULL    NULL    NULL    NULL
0.26    0.36    NULL    NULL    NULL    NULL    NULL    0.34
NULL    0.29    NULL    NULL    NULL    NULL    0.52    NULL
0.38    NULL    NULL    NULL    NULL    0.35    0.93    0.37
NULL    NULL    NULL    NULL    0.35    NULL    NULL    0.28
0.58    NULL    NULL    0.52    0.93    NULL    NULL    NULL
0.16    NULL    0.34    NULL    0.37    0.28    NULL    NULL
```

为了实现邻接表和邻接矩阵代码结构上面的统一，这里用一个Edge类来表示边。

```c++
template <typename Weight>
class Edge{
private:
    int a, b;
    Weight weight;
public:
    Edge(int a, int b, Weight weight){
        this->a = a;
        this->b = b;
        this->weight = weight;
    }

    Edge(){}

    ~Edge(){}

    int v(){ return a;}

    int w(){ return b;}

    Weight wt(){ return weight;}

    int other(int x){
        assert(x == a || x == b);
        return x == a ? b : a;
    }

    friend ostream& operator<<(ostream &os, const Edge &e){
        os<<e.a<<"-"<<e.b<<": "<<e.weight;
        return os;
    }

    bool operator<(Edge<Weight>& e){
        return weight < e.wt();
    }

    bool operator<=(Edge<Weight>& e){
        return weight <= e.wt();
    }

    bool operator>(Edge<Weight>& e){
        return weight > e.wt();
    }

    bool operator>=(Edge<Weight>& e){
        return weight >= e.wt();
    }

    bool operator==(Edge<Weight>& e){
        return weight == e.wt();
    }

};
```

接下来需要修改邻接表的实现，邻接矩阵以及文件读取的实现

```c++
template<typename Weight>
class SparseGraph{

private:
    int n, m;
    bool directed;
    vector<vector<Edge<Weight> *>> g;
public:
    SparseGraph( int n , bool directed){
        this->n = n;
        this->m = 0;
        this->directed = directed;
        for( int i = 0 ; i < n ; i ++ )
            g.push_back( vector<Edge<Weight> *>() );
    }

    ~SparseGraph(){

        for( int i = 0 ; i < n ; i ++ )
            for( int j = 0 ; j < g[i].size() ; j ++ )
                delete g[i][j];
    }

    int V(){ return n;}
    int E(){ return m;}

    void addEdge( int v, int w , Weight weight){
        assert( v >= 0 && v < n );
        assert( w >= 0 && w < n );

        g[v].push_back(new Edge<Weight>(v, w, weight));
        if( v != w && !directed )
            g[w].push_back(new Edge<Weight>(w, v, weight));
        m ++;
    }

    bool hasEdge( int v , int w ){
        assert( v >= 0 && v < n );
        assert( w >= 0 && w < n );
        for( int i = 0 ; i < g[v].size() ; i ++ )
            if( g[v][i]->other(v) == w )
                return true;
        return false;
    }

    void show(){

        for( int i = 0 ; i < n ; i ++ ){
            cout<<"vertex "<<i<<": ";
            for( int j = 0 ; j < g[i].size() ; j ++ )
                cout<<"( to:"<<g[i][j]->w()<<",wt:"<<g[i][j]->wt()<<") ";
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

        Edge<Weight>* begin(){
            index = 0;
            if( G.g[v].size() )
                return G.g[v][index];
            return NULL;
        }

        Edge<Weight>* next(){
            index += 1;
            if( index < G.g[v].size() )
                return G.g[v][index];
            return NULL;
        }

        bool end(){
            return index >= G.g[v].size();
        }
    };
};
```

```c++
template <typename Weight>
class DenseGraph{

private:
    int n, m;
    bool directed;
    vector<vector<Edge<Weight> *>> g;

public:
    DenseGraph( int n , bool directed){
        this->n = n;
        this->m = 0;
        this->directed = directed;
        for( int i = 0 ; i < n ; i ++ ){
            g.push_back( vector<Edge<Weight> *>(n,NULL) );
        }
    }

    ~DenseGraph(){

        for( int i = 0 ; i < n ; i ++ )
            for( int j = 0 ; j < n ; j ++ )
                if( g[i][j] != NULL )
                    delete g[i][j];
    }

    int V(){ return n;}
    int E(){ return m;}

    void addEdge( int v, int w , Weight weight ){
        assert( v >= 0 && v < n );
        assert( w >= 0 && w < n );

        if( hasEdge( v , w  ) ){
            delete  g[v][w];
            if( !directed )
                delete g[w][v];
            m --;
        }

        g[v][w] = new Edge<Weight>(v, w, weight);
        if( !directed )
            g[w][v] = new Edge<Weight>(w, v, weight);
        m ++;
    }

    bool hasEdge( int v , int w ){
        assert( v >= 0 && v < n );
        assert( w >= 0 && w < n );
        return g[v][w] != NULL;
    }

    void show(){

        for( int i = 0 ; i < n ; i ++ ){
            for( int j = 0 ; j < n ; j ++ )
                if( g[i][j] )
                    cout<<g[i][j]->wt()<<"\t";
                else
                    cout<<"NULL\t";
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

        Edge<Weight>* begin(){
            index = -1;
            return next();
        }

        Edge<Weight>* next(){
            for( index += 1 ; index < G.V() ; index ++ )
                if( G.g[v][index] )
                    return G.g[v][index];

            return NULL;
        }

        bool end(){
            return index >= G.V();
        }
    };
};
```

```c++
template <typename Graph, typename Weight>
class ReadGraph{

public:
    ReadGraph(Graph &graph, const string &filename){

        ifstream file(filename);
        string line;
        int V, E;

        assert(file.is_open());

        assert( getline(file,line));
        stringstream ss(line);
        ss>>V>>E;
        assert( graph.V() == V );

        for( int i = 0 ; i < E ; i ++ ){
            assert( getline(file,line));
            stringstream ss(line);

            int a, b;
            Weight w;
            ss>>a>>b>>w;
            assert( a >= 0 && a < V );
            assert( b >= 0 && b < V );
            graph.addEdge(a, b, w);
        }

    }
};
```

这样就成功表示了带权重的图。

## 最小生成树

对于带权图而言，最基本的算法是最小生成树，在生活中很多问题可以抽象为最小生成树。比如在部署电缆的过程中，使用最短的电缆链接全部的节点。

要说最小生成树，首先要说切分定理。切分定理说的是给定任意切分，横切边中权值最小的边必然属于最小生成树。 

根据切分定理，我们可以开始了解Prim算法，先看Lazy Prim。

## Lazy Prim

```c++

```

## Prim

```c++

```

## Kruskal

```c++

```






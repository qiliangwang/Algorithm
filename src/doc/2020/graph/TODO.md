最短路径

图的表示
图算法（leetcode）

二分图：染色算法，先将自己染成红色，然后把相邻的节点染成黑色。循环这个操作，如果运行到最后都ok则为2分图

最小生成树

Kruskal
切分定理 如果讲一个图变成2个切分，这2个切分之间的最短边肯定是最小生成树的一条边（反证法）

最短路径
狄杰斯特拉  利用DP的思想先选取一个最进的点（自己）然后选取这个点全部相邻边中最短的边（没有被visited的点），选取这个最近边的另一个顶点为下一个点，由于没有
负权边，A和其他点集合中最近的边的另一个点（B）肯定是最近的。 A->B === A -> xxx -> xxx -> B(前提条件是没有负权边)
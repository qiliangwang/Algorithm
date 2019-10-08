package leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

class Solution212 {

    private int direction[][] = {{-1,0},{0,1},{1,0},{0,-1}};
    private boolean[][] visited;
    private int m, n;

    class TrieNode {
        String word;
        TrieNode[] next = new TrieNode[26];
    }

    private boolean inArea( int x , int y ){
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word: words) {
            TrieNode p = root;
            for (char c: word.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) {
                    p.next[i] = new TrieNode();
                }
                p = p.next[i];
            }
            p.word = word;
        }
        return root;
    }

    public List<String> findWords(char[][] board, String[] words) {

        ArrayList<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];

        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }

    private void dfs(char[][] board, int x, int y, TrieNode node, ArrayList<String> res) {
        if (inArea(x, y)) {

            char c = board[x][y];

            if (node.next[c - 'a'] != null) {

                node = node.next[c - 'a'];

                if (node.word != null) {
                    res.add(node.word);
                    node.word = null;
                }
                visited[x][y] = true;

                for( int i = 0 ; i < 4 ; i ++ ){
                    int newX = x + direction[i][0];
                    int newY = y + direction[i][1];

                    if (inArea(newX, newY) && !visited[newX][newY]) {
                        dfs(board, newX, newY, node, res);
                    }
                }
                visited[x][y] = false;
            }
        }
    }


    public static void main(String[] args) {
        String[] words = {"oath", "pea", "eat", "rain"};
        char[][] board = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}};

        String[] words1 = {"a"};
        char[][] board1 = {
                {'a'}};
        List<String> result = new Solution212().findWords(board, words);
        for (String res: result) {
            System.out.print(res + " ");
        }

    }
}

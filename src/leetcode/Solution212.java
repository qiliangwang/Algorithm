package leetcode;

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

    public List<String> findWords(char[][] board, String[] words) {

        ArrayList<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);

        m = board.length;
        n = board[0].length;
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }

    private void dfs(char[][] board, int i, int j, TrieNode root, ArrayList<String> res) {

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

    public static void main(String[] args) {

        char[][] b1 = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};

        String words[] = {"ABCCED" , "SEE" , "ABCB" };
//        for( int i = 0 ; i < words.length ; i ++ )
//            if( (new Solution212()).exist(b1, words[i]))
//                System.out.println("found " + words[i]);
//            else
//                System.out.println("can not found " + words[i]);
//
//        // ---
//
//        char[][] b2 = {{'A'}};
//        if( (new Solution212()).exist(b2,"AB"))
//            System.out.println("found AB");
//        else
//            System.out.println("can not found AB");
    }
}

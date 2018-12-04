package backtracking;

class Solution212 {

    private int direction[][] = {{-1,0},{0,1},{1,0},{0,-1}};
    private boolean[][] visited;
    private int m, n;

    private boolean wordSearch(char[][] board, String word, int index, int startX, int startY) {
        if (index == word.length() - 1) {
            return board[startX][startY] == word.charAt(index);
        }
        if( board[startX][startY] == word.charAt(index) ){
            visited[startX][startY] = true;
            for( int i = 0 ; i < 4 ; i ++ ){
                int newX = startX + direction[i][0];
                int newY = startY + direction[i][1];
                if( inArea(newX, newY) && !visited[newX][newY] &&
                        wordSearch( board , word , index + 1 , newX , newY ) )
                    return true;
            }
            visited[startX][startY] = false;
        }
        return false;
    }

    private boolean inArea( int x , int y ){
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public boolean exist(char[][] board, String word) {

        m = board.length;
        n = board[0].length;

        visited = new boolean[m][n];

        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (wordSearch(board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {

        char[][] b1 = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};

        String words[] = {"ABCCED" , "SEE" , "ABCB" };
        for( int i = 0 ; i < words.length ; i ++ )
            if( (new Solution212()).exist(b1, words[i]))
                System.out.println("found " + words[i]);
            else
                System.out.println("can not found " + words[i]);

        // ---

        char[][] b2 = {{'A'}};
        if( (new Solution212()).exist(b2,"AB"))
            System.out.println("found AB");
        else
            System.out.println("can not found AB");
    }
}

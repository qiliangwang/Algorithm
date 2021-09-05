package leetcode.learn;

class WordDistence {

    public int minDistance(String word1, String word2) {
        //先定义一个二维的dp来表示各种结果 dp[I][J]代表了word[I]和word[j]的distence 按照定义word1进行操作和word2相同
        //dp[i][j] = 1相同 就是 dp[i-1][j-1] insert: dp[i-1][j] delete: dp[i][j-1] exchange: dp[i-1][j-1] + 1
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        //init
        for (int i = 0; i < word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < word2.length(); j ++) {
            dp[0][j] = j;
        }
//        for (int i = 1; i < word1.length(); i ++) {
//            for (int j = 1; j < word2.length(); j ++) {
//                if (word1.charAt(i) == word2.charAt(j)) {
//                    dp[i][j] = dp[i-1][j-1];
//                } else {
//                    dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j], dp[][]) + 1;
//                }
//            }
//        }
        return 0;
    }
}

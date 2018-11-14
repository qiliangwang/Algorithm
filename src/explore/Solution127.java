package explore;

import java.util.Arrays;
import java.util.List;

class Solution127 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        return 0;
    }


    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

        int result = new Solution127().ladderLength(beginWord, endWord, wordList);
        System.out.println(result);
    }
}

package leetcode;

import java.util.*;

class Solution126 {

    /**
     * 
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (wordList.size() == 0) return res;


        return res;
    }


    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

        List<List<String>> ladders = new Solution126().findLadders(beginWord, endWord, wordList);
//        System.out.println(result);
    }
}

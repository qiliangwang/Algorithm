package leetcode.solutions;

import java.util.*;

class Solution127 {

    /**
     * https://leetcode-cn.com/problems/word-ladder/description/
     * Input:
     * beginWord = "hit",
     * endWord = "cog",
     * wordList = ["hot","dot","dog","lot","log","cog"]
     *
     * Output: 5
     *
     * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     * return its length 5.
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>(wordList);

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int level = 1, curNum = 1, nextNum = 0;
        while (!queue.isEmpty()) {
            String word = queue.poll();
            curNum--;
            for (int i = 0; i < word.length(); i++) {
                char[] charArray = word.toCharArray();
                for (char j = 'a'; j <= 'z'; j++) {
                    charArray[i] = j;
                    String rebuildWord = new String(charArray);
                    if (set.contains(rebuildWord)) {
                        if (rebuildWord.equals(endWord)) {
                            return level + 1;
                        }
                        nextNum++;
                        queue.offer(rebuildWord);
                        set.remove(rebuildWord);
                    }

                }
            }
            //判断queue里面取出的是新的一层
            if (curNum == 0) {
                curNum = nextNum;
                nextNum = 0;
                level++;
            }
        }
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

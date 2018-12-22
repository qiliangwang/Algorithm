package leetcode;

import java.util.*;

public class Solution692 {

    /**
     * Given a non-empty list of words, return the k most frequent elements.
     *
     * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
     * Output: ["i", "love"]
     * @param words ["i", "love", "leetcode", "i", "love", "coding"]
     * @param k 2
     * @return ["i", "love"]
     */
    public List<String> topKFrequent(String[] words, int k) {
        //cal the freq of each word
        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }
        //a.getValue - b.getValue to get miniHeap (small -> large)
        PriorityQueue<Map.Entry<String, Integer>> maxHeap =
                new PriorityQueue<>((a, b) -> (a.getValue().equals(b.getValue()) ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue()));
        for (Map.Entry<String, Integer> entry: freq.entrySet()) {
            maxHeap.offer(entry);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        //fetch result from maxHeap
        ArrayList<String> res = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            res.add(maxHeap.poll().getKey());
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        List<String> result = new Solution692().topKFrequent(words, 2);
        System.out.println(result);
    }
}

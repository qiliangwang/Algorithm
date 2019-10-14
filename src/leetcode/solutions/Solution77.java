package leetcode.solutions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution77 {

    private List<List<Integer>> res;

    private void generateCombination(int start, int n, int k, LinkedList<Integer> combination) {
        if (combination.size() == k) {
            res.add((List<Integer>) combination.clone());
            return;
        }
        for (int i = start; i <= n; i ++) {
            combination.addLast(i);
            generateCombination(i+1, n, k, combination);
            combination.removeLast();
        }

    }
    public List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<List<Integer>>();
        LinkedList<Integer> combination = new LinkedList<Integer>();
        generateCombination(1, n, k, combination);
        return res;
    }

    private static void printList(List<Integer> list){
        for(Integer e: list)
            System.out.print(e + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("hello");
    }
}

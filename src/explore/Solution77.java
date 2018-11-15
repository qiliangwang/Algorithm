package explore;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution77 {

    private List<List<Integer>> res;

    private void generateCombinations(int n, int k, int start, LinkedList<Integer> c) {
        if (c.size() == k) {
            res.add((List<Integer>) c.clone());
            return;
        }
        for (int i= start; i <= n; i++) {
            c.addLast(i);
            generateCombinations(n, k, i + 1, c);
            c.removeLast();
        }
    }
    public List<List<Integer>> combine(int n, int k) {

        res = new ArrayList<List<Integer>>();
        if(n <= 0 || k <= 0 || k > n)
            return res;
        LinkedList<Integer> c = new LinkedList<Integer>();
        generateCombinations(n, k, 1, c);
        return res;
    }

    private static void printList(List<Integer> list){
        for(Integer e: list)
            System.out.print(e + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        List<List<Integer>> res = new Solution77().combine(4, 2);
        for(List<Integer> list: res)
            printList(list);
    }
}

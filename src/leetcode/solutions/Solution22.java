package leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

public class Solution22 {

    public List<String> generateParenthesis(int n) {
        ArrayList<String> results = new ArrayList<>();
        if (n == 0) return results;
        generateParenthesis(results, "", n, n);
        return results;
    }

    private void generateParenthesis(List<String> results, String result, int left, int right) {
        if (left > right) return;
        if (left == 0 && right == 0) {
            results.add(result);
            return;
        }
        if (left > 0) {
            generateParenthesis(results, result + "(", left - 1, right);
        }
        if (right > 0) {
            generateParenthesis(results, result + ")", left, right - 1);
        }
    }

    public static void main(String[] args) {
        List<String> result = new Solution22().generateParenthesis(3);
        System.out.println(result);
//        for (String s : result) {
//            System.out.println(s);
//        }
    }
}

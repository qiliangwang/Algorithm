package leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

public class Solution784 {

    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        findAll(res, s, "");
        return res;
    }

    private void findAll(List<String> res, String s, String tmp) {
        if (tmp.length() == s.length()) {
            res.add(tmp);
            return;
        }
        int startIdx = tmp.length();
        //isLetter
        if (!isDigit(s.charAt(startIdx))) {
            findAll(res, s, tmp + Character.toLowerCase(s.charAt(startIdx)));
            findAll(res, s, tmp + Character.toUpperCase(s.charAt(startIdx)));
        } else {
            findAll(res, s, tmp + s.charAt(startIdx));
        }
    }

    private Boolean isLetter(char c) {
        return c > 'a' && c < 'z' || c > 'A' && c < 'Z';
    }

    private Boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public static void main(String[] args) {
        List<String> res = new Solution784().letterCasePermutation("0");
        System.out.println(res);
    }
}

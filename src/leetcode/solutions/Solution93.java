package leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

public class Solution93 {

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        helper(res, s, "", 0,0);
        return res;
    }

    private void helper(List<String> res, String s, String current, int index, int count) {
        if (count > 4) return;
        if (count == 4 && index == s.length()) {
            res.add(current);
            return;
        }
        for (int i = 1; i <= 3; i ++) {
            if (index + i > s.length()) break;
            String temp = s.substring(index, index + i);
            if ((temp.startsWith("0") && temp.length() > 1) || (temp.length() == 3 && Integer.parseInt(temp) > 255)) continue;
            helper(res, s, current + temp + (count == 3 ? "" : "."), index + i, count + 1);
        }
    }

    public static void main(String[] args) {
        String s = "25525511135";
        List result = new Solution93().restoreIpAddresses(s);
        System.out.println(result);
    }
}

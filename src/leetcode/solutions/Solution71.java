package leetcode.solutions;

import java.util.Stack;

public class Solution71 {

    public String simplifyPath(String path) {
        String[] split = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String s : split) {
            if (s.equals("") || s.equals(".")) {
                continue;
            }
            if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(s);
            }
        }
        if (stack.isEmpty()) return "/";

        StringBuilder res = new StringBuilder();
        for (String s : stack) {
            res.append("/").append(s);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String result = new Solution71().simplifyPath("/a//b////c/d//././/..");
        System.out.println(result);
    }
}

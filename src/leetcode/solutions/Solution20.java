package leetcode.solutions;

import leetcode.learn.Solution;

import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution20 {

    static HashMap<Character, Character> pair = new HashMap();
    {
        pair.put('(', ')');
        pair.put('[', ']');
        pair.put('{', '}');

    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (!stack.isEmpty()  && isPair( stack.peek(), c)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean isPair(char c1, char c2) {
//        return pair.get(c1) == c2;
        if (c1 == '{') return c2 == '}';
        if (c1 == '[') return c2 == ']';
        if (c1 == '(') return c2 == ')';
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean valid = solution.isValid("()[]{}");
        System.out.println(valid);
    }
}

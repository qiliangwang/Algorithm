package leetcode.solutions;

import visualization.algo.Main;

import java.util.Stack;

class MinStack {

    Stack<Integer> stack;
    Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        //push mini
        if (minStack.isEmpty()) {
            minStack.push(val);
        } else {
            Integer peek = minStack.peek();
            int min = Math.min(peek, val);
            minStack.push(min);
        }
    }

    public void pop() {
        minStack.pop();
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

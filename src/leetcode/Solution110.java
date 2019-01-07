package leetcode;

public class Solution110 {

    /**
     * 110. Balanced Binary Tree
     * Easy
     *
     *Given a binary tree, determine if it is height-balanced.
     *
     * For this problem, a height-balanced binary tree is defined as:
     *
     * a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
     *
     * Example 1:
     *
     * Given the following tree [3,9,20,null,null,15,7]:
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * Return true.
     *
     * Example 2:
     *
     * Given the following tree [1,2,2,3,3,null,null,4,4]:
     *
     *        1
     *       / \
     *      2   2
     *     / \
     *    3   3
     *   / \
     *  4   4
     */
    public boolean isBalanced(TreeNode root) {
        return balance(root) != -1;
    }

    private int balance(TreeNode node) {
        if (node == null) return 0;
        int left = balance(node.left);
        int right = balance(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }


    public static void main(String[] args) {
        boolean result = new Solution110().isBalanced(null);
        System.out.println(result);
    }
}

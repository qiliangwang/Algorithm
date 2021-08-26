package leetcode.solutions;

import leetcode.base.TreeNode;

public class Solution543 {

    private int maxEdges = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        calMaxEdges(root);
        return maxEdges;
    }

    private int calMaxEdges(TreeNode root) {
        if (root == null) return 0;
        int leftMaxEdges = calMaxEdges(root.left);
        int rightMaxEdges = calMaxEdges(root.right);
        maxEdges = Math.max(maxEdges, leftMaxEdges + rightMaxEdges);
        return 1 + Math.max(leftMaxEdges, rightMaxEdges);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        int result = new Solution543().diameterOfBinaryTree(root);
        System.out.println(result);
    }
}

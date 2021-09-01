package leetcode.solutions;

import leetcode.base.TreeNode;

public class Solution98 {

    /**
     * Given a binary tree, determine if it is a valid binary search tree (BST).
     *
     * Assume a BST is defined as follows:
     *
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary search trees.
     * Example 1:
     *
     * Input:
     *     2
     *    / \
     *   1   3
     * Output: true
     * Example 2:
     *
     *     5
     *    / \
     *   1   4
     *      / \
     *     3   6
     * Output: false
     * Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
     *              is 5 but its right child's value is 4.
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {

        return isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValid(TreeNode node, Integer min, Integer max) {

        if (node == null) {return true;}
        if (node.val <= min || node.val >= max) {return false;}

        return isValid(node.left, min, node.val) && isValid(node.right, node.val, max);
    }


    public static void main(String[] args) {

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        boolean result = new Solution98().isValidBST(root2);
        System.out.println(result);
    }
}

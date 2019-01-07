package leetcode;

public class Solution124 {
    /**
     * 124. Binary Tree Maximum Path Sum
     * Hard
     *
     * Given a non-empty binary tree, find the maximum path sum.
     *
     * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
     * The path must contain at least one node and does not need to go through the root.
     *
     * Example 1:
     *
     * Input: [1,2,3]
     *
     *        1
     *       / \
     *      2   3
     *
     * Output: 6
     * Example 2:
     *
     * Input: [-10,9,20,null,null,15,7]
     *
     *    -10
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * Output: 42
     */
    private int res;

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        res = Integer.MIN_VALUE;
        pathSum(root);
        return res;
    }

    private int pathSum(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(pathSum(root.left), 0);
        int right = Math.max(pathSum(root.right), 0);
        res = Math.max(res, left + right + root.val);
        return Math.max(left, right) + root.val;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        int result = new Solution124().maxPathSum(root);
        System.out.println(result);
    }
}

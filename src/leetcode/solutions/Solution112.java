package leetcode.solutions;

import leetcode.base.TreeNode;

public class Solution112 {

    public boolean hasPathSum(TreeNode root, int sum) {
        return pathSum(root, sum);
    }

    private boolean pathSum(TreeNode node, int sum) {
        if (node == null) {
            return false;
        }
        //leaf node
        if (node.left == null && node.right == null)
            return node.val == sum;
        return pathSum(node.left, sum - node.val) || pathSum(node.right, sum - node.val);
    }

    public static void main(String[] args) {

    }
}

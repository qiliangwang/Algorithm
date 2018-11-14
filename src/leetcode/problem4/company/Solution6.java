package leetcode.problem4.company;

public class Solution6 {
    public boolean hasPathSum(TreeNode root, int sum) {
        return pathSum(root, sum);
    }

    private boolean pathSum(TreeNode node, int sum) {
        //leaf node
        if (node == null) {
            return false;
        }
        if (node.left == null && node.right == null)
            return node.val == sum;
        return pathSum(node.left, sum - node.val) || pathSum(node.right, sum - node.val);

    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(4);
        treeNode.right = new TreeNode(8);
        treeNode.right.left = new TreeNode(13);
        treeNode.right.right = new TreeNode(4);
        treeNode.right.right.right = new TreeNode(1);
        treeNode.left.left = new TreeNode(11);
        treeNode.left.left.left = new TreeNode(7);
        treeNode.left.left.right = new TreeNode(2);
        boolean result = new Solution6().hasPathSum(treeNode, 22);
        System.out.println(result);
    }
}

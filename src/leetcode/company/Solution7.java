package leetcode.company;

public class Solution7 {
    public TreeNode buildTree(int[] inOrder, int[] postOrder) {
        return null;
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
//        boolean result = new Solution().hasPathSum(treeNode, 22);
//        System.out.println(result);
    }
}

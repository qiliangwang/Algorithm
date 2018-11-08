package explore;

public class Solution1 {
    public int maxDepth(TreeNode root) {
        if (root != null) {
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);
            return Math.max(leftDepth, rightDepth) + 1;
        }else {
            return 0;
        }
    }

    public static void main(String[] args) {
        //build tree
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        int depth = new Solution1().maxDepth(root);
        System.out.println(depth);
    }
}

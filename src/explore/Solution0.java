package explore;

public class Solution0 {
    private int depth;
    public int maxDepth(TreeNode root) {
        maxDepth(root, 1);
        return depth;
    }

    private void maxDepth(TreeNode node, int curDepth) {
        if (node != null) {
            if (curDepth > depth) {
                depth = curDepth;
            }
            maxDepth(node.left, curDepth + 1);
            maxDepth(node.right, curDepth + 1);
        }
    }

    public static void main(String[] args) {
        //build tree
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        int depth = new Solution0().maxDepth(root);
        System.out.println(depth);
    }
}

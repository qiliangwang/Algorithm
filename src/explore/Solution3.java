package explore;

public class Solution3 {

    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }
    private boolean isMirror(TreeNode node1, TreeNode node2) {

        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null) return false;

        return node1.val == node2.val && isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);

    }


    public static void main(String[] args) {
        //build tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(3);


        boolean symmetric = new Solution3().isSymmetric(root);
        System.out.println(symmetric);
    }
}

package leetcode.solutions;

import leetcode.base.TreeNode;

public class Solution226 {

    /**
     * inorder
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {return null;}

        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = right;
        root.right = left;

        invertTree(root.left);
        invertTree(root.right);
        return root;
    }


    /**
     * postorder
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {return null;}

        TreeNode left = invertTree2(root.left);
        TreeNode right = invertTree2(root.right);

        root.left = right;
        root.right = left;
        return root;
    }
}

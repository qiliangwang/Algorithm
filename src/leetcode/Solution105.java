package leetcode;

public class Solution105 {

    /**
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, 0, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int[] inorder, int preStart, int inStart, int inEnd) {
        if (preStart >= preorder.length || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int mid = 0;
        for (int i = inStart; i <= inEnd; i ++) {
            if (inorder[i] == root.val) {
                mid = i;
            }
        }
        root.left = build(preorder, inorder, preStart + 1, inStart, mid - 1);
        root.right = build(preorder, inorder, preStart + (mid - inStart + 1), mid + 1, inEnd);
        return root;
    }

    public static void main(String[] args) {
//        int[] preorder = {3, 9, 20, 15, 7};
//        int[] inorder = {9, 3, 15, 20, 7};

        int[] preorder = {1, 2, 3};
        int[] inorder = {2, 3, 1};
        TreeNode result = new Solution105().buildTree(preorder, inorder);
        System.out.println(result);
    }
}

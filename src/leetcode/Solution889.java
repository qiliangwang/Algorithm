package leetcode;

/**
 * Vader Wang
 */
public class Solution889 {

    /**
     * Return any binary tree that matches the given preorder and postorder traversals.
     *
     * Values in the traversals pre and post are distinct positive integers.
     *
     *
     *
     * Example 1:
     *
     * Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
     * Output: [1,2,3,4,5,6,7]
     */
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return construct(pre, post, 0, 0, pre.length);
    }

    private TreeNode construct(int[] pre, int[] post, int preStart, int postStart, int len) {
        if (len <= 0) return null;

        if (len == 1) return new TreeNode(pre[preStart]);

        TreeNode root = new TreeNode(pre[preStart]);

        int index = postStart;
        while (pre[preStart + 1] != post[index]) {
            index++;
        }
        int leftLen = index - postStart + 1;
        root.left = construct(pre, post, preStart + 1, postStart, leftLen);
        root.right = construct(pre, post, preStart + leftLen + 1, postStart + leftLen,len - leftLen - 1);
        return root;
    }


    public static void main(String[] args) {
        int[] pre = new int[] {1,2};
        int[] post = new int[] {2,1};

        TreeNode result = new Solution889().constructFromPrePost(pre, post);
        System.out.println(result);
    }
}

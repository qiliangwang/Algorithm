package leetcode.problem4.company;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(TreeNode node, List<Integer> result) {
        if (node != null ) {
            inOrder(node.left, result);
            result.add(node.val);
            inOrder(node.right, result);
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.right = new TreeNode(2);
        treeNode.right.left = new TreeNode(3);
        ArrayList<Integer> result = new ArrayList<>();
        new Solution2().inOrder(treeNode, result);
        System.out.println(result);
    }
}

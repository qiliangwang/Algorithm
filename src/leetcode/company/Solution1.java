package leetcode.company;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        preOrder(root, result);
        return result;
    }

    private void preOrder(TreeNode node, List<Integer> result) {
        if (node != null ) {
            result.add(node.val);
            preOrder(node.left, result);
            preOrder(node.right, result);
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.right = new TreeNode(2);
        treeNode.right.left = new TreeNode(3);
        List<Integer> result = new Solution1().preorderTraversal(treeNode);
        System.out.println(result);
    }
}

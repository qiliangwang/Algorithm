package leetcode.company;

import java.util.List;

public class BinaryTreePreOrderTraversal {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            preOrder(root);
            return null;
        }

        public void preOrder(TreeNode node) {
            if (node != null) {
                System.out.println(node.val);
            }
            preOrder(node.left);
            preOrder(node.right);
        }
    }

//    public static void main(String[] args) {
//        Solution
//    }
}



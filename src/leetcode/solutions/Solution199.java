package leetcode.solutions;

import leetcode.base.TreeNode;

import java.util.*;

public class Solution199 {

    /**
     * 使用BFS来得到二叉树的右视图
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i ++) {
                TreeNode node = queue.poll();
                if (i == size - 1) {
                    res.add(node.val);
                }
                if (node.left != null ) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(0);
        node.left = new TreeNode(1);
        node.right = new TreeNode(2);
        node.right.left = new TreeNode(3);

        List<Integer> integers = new Solution199().rightSideView(node);
        System.out.println(integers);
    }
}

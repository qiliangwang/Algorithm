package leetcode.problem4.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution4 {
    private class LevelNode {
        TreeNode treeNode;
        Integer level;
        public LevelNode(TreeNode treeNode, Integer level) {
            this.treeNode = treeNode;
            this.level = level;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {

        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        Queue<LevelNode> queue = new LinkedList<>();
        if (root == null) {
            return res;
        }
        queue.offer(new LevelNode(root, 0));
        while (!queue.isEmpty()) {

            LevelNode head = queue.remove();
            if (head.treeNode != null) {
                queue.offer(new LevelNode(head.treeNode.left, head.level + 1));
                queue.offer(new LevelNode(head.treeNode.right, head.level + 1));
                if (res.size() > head.level) {
                    res.get(head.level).add(head.treeNode.val);
                }else {
                    ArrayList<Integer> integers = new ArrayList<>();
                    integers.add(head.treeNode.val);
                    res.add(integers);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20);
        treeNode.right.left = new TreeNode(15);
        treeNode.right.right = new TreeNode(7);
        List<List<Integer>> result = new Solution4().levelOrder(treeNode);
        System.out.println(result);
    }
}

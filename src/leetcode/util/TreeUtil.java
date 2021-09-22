package leetcode.util;

import leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeUtil {


    /**
     * 按照层序遍历生成一颗树 主要是方便了
     * @param size
     * @return
     */
    public static TreeNode generateTree(int size) {
        return generateTreeBySize(size);
    }

    public static TreeNode generateTreeBySize(int size) {
        int num = 0;
        TreeNode root = new TreeNode(num++);
        size --;
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);

        while (!queue.isEmpty() && size > 0) {
            int qSize = queue.size();
            for (int i = 0; i < qSize; i ++) {
                TreeNode node = queue.poll();
                if (size > 0) {
                    size--;
                    node.left = new TreeNode(num++);
                    queue.offer(node.left);
                }
                if (size > 0) {
                    size --;
                    node.right = new TreeNode(num++);
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }

    public static TreeNode generateTreeByLevel() {
        return null;
    }

    /**
     * 层序遍历一颗树 && pretty print
     * @param root
     */
    public static void printTree(TreeNode root) {
        List<List<TreeNode>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean levelNotAllNull = true;
        while (!queue.isEmpty() && levelNotAllNull) {
            int size = queue.size();
            //获取非nullsize
            List<TreeNode> level = new ArrayList<>();
            levelNotAllNull = false;
            for (int i = 0; i < size; i ++) {
                TreeNode node = queue.poll();
                level.add(node);
                if (node != null) {
                    levelNotAllNull = true;
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    queue.offer(null);
                    queue.offer(null);
                }
            }
            if (!levelNotAllNull) {
                break;
            }
            ans.add(level);
        }
        for (List<TreeNode> l : ans) {
            System.out.println(l);
        }
    }

    public static void main(String[] args) {
        TreeNode node = TreeUtil.generateTree(10);
        TreeUtil.printTree(node);
    }
}

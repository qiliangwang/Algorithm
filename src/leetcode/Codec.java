package leetcode;

import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder res = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            res.append(pop.val + " ");
            if (pop.right != null) stack.push(pop.right);
            if (pop.left != null) stack.push(pop.left);
        }
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("")) return null;
        String[] newData = data.split(" ");
        Queue<Integer> queue = new LinkedList<>();
        for (String s : newData) {
            queue.offer(Integer.parseInt(s));
        }
        return getNode(queue);
    }

    private TreeNode getNode(Queue<Integer> queue) {
        if (queue.isEmpty()) return null;
        TreeNode root = new TreeNode(queue.poll());
        LinkedList<Integer> smallQueue = new LinkedList<>();
        while (!queue.isEmpty() && queue.peek() < root.val) {
            smallQueue.offer(queue.poll());
        }
        //left side of the tree
        root.left = getNode(smallQueue);
        root.right = getNode(queue);
        return root;
    }

    private static void preOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(5);

        treeNode.left = new TreeNode(4);
        treeNode.left.left = new TreeNode(1);

        treeNode.right = new TreeNode(6);
        treeNode.right.right = new TreeNode(8);

        Codec codec = new Codec();
        String serialize = codec.serialize(treeNode);
        System.out.println(serialize);

        TreeNode node = codec.deserialize(serialize);
        preOrder(node);
    }
}

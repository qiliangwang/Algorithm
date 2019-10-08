package leetcode.solutions;


import leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution145 {

    class NodeContainer {
        TreeNode node;
        boolean visited;

        public NodeContainer(TreeNode node, boolean visited) {
            this.node = node;
            this.visited = visited;
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<NodeContainer> stack = new Stack<>();
        TreeNode current = root;
        while (!stack.empty() || current != null) {
            // go to left leaf （current = null)
            while (current != null) {
                stack.push(new NodeContainer(current, false));
                current = current.left;
            }
            NodeContainer nodeContainer = stack.pop();
            TreeNode node = nodeContainer.node;
            if (nodeContainer.visited) {
                result.add(node.val);
            }else {
                nodeContainer.visited = true;
                stack.push(nodeContainer);
                current = node.right;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.right = new TreeNode(2);
        treeNode.right.left = new TreeNode(3);
        List<Integer> result = new Solution145().postorderTraversal(treeNode);
        System.out.println(result);
    }
}

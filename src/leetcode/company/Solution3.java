package leetcode.company;



import java.util.ArrayList;
import java.util.List;

public class Solution3 {
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        postOrder(root, result);
        return result;
    }

    private void postOrder(TreeNode node, List<Integer> result) {
        if (node != null ) {
            postOrder(node.left, result);
            postOrder(node.right, result);
            result.add(node.val);
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.right = new TreeNode(2);
        treeNode.right.left = new TreeNode(3);
        List<Integer> result = new Solution3().postorderTraversal(treeNode);
        System.out.println(result);
    }
}

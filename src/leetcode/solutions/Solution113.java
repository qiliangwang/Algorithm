package leetcode.solutions;

import leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution113 {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        ArrayList<List<Integer>> ans = new ArrayList<>();
        findPath(root, targetSum, new ArrayList<>(), ans);
        return ans;
    }

    private void findPath(TreeNode node, int sum, List<Integer> path, List<List<Integer>> ans) {
        if (node == null) {
            return;
        }
        path.add(node.val);
        if (node.val == sum && node.left == null && node.right == null) {
            ans.add(new ArrayList<>(path));
        }
        findPath(node.left, sum - node.val, path, ans);
        findPath(node.right, sum - node.val, path, ans);
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
    }
}

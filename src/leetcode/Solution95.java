package leetcode;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class Solution95 {

    public List<TreeNode> generateTrees(int n) {
        return helper(1, n);
    }

    private List<TreeNode> helper(int min, int max) {
        List<TreeNode> res = new ArrayList<>();
        if (min > max) return res;
        for (int rt = min; rt <= max; rt ++) {
            List<TreeNode> leftList = helper(min, rt - 1);
            List<TreeNode> rightList = helper(rt + 1, max);
            if (leftList.size() == 0 && rightList.size() == 0) {
                res.add(new TreeNode(rt));
            } else if (leftList.size() == 0) {
                for (TreeNode right : rightList) {
                    TreeNode root = new TreeNode(rt);
                    root.right = right;
                    res.add(root);
                }
            } else if (rightList.size() == 0) {
                for (TreeNode left : leftList) {
                    TreeNode root = new TreeNode(rt);
                    root.left = left;
                    res.add(root);
                }
            } else {
                for (TreeNode left : leftList) {
                    for (TreeNode right : rightList) {
                        TreeNode root = new TreeNode(rt);
                        root.left = left;
                        root.right = right;
                        res.add(root);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<TreeNode> result = new Solution95().generateTrees(3);
        System.out.println(result);
    }
}

package leetcode.solutions;

import leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 *
 * Example 1:
 *
 * Input: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * Output: 1
 *
 * --------------------------------------------
 *
 * Example 2:
 *
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * Output: 3
 * Follow up:
 * What if the BST is modified (insert/delete operations)
 * often and you need to find the kth smallest frequently?
 * How would you optimize the kthSmallest routine?
 * @author wangql
 * @since 2019/11/13 10:08 上午
 */
public class Solution230 {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> nums = inOrder(root, new ArrayList<>());
        return nums.get(k - 1);
    }

    private List<Integer> inOrder(TreeNode node, List<Integer> list) {
        if (node == null) {return list;}
        inOrder(node.left, list);
        list.add(node.val);
        inOrder(node.right, list);
        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);

        root.left = new TreeNode(3);
        root.right = new TreeNode(6);

        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);

        root.left.left.left = new TreeNode(1);

        int i = new Solution230().kthSmallest(root, 1);
        System.out.println(i);
    }
}

package leetcode;

import java.util.LinkedList;

class CodecII {

    /**
     *        1
     *       / \
     *      2   3
     *         / \
     *        4   5
     * "[1,2,3,null,null,4,5]"
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder res = new StringBuilder();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                res.append("# ");
                continue;
            }
            res.append(cur.val + " ");
            queue.offer(cur.left);
            queue.offer(cur.right);
        }
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("")) return null;
        String[] nodes = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        for (int i = 1; i < nodes.length; i++) {
            TreeNode cur = queue.poll();
            if (!nodes[i].equals("#")) {
                cur.left = new TreeNode(Integer.parseInt(nodes[i]));
                queue.offer(cur.left);
            }
            if (!nodes[++i].equals("#")) {
                cur.right = new TreeNode(Integer.parseInt(nodes[i]));
                queue.offer(cur.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);

        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        CodecII codecII = new CodecII();
        String serialize = codecII.serialize(root);
        TreeNode deserialize = codecII.deserialize(serialize);
        String deserializeSerialize = codecII.serialize(deserialize);
        System.out.println(serialize);
        System.out.println(deserializeSerialize);
    }
}

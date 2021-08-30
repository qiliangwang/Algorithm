package leetcode.base;

public class ListNodeUtil {

    /**
     * 打印链表的元素
     * @param node
     */
    public static void printLinkedList(ListNode node) {
        while (node != null) {
            if (node.next != null) {
                System.out.print(node.val + "->");
            } else {
                System.out.print(node.val);
            }
            node = node.next;
        }
        System.out.println();
    }


    /**
     * 生成链表
     * @param size
     * @return
     */
    public static ListNode generateLinkedList(int size) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int i = 1; i <= size; i ++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        return dummy.next;
    }
}

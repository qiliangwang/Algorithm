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

    /**
     * 使用快慢指针获取list的中间元素 奇数的时候刚刚好返回中间的node偶数的时候返回左边的node
     * @param head
     * @return
     */
    public static ListNode getMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 使用头插法来实现reverse
     * @return
     */
    public static ListNode reverseListByDummyNode(ListNode head) {
        ListNode dummy = new ListNode();
        while (head != null) {
            ListNode next = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = next;
        }
        return dummy.next;
    }

    /**
     * 使用pre来实现reverse
     * @return
     */
    public static ListNode reverseListByPreNode(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return head;
    }


    /**
     * 通过递归来reverse list
     * @return
     */
    public static ListNode reverseListByRecursive(ListNode head) {

        if (head == null || head.next == null) {return head;}

        ListNode reversed = reverseListByRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return reversed;
    }


    public static void main(String[] args) {
        ListNode node = ListNodeUtil.generateLinkedList(10);
        ListNodeUtil.printLinkedList(node);
        ListNodeUtil.printLinkedList(ListNodeUtil.getMiddle(node));
    }


}

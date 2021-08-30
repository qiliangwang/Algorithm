package leetcode.solutions;

import leetcode.base.ListNode;
import leetcode.base.ListNodeUtil;

/**
 * 其实是借助dummy来对链表进行reverse
 * 其实就是遍历reverse 每次都是insert到dummy的后面就行了
 * dummy -> 1
 * dummy -> 2 -> 1
 * ......
 * dummy -> 9 -> 8 -> 7 .....-> 1
 * 所以核心就几个东西了，1.保存heade的next 2. 将head的next变成dummy的next 3dummy的next变成head head变成next继续迭代
 */
public class Solution206 {
//
//    public ListNode reverseList0(ListNode head) {
//        ListNode pre = null;
//        //reverse
//        while (head != null) {
//            ListNode next = head.next;
//            head.next = pre;
//            pre = head;
//            head = next;
//        }
//        return pre;
//    }

    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode();
        while (head != null) {
            ListNode next = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {

        ListNode node = ListNodeUtil.generateLinkedList(10);

        ListNode result = new Solution206().reverseList(node);
        ListNodeUtil.printLinkedList(result);
    }
}

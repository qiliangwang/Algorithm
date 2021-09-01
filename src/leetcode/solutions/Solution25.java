package leetcode.solutions;


import leetcode.base.ListNode;
import leetcode.base.ListNodeUtil;

/**
 * 这个意思是按照k的间隔将list reverse 如果不符合k就不reverse
 */
public class Solution25 {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {return head;}

        ListNode cur = head;
        int count = 0;
        while (cur != null && count != k) {
            cur = cur.next;
            count ++;
        }

        if (count == k) {
            //这里其实是将（k后面的处理一下）
            cur = reverseKGroup(cur, k);
            //处理k的node 将node插到最前面即可
            while (count > 0) {
                ListNode temp = head.next;
                head.next = cur;
                cur = head;
                head = temp;
                count--;
            }
            head = cur;
        }
        return head;
    }


    public static void main(String[] args) {
      ListNode node = ListNodeUtil.generateLinkedList(10);
      ListNodeUtil.printLinkedList(node);
      ListNode result = new Solution25().reverseKGroup(node, 10);
      ListNodeUtil.printLinkedList(result);
    }
}

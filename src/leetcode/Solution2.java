package leetcode;

public class Solution2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode itr = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry > 0) {
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            carry = sum / 10;
            itr.next = new ListNode(sum % 10);

            itr = itr.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return dummy.next;
    }
    public static void main(String[] args) {
        ListNode result = new Solution2().addTwoNumbers(null, null);
        System.out.println(result);
    }
}

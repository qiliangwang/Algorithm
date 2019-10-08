package leetcode.solutions;

import leetcode.base.RandomListNode;

import java.util.HashMap;

/**
 * @author Vader Wang
 */
class Solution138 {

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode cur = dummy;
        while (head != null) {
            if (!map.containsKey(head)) {
                map.put(head, new RandomListNode(head.label));
            }
            cur.next = map.get(head);
            if (head.random != null) {
                if (!map.containsKey(head.random)) {
                    map.put(head.random, new RandomListNode(head.random.label));
                }
                cur.next.random = map.get(head.random);
            }
            head = head.next;
            cur = cur.next;
        }
        return dummy.next;
    }

    public RandomListNode copyRandomListII(RandomListNode head) {
        if (head == null) {
            return null;
        }
        //link copy to the original
        //point next and random
        //extract copy
        return null;
    }

    public static void main(String[] args) {
        RandomListNode result = new Solution138().copyRandomList(null);
    }
}

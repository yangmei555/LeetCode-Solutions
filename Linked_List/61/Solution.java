/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0)
            return head;
        int len = 0;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.next;
        }
        k %= len;
        if (k == 0)
            return head;
        ListNode newhead = head;
        node = head;
        for (int i = 0; i < len - 1; i++) {
            if (i == len - 1 - k) {
                ListNode temp = node.next;
                node.next = null;
                newhead = temp;
                node = temp;
            } else {
                node = node.next;
            }
        }
        node.next = head;
        return newhead;
    }
}


class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null)
            return null;
        ListNode slow = head, fast = head;
        int len = 0;
        while (k-- > 0) {
            fast = fast.next;
            len++;
            if (fast == null) {
                fast = head;
                k %= len;
            }
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        fast.next = head;
        ListNode res = slow.next;
        slow.next = null;
        return res;
    }
}

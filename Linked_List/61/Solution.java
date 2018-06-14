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

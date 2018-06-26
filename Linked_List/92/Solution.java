/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = dummy;
        for (int i = 1; i < m; i++)
            node = node.next;
        ListNode start = node.next, cur = node.next, pre = null, temp = null;
        for (int i = m; i < n; i++) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        temp = cur.next;
        cur.next = pre;
        start.next = temp;
        node.next = cur;
        return dummy.next;
    }
}


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = dummy;
        for (int i = 1; i < m; i++)
            node = node.next;
        ListNode start = node.next;
        for (int i = m; i < n; i++) {
            ListNode next = start.next;
            start.next = next.next;
            next.next = node.next;
            node.next = next;
        }
        return dummy.next;
    }
}

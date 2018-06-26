/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode last = head, node = last.next, pre = dummy, cur = head;
        while (node != null) {
            last.next = node.next;
            if (cur.val > node.val) {
                pre = dummy;
                cur = dummy.next;
            }
            while (pre != last) {
                if (cur.val > node.val)
                    break;
                pre = cur;
                cur = cur.next;
            }
            node.next = pre.next;
            pre.next = node;
            if (pre == last)
                last = last.next;
            node = last.next;
            cur = pre.next;
        }
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
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy, node = head;
        while (node != null) {
            ListNode temp = node.next;
            if (pre.val > node.val)
                pre = dummy;
            while (pre.next != null && pre.next.val <= node.val)
                pre = pre.next;
            node.next = pre.next;
            pre.next = node;
            node = temp;
        }
        return dummy.next;
    }
}

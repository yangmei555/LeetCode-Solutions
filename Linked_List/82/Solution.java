/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy, node = head;
        while (node != null && node.next != null) {
            if (node.val == node.next.val) {
                while (node.next != null && node.val == node.next.val)
                    node = node.next;
                node = node.next;
                prev.next = node;
            } else {
                prev = node;
                node = node.next;
            }
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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        if (head.val == head.next.val) {
            while (head.next != null && head.val == head.next.val)
                head = head.next;
            return deleteDuplicates(head.next);
        } else {
            head.next = deleteDuplicates(head.next);
            return head;
        }
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0), cur = dummy, node = head;
        cur.next = head;
        while (true) {
            ListNode next = node, bound = node;
            int count = k;
            while (count > 0 && bound != null) {
                bound = bound.next;
                count--;
            }
            if (count != 0)
                break;
            ListNode pre = bound;
            while (node != bound) {
                ListNode temp = node.next;
                node.next = pre;
                pre = node;
                node = temp;
            }
            cur.next = pre;
            cur = next;
        }
        return dummy.next;
    }
}

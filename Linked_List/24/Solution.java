/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = dummy, temp = null;
        while (node.next != null && node.next.next != null) {
            temp = node.next;
            node.next = node.next.next;
            temp.next = temp.next.next;
            node.next.next = temp;
            node = node.next.next;
        }
        return dummy.next;
    }
}

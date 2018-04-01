/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return l1 == null ? l2 : l1;
        int carry = 0, n1 = 0, n2 = 0, sum = 0;
        ListNode dummy = new ListNode(0), node = dummy;
        while (l1 != null || l2 != null || carry != 0) {
            n1 = l1 == null ? 0 : l1.val;
            n2 = l2 == null ? 0 : l2.val;
            sum = n1 + n2 + carry;
            node.next = new ListNode(sum % 10);
            carry = sum / 10;
            node = node.next;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        return dummy.next;
    }
}

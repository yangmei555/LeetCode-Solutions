/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        int lenA = 1, lenB = 1;
        ListNode nodeA = headA, nodeB = headB;
        while (nodeA.next != null || nodeB.next != null) {
            if (nodeA.next != null) {
                lenA++;
                nodeA = nodeA.next;
            }
            if (nodeB.next != null) {
                lenB++;
                nodeB = nodeB.next;
            }
        }
        if (nodeA != nodeB)
            return null;
        ListNode startFirst = null, startSecond = null;
        startFirst = lenA > lenB ? headA : headB;
        startSecond = lenA > lenB ? headB : headA;
        int diff = lenA > lenB ? lenA - lenB : lenB - lenA;
        for (int i = 0; i < diff; i++)
            startFirst = startFirst.next;
        while (startFirst != null) {
            if (startFirst == startSecond)
                return startFirst;
            else {
                startFirst = startFirst.next;
                startSecond = startSecond.next;
            }
        }
        return null;
    }
}

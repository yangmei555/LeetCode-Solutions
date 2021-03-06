/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return l1 == null ? l2 : l1;
        ListNode dummy = new ListNode(0), node = dummy;
        ListNode compare = l2, temp = null;
        node.next = l1;
        while (node.next != null) {
            if (node.next.val > compare.val) {
                temp = node.next;
                node.next = compare;
                compare = temp;
                node = node.next;
            } else {
                node = node.next;
            }
        }
        if (compare != null)
            node.next = compare;
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return l1 == null ? l2 : l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        else if (l2 == null)
            return l1;
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                node.next = l1;
                l1 = l1.next;
                node = node.next;
            } else {
                node.next = l2;
                l2 = l2.next;
                node = node.next;
            }
        }
        if (l1 == null)
            node.next = l2;
        else 
            node.next = l1;
        return dummy.next;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode dum1 = new ListNode(0), dum2 = new ListNode(0), node = head;
        ListNode node1 = dum1, node2 = dum2;
        while (node != null) {
            if (node.val < x) {
                node1.next = new ListNode(node.val);
                node1 = node1.next;
            } else {
                node2.next = new ListNode(node.val);
                node2 = node2.next;
            }
            node = node.next;
        }
        node1.next = dum2.next;
        return dum1.next;
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
    public ListNode partition(ListNode head, int x) {
        ListNode pre = null, node = head, tail1 = new ListNode(0), tail2 = null, 
                    newhead = null, temp = null;
        tail1.next = head;
        while (node != null) {
            if (node.val < x) { 
                temp = node.next;
                if (tail2 != null) 
                    tail2.next = temp;
                if (newhead == null) 
                    newhead = node;
                if (tail2 != null) {
                    node.next = tail1.next;
                    tail1.next = node;
                    tail1 = tail1.next;
                } else {
                    tail1 = node;
                }
                node = temp;
            } else {
                tail2 = node;
                node = node.next;
            }
        }
        return newhead == null ? head : newhead;
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
    public ListNode partition(ListNode head, int x) {
        ListNode dum1 = new ListNode(0), dum2 = new ListNode(0), node = head;
        ListNode node1 = dum1, node2 = dum2;
        while (node != null) {
            if (node.val < x) {
                node1.next = node;
                node1 = node1.next;
            } else {
                node2.next = node;
                node2 = node2.next;
            }
            node = node.next;
        }
        node1.next = dum2.next;
        node2.next = null;
        return dum1.next;
    }
}

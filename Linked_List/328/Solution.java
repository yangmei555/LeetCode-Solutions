/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(1);
        ListNode[] nodes = new ListNode[]{dummy1, dummy2};
        ListNode node = head;
        int count = 0;
        while (true) {
            nodes[count].next = node;
            if (node == null)
                break;
            nodes[count] = nodes[count].next;
            node = node.next;
            count = (count + 1) & 1;
        }
        nodes[0].next = dummy2.next;
        return dummy1.next;
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
    public ListNode oddEvenList(ListNode head) {
        ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(1);
        ListNode node1 = dummy1, node2 = dummy2;
        ListNode node = head;
        while (node != null) {
            node1.next = node;
            node = node.next;
            node1 = node1.next;
            if (node == null)
                break;
            node2.next = node;
            node = node.next;
            node2 = node2.next;
        }
        node1.next = dummy2.next;
        node2.next = null;
        return dummy1.next;
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
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode odd = head, even = head.next, second = head.next;
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = second;
        return head;
    }
}

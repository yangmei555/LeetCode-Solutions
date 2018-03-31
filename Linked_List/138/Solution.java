/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null)
            return null;
        RandomListNode dummy = new RandomListNode(0), rnode = dummy, node = head, temp = null;
        while (node != null) {
            temp = node.next;
            node.next = new RandomListNode(node.label);
            node.next.next = temp;
            node = temp;
        }
        dummy.next = head.next;
        node = head;
        while (node != null) {
            if (node.random == null)
                node.next.random = null;
            else
                node.next.random = node.random.next;
            node = node.next.next;
        }
        node = head;
        while (node != null) {
            rnode.next = node.next;
            node.next = node.next.next;
            node = node.next;
            rnode = rnode.next;
        }
        rnode.next = null;
        return dummy.next;
    }
}

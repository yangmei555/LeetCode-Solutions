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
        RandomListNode node = head, newhead = new RandomListNode(0), newnode = newhead;
        while (node != null) {
            RandomListNode temp = node.next;
            newnode.next = new RandomListNode(node.label);
            newnode = newnode.next;
            newnode.random = node;
            node.next = newnode;
            node = temp;
        }
        newnode = newhead.next;
        while (newnode != null) {
            RandomListNode temp = newnode.next;
            if (newnode.random.random == null)
                newnode.random = null;
            else
                newnode.random = newnode.random.random.next;
            if (temp != null)
                newnode.next = temp.random;
            newnode = temp;
        }
        node = head;
        while (node != null) {
            RandomListNode temp = node.next.next;
            if (temp != null)
                node.next.next = node.next.next.next;
            node.next = temp;
            node = node.next;
        }
        return newhead.next;
    }
}


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
        RandomListNode node = head, newhead = new RandomListNode(0), newnode = newhead;
        while (node != null) {
            newnode.next = new RandomListNode(node.label);
            newnode = newnode.next;
            newnode.random = node.random;
            node.random = newnode;
            node = node.next;
        }
        newnode = newhead.next;
        while (newnode != null) {
            RandomListNode temp = newnode.next;
            newnode.next = newnode.random;
            if (newnode.random != null)
                newnode.random = newnode.random.random;
            newnode = temp;
        }
        node = head;
        while (node != null) {
            RandomListNode temp = node.random.next;
            if (node.next != null)
                node.random.next = node.next.random;
            else
                node.random.next = null;
            node.random = temp;
            node = node.next;
        }
        return newhead.next;
    }
}


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
        RandomListNode node = head;
        while (node != null) {
            RandomListNode temp = node.next;
            node.next = new RandomListNode(node.label);
            node.next.next = temp;
            node = temp;
        }
        node = head;
        while (node != null) {
            if (node.random == null)
                node.next.random = null;
            else
                node.next.random = node.random.next;
            node = node.next.next;
        }
        RandomListNode newhead = new RandomListNode(0), randomnode = newhead;
        newhead.next = head;
        node = head;
        while (node != null) {
            randomnode.next = randomnode.next.next;
            node.next = node.next.next;
            randomnode = randomnode.next;
            node = node.next;
        }
        return newhead.next;
    }
}

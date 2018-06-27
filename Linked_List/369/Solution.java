/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        helper(dummy);
        return dummy.val == 0 ? dummy.next : dummy;
    }
    
    public boolean helper(ListNode head) {
        if (head == null)
            return true;
        boolean ret = helper(head.next);
        if (ret) {
            head.val++;
            if (head.val == 10) {
                head.val = 0;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
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
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy, node = dummy.next;
        while (node.next != null) {
            if (node.val != 9)
                first = node;
            node = node.next;
        }
        if (node.val == 9) {
            first.val++;
            first = first.next;
            while (first != null) {
                first.val = 0;
                first = first.next;
            }
        } else {
            node.val++;
        }
        return dummy.val == 0 ? dummy.next : dummy;
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
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        while (head.next != null) {
            ListNode temp = head.next;
            head.next = head.next.next;
            temp.next = dummy.next;
            dummy.next = temp;
        }
        int carry = 1;
        ListNode node = dummy.next;
        while (carry != 0) {
            node.val++;
            carry = node.val / 10;
            node.val %= 10;
            if (carry == 1) {
                if (node.next == null) {
                    node.next = new ListNode(1);
                    carry = 0;
                } else {
                    node = node.next;
                }
            }
        }
        head = dummy.next;
        while (head.next != null) {
            ListNode temp = head.next;
            head.next = head.next.next;
            temp.next = dummy.next;
            dummy.next = temp;
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
    public ListNode plusOne(ListNode head) {
        if (head == null)
            return new ListNode(1);
        ListNode node = plusOne(head.next);
        if (node == head.next) {
            return head;
        } else {
            head.val++;
            if (head.val != 10) {
                return head;
            } else {
                head.val = 0;
                head.next = node.next;
                node.next = head;
                return node;
            }
        }
    }
}

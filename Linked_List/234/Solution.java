/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    boolean result = true;
    public boolean isPalindrome(ListNode head) {
        help(head, head);
        return result;
    }
    
    public ListNode help(ListNode slow, ListNode fast) {
        if (fast == null)
            return slow;
        else if (fast.next == null)
            return slow.next;
        ListNode node = help(slow.next, fast.next.next);
        if (result == false)
            return null;
        if (node.val == slow.val) {
            return node.next;
        } else {
            result = false;
            return null;
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
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head, reverse = null, temp = null;
        while (fast != null && fast.next != null) {
            temp = slow;
            slow = slow.next;
            fast = fast.next.next;
            temp.next = reverse;
            reverse = temp;
        }
        if (fast != null)
            slow = slow.next;
        while (slow != null) {
            if (slow.val != reverse.val)
                return false;
            slow = slow.next;
            reverse = reverse.next;
        }
        return true;
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
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null)
            slow = slow.next;
        ListNode halfHead = null, temp = null;
        while (slow != null) {
            temp = slow.next;
            slow.next = halfHead;
            halfHead = slow;
            slow = temp;
        }
        slow = head;
        while (halfHead != null) {
            if (halfHead.val != slow.val)
                return false;
            halfHead = halfHead.next;
            slow = slow.next;
        }
        return true;   
    }
}

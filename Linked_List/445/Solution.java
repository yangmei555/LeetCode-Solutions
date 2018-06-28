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
        Stack<Integer> stack1 = new Stack<>(), stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode node = null, last = null;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            node = new ListNode(stack1.pop() + stack2.pop() + carry);
            carry = node.val / 10;
            node.val %= 10;
            node.next = last;
            last = node;
        }
        if (stack1.isEmpty()) {
            while (!stack2.isEmpty()) {
                node = new ListNode(stack2.pop() + carry);
                carry = node.val / 10;
                node.val %= 10;
                node.next = last;
                last = node;
            }
        } else {
            while (!stack1.isEmpty()) {
                node = new ListNode(stack1.pop() + carry);
                carry = node.val / 10;
                node.val %= 10;
                node.next = last;
                last = node;
            }
        }
        if (carry != 0) {
            node = new ListNode(1);
            node.next = last;
        } 
        return node;
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
    int carry = 0;
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node1 = l1, node2 = l2;
        while (node1 != null && node2 != null) {
            node1 = node1.next;
            node2 = node2.next;
        }
        int count = 0;
        ListNode longlist = node1 == null ? l2 : l1, shortlist = node1 == null ? l1 : l2;
        while (node1 != null || node2 != null) {
            count++;
            node1 = node1 == null ? null : node1.next;
            node2 = node2 == null ? null : node2.next;
        }
        ListNode longmove = longlist;
        for (int i = 0; i < count; i++)
            longmove = longmove.next;
        ListNode res = helper(longmove, shortlist);
        res = helper2(longlist, longmove, res);
        if (carry == 1) {
            ListNode newnode = new ListNode(1);
            newnode.next = res;
            res = newnode;
        }
        return res;
    }
    
    public ListNode helper(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)
            return null;
        ListNode ret = helper(l1.next, l2.next);
        ListNode node = new ListNode(l1.val + l2.val + carry);
        carry = node.val / 10;
        node.val %= 10;
        node.next = ret;
        return node;
    }
    
    public ListNode helper2(ListNode longlist, ListNode term, ListNode splice) {
        if (longlist == term) {
            return splice;
        } else {
            ListNode ret = helper2(longlist.next, term, splice);
            ListNode res = new ListNode(longlist.val + carry);
            carry = res.val / 10;
            res.val %= 10;
            res.next = ret;
            return res;
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
    int carry = 0;
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node1 = l1, node2 = l2;
        while (node1 != null && node2 != null) {
            node1 = node1.next;
            node2 = node2.next;
        }
        int count = 0;
        ListNode longlist = node1 == null ? l2 : l1, shortlist = node1 == null ? l1 : l2;
        while (node1 != null || node2 != null) {
            count++;
            node1 = node1 == null ? null : node1.next;
            node2 = node2 == null ? null : node2.next;
        }
        ListNode shortpad = new ListNode(0), shortrun = shortpad;
        for (int i = 0; i < count; i++) {
            shortrun.next = new ListNode(0);
            shortrun = shortrun.next;
        }
        shortrun.next = shortlist;
        ListNode res = helper(longlist, shortpad.next);
        if (carry == 1) {
            ListNode newnode = new ListNode(1);
            newnode.next = res;
            res = newnode;
        }
        return res;
    }
    
    public ListNode helper(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)
            return null;
        ListNode ret = helper(l1.next, l2.next);
        ListNode node = new ListNode(l1.val + l2.val + carry);
        carry = node.val / 10;
        node.val %= 10;
        node.next = ret;
        return node;
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
    int carry = 0;
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node1 = l1, node2 = l2;
        while (node1 != null && node2 != null) {
            node1 = node1.next;
            node2 = node2.next;
        }
        int count = 0;
        ListNode longlist = node1 == null ? l2 : l1, shortlist = node1 == null ? l1 : l2;
        while (node1 != null || node2 != null) {
            count++;
            node1 = node1 == null ? null : node1.next;
            node2 = node2 == null ? null : node2.next;
        }
        ListNode dummy = new ListNode(0), not9 = dummy, run = dummy;
        for (int i = 0; i < count; i++) {
            run.next = new ListNode(longlist.val);
            run = run.next;
            longlist = longlist.next;
            if (run.val != 9)
                not9 = run;
        }
        while (longlist != null && shortlist != null) {
            run.next = new ListNode(longlist.val + shortlist.val);
            run = run.next;
            longlist = longlist.next;
            shortlist = shortlist.next;
            if (run.val >= 10) {
                run.val -= 10;
                not9.val++;
                not9 = not9.next;
                while (not9 != run) {
                    not9.val = 0;
                    not9 = not9.next;
                }
            } else if (run.val != 9) {
                not9 = run;
            }
        }
        return dummy.val == 1 ? dummy : dummy.next;
    }
}

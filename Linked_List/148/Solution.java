/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode second = slow.next;
        slow.next = null;
        head = sortList(head);
        second = sortList(second);
        ListNode dummy = new ListNode(0), node = dummy;
        while (head != null && second != null) {
            if (head.val < second.val) {
                node.next = head;
                head = head.next;
            } else {
                node.next = second;
                second = second.next;
            }
            node = node.next;
        }
        node.next = head == null ? second : head;
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
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0), node = head;
        dummy.next = head;
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        for (int size = 1; size < len; size *= 2) {
            ListNode pre = dummy, start = dummy.next;
            while (start != null) {
                ListNode left = start;
                ListNode right = partition(start, size);
                ListNode nextstart = partition(right, size);
                pre = merge(left, right, pre);
                pre.next = nextstart;
                start = nextstart;
            }
        }
        return dummy.next;
    }
    
    public ListNode partition(ListNode start, int size) {
        if (start == null)
            return null;
        int count = 1;
        while (count < size && start.next != null) {
            count++;
            start = start.next;
        }
        ListNode res = start.next;
        start.next = null;
        return res;
    }
    
    public ListNode merge(ListNode left, ListNode right, ListNode pre) {
        while (left != null && right != null) {
            if (left.val < right.val) {
                pre.next = left;
                left = left.next;
            } else {
                pre.next = right;
                right = right.next;
            }
            pre = pre.next;
        }
        pre.next = left == null ? right : left;
        while (pre.next != null)
            pre = pre.next;
        return pre;
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
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode tail = head;
        while (tail.next != null)
            tail = tail.next;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        quicksort(dummy, head, tail);
        return dummy.next;
    }
    
    public void quicksort(ListNode dummy, ListNode head, ListNode tail) {
        if (head == tail)
            return;
        ListNode tail1 = head, tail2 = null, node = head.next, end = tail.next;
        head.next = null;
        while (node != end) {
            ListNode next = node.next;
            if (node.val <= head.val) {
                tail1 = tail1 == head ? node : tail1;
                node.next = dummy.next;
                dummy.next = node;
            } else {
                tail2 = tail2 == null ? node : tail2;
                node.next = head.next;
                head.next = node;
            }
            node = next;
        }
        if (tail2 == null) {
            head.next = end;
            tail2 = end;
        } else {
            tail2.next = end;
        }
        quicksort(dummy, dummy.next, tail1);
        quicksort(head, head.next, tail2);
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
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0), tail = head;
        while (tail.next != null)
            tail = tail.next;
        dummy.next = head;
        quicksort(dummy, tail);
        return dummy.next;
    } 
    
    public void quicksort(ListNode dummy, ListNode tail) {
        if (dummy == tail || dummy.next == tail)
            return;
        ListNode node = dummy.next, place = dummy;
        while (node != tail) {
            if (node.val <= tail.val) {
                place = place.next;
                int temp = place.val;
                place.val = node.val;
                node.val = temp;
            }
            node = node.next;
        }
        int temp = place.next.val;
        place.next.val = tail.val;
        tail.val = temp;
        quicksort(dummy, place);
        quicksort(place.next, tail);
    }
}

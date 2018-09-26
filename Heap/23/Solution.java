/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            public int compare(ListNode node1, ListNode node2) {
                return node1.val - node2.val;
            }
        });
        for (ListNode node : lists) {
            if (node != null)
                queue.offer(node);
        }
        ListNode dump = new ListNode(0);
        ListNode cur = dump;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null)
                queue.offer(node.next);
        }
        return dump.next;
    }
}


// merge sort. seems also O(N * log(k)) 
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        return helper(lists, 0, lists.length-1);
    }
    
    public ListNode helper(ListNode[] lists, int left, int right) {
        if (left > right)
            return null;
        if (left == right)
            return lists[left];
        int mid = (left + right) / 2;
        ListNode node1 = helper(lists, left, mid), node2 = helper(lists, mid+1, right);
        ListNode dummy = new ListNode(0), cur = dummy;
        while (node1 != null || node2 != null) {
            if (node2 == null || node1 != null && node1.val <= node2.val) {
                cur.next = node1;
                node1 = node1.next;
            } else {
                cur.next = node2;
                node2 = node2.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}

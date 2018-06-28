/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        ListNode node = root;
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        node = root;
        for (int i = 0; i < k && node != null; i++) {
            res[i] = node;
            for (int j = 1; j < len / k; j++)
                node = node.next;
            if (len / k != 0 && i < len % k) 
                node = node.next;
            ListNode temp = node.next;
            node.next = null;
            node = temp;
        }
        return res;
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
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        ListNode node = root;
        int step = k;
        for (int i = 0; i < k && node != null; i++) {
            res[i] = node;
            ListNode fast = node, slow = node;
            while (fast != null) {
                for (int j = 0; j < step && fast != null; j++)
                    fast = fast.next;
                if (fast != null) 
                    slow = slow.next;
            }
            ListNode temp = slow.next;
            slow.next = null;
            slow = temp;
            node = slow;
            step--;
        }
        return res;
    }
}

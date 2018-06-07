/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        return helper(list, 0, list.size()-1);
    }
    
    public TreeNode helper(List<Integer> list, int start, int end) {
        if (start > end)
            return null;
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = helper(list, start, mid - 1);
        root.right = helper(list, mid + 1, end);
        return root;
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
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    ListNode node = null;
    public TreeNode sortedListToBST(ListNode head) {
        node = head;
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return helper(0, len-1);
    }
    
    public TreeNode helper(int start, int end) {
        if (start > end)
            return null;
        int mid = (start + end) / 2;
        TreeNode left = helper(start, mid-1);
        TreeNode root = new TreeNode(node.val);
        node = node.next;
        TreeNode right = helper(mid+1, end);
        root.left = left;
        root.right = right;
        return root;
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
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        ListNode slow = head, fast = head, pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        if (pre != null) {
            pre.next = null;
            root.left = sortedListToBST(head);        
        }
        root.right = sortedListToBST(slow.next);
        return root;
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
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    ListNode node = null;
    public TreeNode sortedListToBST(ListNode head) {
        node = head;
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return helper(len-1);
    }
    
    public TreeNode helper(int end) {
        if (0 > end)
            return null;
        int mid = (0 + end) / 2;
        TreeNode left = helper(mid-1);
        TreeNode root = new TreeNode(node.val);
        node = node.next;
        TreeNode right = helper(end-mid-1);
        root.left = left;
        root.right = right;
        return root;
    }
}

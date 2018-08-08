/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int numComponents(ListNode head, int[] G) {
        ListNode node = head;
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        boolean[] map = new boolean[len];
        for (int g : G)
            map[g] = true;
        int res = 0;
        node = head;
        while (node != null) {
            if (map[node.val]) {
                res++;
                while (node != null && map[node.val])
                    node = node.next;
            }
            if (node != null)
                node = node.next;
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
    public int numComponents(ListNode head, int[] G) {
        Set<Integer> set = new HashSet<>();
        for (int g : G)
            set.add(g);
        int res = 0;
        ListNode node = head;
        while (node != null) {
            if (set.contains(node.val) && (node.next == null || !set.contains(node.next.val)))
                res++;
            node = node.next;
        }
        return res;
    }
}

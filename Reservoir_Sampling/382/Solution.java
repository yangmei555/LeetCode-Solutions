/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    List<Integer> list;
    Random random;
    public Solution(ListNode head) {
        list = new LinkedList<>();
        random = new Random();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    ListNode head;
    Random random;
    public Solution(ListNode head) {
        random = new Random();
        this.head = head;
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        int step = 0, res = 0;
        ListNode node = head;
        while (node != null) {
            step++;
            if (random.nextInt(step) == 0)
                res = node.val;
            node = node.next;
        }
        return res;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */

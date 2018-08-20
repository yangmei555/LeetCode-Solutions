/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        TreeLinkNode node = root;
        while (node != null) {
            TreeLinkNode nextLevel = null, loop = node, cur = null;
            while (loop != null) {
                if (cur != null && loop.left != null) {
                    cur.next = loop.left;
                    cur = cur.next;
                }
                if (cur == null && loop.left != null) {
                    cur = loop.left;
                    nextLevel = cur;
                }
                if (cur != null && loop.right != null) {
                    cur.next = loop.right;
                    cur = cur.next;
                }
                if (cur == null && loop.right != null) {
                    cur = loop.right;
                    nextLevel = cur;
                }
                loop = loop.next;
            }
            node = nextLevel;
        }
    }
}


// this one is not constant space so it does not meet the requirement 
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        Queue<TreeLinkNode> queue = new LinkedList<>();
        if (root != null)
            queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeLinkNode node = queue.poll();
                if (i != size - 1)
                    node.next = queue.peek();
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
        }
    }
}


/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root != null) {
            if (root.left != null && root.right != null) 
                root.left.next = root.right;
            if (root.left != null || root.right != null) {
                TreeLinkNode node = root.left != null && root.right != null ? root.right : 
                                                (root.left == null ? root.right : root.left);
                TreeLinkNode temp = root.next;
                while (temp != null && temp.left == null && temp.right == null)
                    temp = temp.next;
                if (temp != null)
                    node.next = temp.left != null ? temp.left : temp.right;
            }
            connect(root.right);
            connect(root.left);
        }
    }
}

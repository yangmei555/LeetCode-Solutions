/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null)
            queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null)
                sb.append("null").append(',');
            else
                sb.append(node.val).append(',');
            if (node != null) {
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return sb.length() == 0 ? "" : sb.substring(0, sb.length()-1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0)
            return null;
        String[] strs = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        int index = 0;
        TreeNode root = new TreeNode(Integer.parseInt(strs[index++]));
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (!strs[index].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(strs[index]));
                queue.offer(node.left);
            }
            index++;
            if (!strs[index].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(strs[index]));
                queue.offer(node.right);
            }
            index++;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

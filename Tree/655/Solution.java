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
    public List<List<String>> printTree(TreeNode root) {
        return helper(root);
    }
    
    public List<List<String>> helper(TreeNode root) {
        List<List<String>> res = new LinkedList<>();
        if (root == null)
            return res;
        if (root.left == null && root.right == null) {
            List<String> list = new LinkedList<>();
            list.add("" + root.val);
            res.add(list);
            return res;
        }
        List<List<String>> left = helper(root.left), right = helper(root.right);
        int size1 = left.size(), size2 = right.size();
        int maxrow = size1 > size2 ? size1 : size2;
        int col1 = root.left == null ? 0 : left.get(0).size(), 
            col2 = root.right == null ? 0 : right.get(0).size();
        int maxcol = col1 > col2 ? col1 : col2;
        int times = maxcol == col1 ? (col1-col2)/2 : (col2-col1)/2;
        for (int i = 0; i < maxrow; i++) {
            List<String> llist = null;
            List<String> rlist = null;
            if (i >= size1) {
                llist = new LinkedList<>();
                if (size1 == 0) {
                    llist.add("");
                } else {
                    for (int j = 0; j < col1; j++)
                        llist.add("");
                }
            } else {
                llist = left.get(i);
            }
            if (i >= size2) {
                rlist = new LinkedList<>();
                if (size2 == 0) {
                    rlist.add("");
                } else {
                    for (int j = 0; j < col2; j++)
                        rlist.add("");
                }
            } else {
                rlist = right.get(i);
            }
            List<String> pad = maxcol != col1 ? llist : rlist;
            int counts = times / (pad.size()/2 + 1);
            for (int j = 0; j < pad.size(); j += 1) {
                for (int k = 0; k < counts; k++) 
                    pad.add(j, "");
                j += counts + 1;
                for (int k = 0; k < counts; k++)
                    pad.add(j, "");
                j += counts;
            }
            llist.add("");
            llist.addAll(rlist);
            res.add(llist);
        }
        List<String> first = new LinkedList<>();
        first.add("" + root.val);
        for (int i = 0; i < maxcol; i++) {
            first.add(0, "");
            first.add("");
        }
        res.add(0, first);
        return res;
    }
}


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
    public List<List<String>> printTree(TreeNode root) {
        int height = helper(root, 0);
        return getList(root, 0, height);
    }
    
    public int helper(TreeNode root, int level) {
        if (root == null)
            return level;
        int left = helper(root.left, level + 1);
        int right = helper(root.right, level + 1);
        return left > right ? left : right;
    }
    
    public List<List<String>> getList(TreeNode root, int level, int height) {
        if (root == null)
            return null;
        List<List<String>> res = new ArrayList<>();
        List<String> first = new ArrayList<>(), padlist = new LinkedList<>();
        int pad = (1 << (height-1-level))-1;
        for (int i = 0; i < pad; i++)
            padlist.add("");
        first.addAll(padlist);
        first.add("" + root.val);
        first.addAll(padlist);
        res.add(first);
        if (root.left == null && root.right == null) {
            for (int i = 0; i < height-1-level; i++) {
                List<String> list = new LinkedList<>();
                list.addAll(padlist);
                list.addAll(padlist);
                list.add("");
                res.add(list);
            }
            return res;
        }
        List<List<String>> left = getList(root.left, level+1, height);
        List<List<String>> right = getList(root.right, level+1, height);
        int row = left == null ? right.size() : left.size();
        for (int i = 0; i < row; i++) {
            List<String> llist = left == null ? new LinkedList<>(padlist) : left.get(i);
            List<String> rlist = right == null ? new LinkedList<>(padlist) : right.get(i);
            llist.add("");
            llist.addAll(rlist);
            res.add(llist);
        }
        return res;
    }
}


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
    public List<List<String>> printTree(TreeNode root) {
        int height = helper1(root, 0);
        String[][] strs = new String[height][(1 << height) - 1];
        for (int i = 0; i < height; i++)
            Arrays.fill(strs[i], "");
        helper2(strs, root, 0, 0, (1 << height) - 2);
        List<List<String>> res = new LinkedList<>();
        for (int i = 0; i < height; i++)
            res.add(Arrays.asList(strs[i]));
        return res;
    }
    
    public int helper1(TreeNode root, int level) {
        if (root == null)
            return level;
        int left = helper1(root.left, level + 1);
        int right = helper1(root.right, level + 1);
        return left > right ? left : right;
    }
    
    public void helper2(String[][] strs, TreeNode root, int level, int left, int right) {
        if (root == null)
            return;
        int mid = (left + right) / 2;
        strs[level][mid] = root.val + "";
        helper2(strs, root.left, level+1, left, mid-1);
        helper2(strs, root.right, level+1, mid+1, right);
    }
}

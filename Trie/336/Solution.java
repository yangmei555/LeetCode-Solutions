class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new LinkedList<>();
        boolean[][] used = new boolean[words.length][words.length];
        Node root = new Node(), revroot = new Node();
        for (int i = 0; i < words.length; i++) {
            char[] ch = words[i].toCharArray();
            Node cur = root;
            for (char c : ch) {
                if (cur.nodes[c-'a'] == null)
                    cur.nodes[c-'a'] = new Node();
                cur = cur.nodes[c-'a'];
            }
            cur.end = true;
            cur.index = i;
            cur = revroot;
            for (int j = ch.length-1; j >= 0; j--) {
                if (cur.nodes[ch[j]-'a'] == null)
                    cur.nodes[ch[j]-'a'] = new Node();
                cur = cur.nodes[ch[j]-'a'];
            }
            cur.end = true;
            cur.index = i;
        }
        for (int i = 0; i < words.length; i++) {
            char[] ch = words[i].toCharArray();
            Node cur = revroot;
            boolean flag = false;
            for (char c : ch) {
                if (cur.nodes[c-'a'] == null) {
                    flag = true;
                    break;
                }
                cur = cur.nodes[c-'a'];
            }
            if (!flag)
                helper(cur, new StringBuilder(), i, true, used, res);
            cur = root;
            flag = false;
            for (int j = ch.length-1; j >= 0; j--) {
                if (cur.nodes[ch[j]-'a'] == null) {
                    flag = true;
                    break;
                } 
                cur = cur.nodes[ch[j]-'a'];
            }
            if (!flag)
                helper(cur, new StringBuilder(), i, false, used, res);
        } 
        return res;
    }
    
    public void helper(Node node, StringBuilder sb, int index, boolean front, 
                                                boolean[][] used, List<List<Integer>> res) {
        if (node.end && index != node.index) {
            String origin = sb.toString();
            sb.reverse();
            if (origin.equals(sb.toString())) {
                if (front ? !used[index][node.index] : !used[node.index][index]) {
                    res.add(front ? Arrays.asList(index, node.index) : 
                                    Arrays.asList(node.index, index));
                    used[front ? index : node.index][front ? node.index : index] = true;
                }
            }
            sb.reverse();
        }
        for (int i = 0; i < node.nodes.length; i++) {
            if (node.nodes[i] != null) {
                sb.append((char)('a' + i));
                helper(node.nodes[i], sb, index, front, used, res);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
    
    class Node {
        Node[] nodes = new Node[26];
        boolean end;
        int index;
    }
}

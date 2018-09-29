class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new LinkedList<>();
        Node root = new Node();
        for (String w : words) {
            Node cur = root;
            for (char c : w.toCharArray()) {
                if (cur.nodes[c-'a'] == null)
                    cur.nodes[c-'a'] = new Node();
                cur = cur.nodes[c-'a'];
            }
            cur.end = true;
        }
        for (String w : words) {
            char[] ch = w.toCharArray();
            if (helper(root, ch, 0))
                res.add(w);
        }
        return res;
    }
    
    public boolean helper(Node root, char[] ch, int index) {
        Node cur = root;
        int origin = index;
        while (index < ch.length) {
            if (cur.nodes[ch[index]-'a'] == null)
                return false;
            cur = cur.nodes[ch[index++]-'a'];
            if (cur.end && index != ch.length && helper(root, ch, index))
                return true;
        }
        return origin != 0 && cur.end ? true : false;
    }
    
    class Node {
        Node[] nodes = new Node[26];
        boolean end;
    }
}

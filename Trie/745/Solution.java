class WordFilter {
    
    Node root;
    public WordFilter(String[] words) {
        root = new Node();
        for (int i = 0; i < words.length; i++) {
            String str = words[i];
            char[] ch = str.toCharArray();
            Node cur = root;
            cur.map.put(str, i);
            for (int j = 0; j < ch.length; j++) {
                if (cur.nodes[ch[j]-'a'] == null)
                    cur.nodes[ch[j]-'a'] = new Node();
                cur = cur.nodes[ch[j]-'a'];
                cur.map.put(str.substring(j+1), i);
            }
            cur.end = true;
            cur.weight = i;
        }
    }
    
    public int f(String prefix, String suffix) {
        int res = -1;
        Node cur = root;
        for (char c : prefix.toCharArray()) {
            if (cur.map.containsKey(suffix))
                res = Math.max(res, cur.map.get(suffix));
            if (cur.nodes[c-'a'] == null)
                return -1;
            cur = cur.nodes[c-'a'];
        }
        return Math.max(res, helper(cur, suffix));
    }
    
    public int helper(Node node, String suffix) {
        int res = -1;
        if (node.map.containsKey(suffix))
            res = node.map.get(suffix);
        for (int i = 0; i < node.nodes.length; i++) {
            if (node.nodes[i] != null)
                res = Math.max(res, helper(node.nodes[i], suffix));
        }
        return res;
    }
    
    class Node {
        Node[] nodes = new Node[26];
        boolean end;
        int weight;
        Map<String, Integer> map = new HashMap<>();
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */

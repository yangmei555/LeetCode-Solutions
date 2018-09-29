class MagicDictionary {

    /** Initialize your data structure here. */
    Node root;
    public MagicDictionary() {
        root = new Node();
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String str : dict) {
            Node cur = root;
            for (char c : str.toCharArray()) {
                if (cur.nodes[c-'a'] == null)
                    cur.nodes[c-'a'] = new Node();
                cur = cur.nodes[c-'a'];
            }
            cur.end = true;
        }
    }
    
    /** Returns if there is any word in the trie that equals to the given word after 
        modifying exactly one character */
    public boolean search(String word) {
        char[] ch = word.toCharArray();
        Node cur = root;
        for (int i = 0; i < ch.length; i++) {
            for (int j = 0; j < cur.nodes.length; j++) {
                if (cur.nodes[j] != null && j != ch[i] - 'a') {
                    Node node = cur.nodes[j];
                    int k = i + 1;
                    for (; k < ch.length; k++) {
                        if (node.nodes[ch[k]-'a'] == null)
                            break;
                        node = node.nodes[ch[k]-'a'];
                    }
                    if (k == ch.length && node.end)
                        return true;
                }
            }
            if (cur.nodes[ch[i]-'a'] == null)
                return false;
            cur = cur.nodes[ch[i]-'a'];
        }
        return false;
    }
    
    class Node {
        Node[] nodes = new Node[26];
        boolean end;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */

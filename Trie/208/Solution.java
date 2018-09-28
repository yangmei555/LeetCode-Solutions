class Trie {

    /** Initialize your data structure here. */
    Node root;
    public Trie() {
        root = new Node();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (cur.children[c-'a'] == null)
                cur.children[c-'a'] = new Node();
            cur = cur.children[c-'a'];
        }
        cur.end = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (cur.children[c-'a'] == null)
                return false;
            cur = cur.children[c-'a'];
        }
        return cur.end;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node cur = root;
        for (char c : prefix.toCharArray()) {
            if (cur.children[c-'a'] == null)
                return false;
            cur = cur.children[c-'a'];
        }
        return true;
    }
    
    class Node {
        Node[] children;
        boolean end;
        public Node() {
            children = new Node[26];
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

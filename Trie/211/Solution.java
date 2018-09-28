class WordDictionary {

    /** Initialize your data structure here. */
    Node root;
    public WordDictionary() {
        root = new Node();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (cur.nodes[c-'a'] == null)
                cur.nodes[c-'a'] = new Node();
            cur = cur.nodes[c-'a'];
        }
        cur.end = true;
    }
    
    /** Returns if the word is in the data structure. 
        A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return helper(word.toCharArray(), 0, root);
    }
    
    public boolean helper(char[] ch, int index, Node node) {
        if (index == ch.length)
            return node.end;
        if (ch[index] == '.') {
            for (int i = 0; i < node.nodes.length; i++) {
                if (node.nodes[i] != null && helper(ch, index+1, node.nodes[i]))
                    return true;
            }
        } else {
            if (node.nodes[ch[index]-'a'] != null)
                return helper(ch, index+1, node.nodes[ch[index]-'a']);
        }
        return false;
    }
    
    class Node {
        Node[] nodes = new Node[26];
        boolean end;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

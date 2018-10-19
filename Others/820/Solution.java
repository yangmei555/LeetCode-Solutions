class Solution {
    public int minimumLengthEncoding(String[] words) {
        Node root = new Node();
        for (String str : words) {
            char[] ch = str.toCharArray();
            Node cur = root;
            for (int i = ch.length-1; i >= 0; i--) {
                if (cur.nodes[ch[i]-'a'] == null)
                    cur.nodes[ch[i]-'a'] = new Node();
                cur = cur.nodes[ch[i]-'a'];
            }
        }
        return helper(root, 0);
    }
    
    public int helper(Node cur, int level) {
        int res = 0;
        for (Node node : cur.nodes) {
            if (node != null)
                res += helper(node, level+1);
        }
        return res == 0 ? level+1 : res;
    }
    
    class Node {
        Node[] nodes = new Node[26];
    }
}


// another solution using Trie, one pass. "end = true" means it has been added to the result 
class Solution {
    public int minimumLengthEncoding(String[] words) {
        Node root = new Node();
        int res = 0;
        for (String str : words) {
            char[] ch = str.toCharArray();
            Node cur = root;
            for (int i = ch.length-1; i > 0; i--) {
                if (cur.nodes[ch[i]-'a'] == null)
                    cur.nodes[ch[i]-'a'] = new Node();
                cur = cur.nodes[ch[i]-'a'];
                if (cur.end) {
                    res -= ch.length - i + 1;
                    cur.end = false;
                }
            }
            if (cur.nodes[ch[0]-'a'] == null) {
                cur.nodes[ch[0]-'a'] = new Node();
                cur.nodes[ch[0]-'a'].end = true;
                res += ch.length + 1;
            }
        }
        return res;
    }
    
    class Node {
        Node[] nodes = new Node[26];
        boolean end = false;
    }
}


// remove suffix one by one 
class Solution {
    public int minimumLengthEncoding(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (int i = 0; i < words.length; i++) {
            for (int j = 1; j < words[i].length(); j++)
                set.remove(words[i].substring(j));
        }
        int res = 0;
        for (String str : set)
            res += str.length() + 1;
        return res;
    }
}

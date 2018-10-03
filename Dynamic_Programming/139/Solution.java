class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        return helper(s, wordDict, set);
    }
    
    public boolean helper(String s, List<String> list, Set<String> set) {
        if (s.equals(""))
            return true;
        set.add(s);
        int i = 0, len = list.size();
        for ( ; i < len; i++) {
            if (s.indexOf(list.get(i)) == 0) {
                if (set.contains(s.substring(list.get(i).length())))
                    continue;
                if (helper(s.substring(list.get(i).length()), list, set))
                    return true;
            }
        }
        return false;
    }
}


class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] index = new boolean[s.length() + 1];
        index[s.length()] = true;
        for (int i = s.length() - 1; i >= 0; i--) {
            String sub = s.substring(i);
            for (String w : wordDict) {
                if (sub.indexOf(w) == 0 && index[i + w.length()]) {
                    index[i] = true;
                    break;
                }
            }
        }
        return index[0];
    }
}


// using Trie 
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Node root = new Node();
        for (String str : wordDict) {
            Node cur = root;
            for (char c : str.toCharArray()) {
                if (cur.nodes[c-'a'] == null) 
                    cur.nodes[c-'a'] = new Node();
                cur = cur.nodes[c-'a'];
            }
            cur.end = true;
        }
        return helper(root, s.toCharArray(), 0, new boolean[s.length()]);
    }
    
    public boolean helper(Node node, char[] ch, int index, boolean[] visited) {
        if (index == ch.length)
            return true;
        if (visited[index])
            return false;
        Node cur = node;
        for (int i = index; i < ch.length; i++) {
            if (cur.nodes[ch[i]-'a'] == null)
                break;
            cur = cur.nodes[ch[i]-'a'];
            if (cur.end && helper(node, ch, i+1, visited))
                return true;
        }
        visited[index] = true;
        return false;
    }
    
    public class Node {
        Node[] nodes = new Node[26];
        boolean end;
    }
}

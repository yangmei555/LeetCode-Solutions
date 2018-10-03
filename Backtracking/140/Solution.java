class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
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
        char[] ch = s.toCharArray();
        List<String>[] visited = new List[ch.length];
        return helper(ch, root, 0, new StringBuilder(), visited);
        
    }
    
    public List<String> helper(char[] ch, Node root, int index, StringBuilder sb, 
                                                            List<String>[] visited) {
        List<String> res = new LinkedList<>();
        if (index == ch.length) {
            res.add("");
            return res;
        }
        if (visited[index] != null)
            return visited[index];
        Node cur = root;
        int len = sb.length();
        for (int i = index; i < ch.length; i++) {
            if (cur.nodes[ch[i]-'a'] == null)
                break;
            cur = cur.nodes[ch[i]-'a'];
            sb.append(ch[i]);
            if (cur.end) {
                sb.append(' ');
                List<String> ret = helper(ch, root, i+1, new StringBuilder(), visited);
                for (String str : ret) {
                    if (str.length() == 0)
                        res.add(sb.substring(0, sb.length()-1));
                    else
                        res.add(sb.toString() + str);
                }
                sb.deleteCharAt(sb.length()-1);
            }
        }
        sb.setLength(len);
        visited[index] = res;
        return res;
    }
        
    class Node {
        Node[] nodes = new Node[26];
        boolean end;
    }
}

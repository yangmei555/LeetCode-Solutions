// basically hash map plus trie 
class Solution {
    public List<String> wordsAbbreviation(List<String> dict) {
        Map<String, List<Integer>> strToIndex = new HashMap<>();
        String[] res = new String[dict.size()];
        for (int i = 0; i < dict.size(); i++) {
            String str = dict.get(i);
            if (str.length() > 3) {
                String abbr = str.charAt(0) + (str.length()-2 + "") + str.charAt(str.length()-1);
                strToIndex.putIfAbsent(abbr, new LinkedList<>());
                strToIndex.get(abbr).add(i);
            }
        }
        for (String key : strToIndex.keySet()) {
            List<Integer> list = strToIndex.get(key);
            if (list.size() == 1) {
                res[list.get(0)] = key;
            } else {
                helper(dict, list, res);
            }
        }
        List<String> ret = new LinkedList<>();
        for (int i = 0; i < dict.size(); i++)
            ret.add(res[i] == null ? dict.get(i) : res[i]);
        return ret;
    }
    
    public void helper(List<String> dict, List<Integer> list, String[] res) {
        Node root = new Node(-1, 0);
        for (int index : list) {
            char[] ch = dict.get(index).toCharArray();
            Node cur = root;
            root.unique = false;
            for (int i = 0; i < ch.length; i++) {
                if (cur.nodes[ch[i]-'a'] == null)
                    cur.nodes[ch[i]-'a'] = new Node(index, i+1);
                else
                    cur.nodes[ch[i]-'a'].unique = false;
                cur = cur.nodes[ch[i]-'a'];
            }
        }
        dfs(root, res, dict, dict.get(list.get(0)).length());
    }
    
    public void dfs(Node node, String[] res, List<String> dict, int len) {
        if (node.len == len-3 || node.unique) { 
            if (node.unique) {
                String str = dict.get(node.index);
                res[node.index] = str.substring(0, node.len) + (str.length()-node.len-1) + 
                                    str.charAt(str.length()-1);
            } 
        } else {
            for (Node child : node.nodes) {
                if (child != null)
                    dfs(child, res, dict, len);
            }
        }
    }
    
    class Node {
        Node[] nodes = new Node[26];
        boolean unique = true;
        int index, len;
        public Node(int index, int len) {
            this.index = index;
            this.len = len;
        }
    }
}

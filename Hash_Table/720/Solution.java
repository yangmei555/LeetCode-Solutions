class Solution {
    public String longestWord(String[] words) {
        Set<String> set = new HashSet<>();
        for (String s : words) {
            set.add(s);
        }
        int res = 0;
        String str = null;
        for (String s : words) {
            int i = 1;
            for (; i < s.length(); i++) {
                if (set.contains(s.substring(0, i)) == false)
                    break;
            }
            if (i == s.length()) {
                if (s.length() > res) {
                    res = s.length();
                    str = s;
                } else if (s.length() == res) {
                    if (str.compareTo(s) > 0)
                        str = s;
                }
            }
        }
        return str;
    }
}


class Solution {
    public String longestWord(String[] words) {
        Set<String> set = new HashSet<>();
        for (String s : words) {
            set.add(s);
        }
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : words) {
            if (set.contains(s.substring(0, s.length() - 1))) {
                List<String> list = null;
                if (map.containsKey(s.substring(0, s.length() - 1)))
                    list = map.get(s.substring(0, s.length() - 1));
                else {
                    list = new LinkedList<>();
                    map.put(s.substring(0, s.length() - 1), list)
                }
                list.add(s);
            }
        }
        String res = "", str;
        for (String s : set) {
            if (s.length() == 1) {
                str = helper(map, s);
                if (str.length() > res.length())
                    res = str;
                else if (str.length() == res.length() && str.compareTo(res) < 0)
                    res = str;
            }
        }
        return res;
    }

    public String helper(HashMap<String, List<String>> map, String s) {
        if (map.containsKey(s) == false)
            return s;
        List<String> list = map.get(s);
        String res = "", str;
        for (String string : list) {
            str = helper(map, string);
            if (str.length() > res.length())
                res = str;
            else if (str.length() == res.length() && str.compareTo(res) < 0)
                res = str;
        }
        return res;
    }
}


class Solution {
    public String longestWord(String[] words) {
        Arrays.sort(words);
        HashSet<String> set = new HashSet<>();
        String str = "";
        set.add("");
        for (String s : words) {
            if (set.contains(s.substring(0, s.length() - 1))) {
                set.add(s);
                if (s.length() > str.length())
                    str = s;
            }
        }
        return str;
    }
}


// using prefix tree 
class Solution {
    String res;
    public String longestWord(String[] words) {
        Node root = new Node();
        res = "";
        for (String str : words) {
            char[] ch = str.toCharArray();
            Node cur = root;
            for (char c : ch) {
                if (cur.nodes[c-'a'] == null)
                    cur.nodes[c-'a'] = new Node();
                cur = cur.nodes[c-'a'];
            }
            cur.end = true;
        }
        StringBuilder sb = new StringBuilder();
        helper(root, sb);
        return res;
    }
    
    public void helper(Node node, StringBuilder sb) {
        if (sb.length() > res.length())
            res = sb.toString();
        for (int i = 0; i < node.nodes.length; i++) {
            if (node.nodes[i] != null && node.nodes[i].end) {
                sb.append((char)('a' + i));
                helper(node.nodes[i], sb);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
    
    class Node {
        Node[] nodes = new Node[26];
        boolean end;
    }
}

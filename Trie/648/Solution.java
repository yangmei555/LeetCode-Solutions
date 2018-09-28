class Solution {
    public String replaceWords(List<String> dict, String sentence) {
        Node root = new Node();
        for (String str : dict) {
            Node cur = root;
            for (char c : str.toCharArray()) {
                if (cur.nodes[c-'a'] == null)
                    cur.nodes[c-'a'] = new Node();
                cur = cur.nodes[c-'a'];
            }
            cur.end = true;
        }
        String[] strs = sentence.split(" ");
        for (int i = 0; i < strs.length; i++) {
            Node cur = root;
            for (int j = 0; j < strs[i].length(); j++) {
                cur = cur.nodes[strs[i].charAt(j)-'a'];
                if (cur == null)
                    break;
                if (cur.end) {
                    strs[i] = strs[i].substring(0, j+1);
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strs)
            sb.append(str).append(' ');
        return sb.substring(0, sb.length()-1);
    }
    
    class Node {
        Node[] nodes = new Node[26];
        boolean end;
    }
}

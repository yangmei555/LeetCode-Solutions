class AutocompleteSystem {
    
    Node root, cur;
    StringBuilder sb;
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new Node();
        cur = root;
        sb = new StringBuilder();
        for (int i = 0; i < sentences.length; i++) {
            String str = sentences[i];
            Node node = root;
            for (char c : str.toCharArray()) {
                if (node.nodes[c-'a' < 0 ? 26 : c-'a'] == null)
                    node.nodes[c-'a' < 0 ? 26 : c-'a'] = new Node();
                node = node.nodes[c-'a' < 0 ? 26 : c-'a'];
            }
            node.end = true;
            node.freq = times[i];
        }
    }
    
    public List<String> input(char c) {
        List<String> res = new LinkedList<>();
        if (c == '#') {
            sb.setLength(0);
            cur.end = true;
            cur.freq++;
            cur = root;
            return res;
        } else {
            sb.append(c);
            if (cur.nodes[c-'a' < 0 ? 26 : c-'a'] == null) {
                cur.nodes[c-'a' < 0 ? 26 : c-'a'] = new Node();
                cur = cur.nodes[c-'a' < 0 ? 26 : c-'a'];
            } else {
                cur = cur.nodes[c-'a' < 0 ? 26 : c-'a'];
                String[] strs = new String[3];
                int[] times = new int[3];
                Node node = cur;
                helper(node, sb, strs, times);
                for (String str : strs) {
                    if (str != null)
                        res.add(str);
                }
            }
            return res;
        }
    }
    
    public void helper(Node node, StringBuilder sb, String[] strs, int[] times) {
        if (node.end) {
            if (node.freq > times[0] || 
                node.freq == times[0] && strs[0].compareTo(sb.toString()) > 0) {
                strs[2] = strs[1];
                strs[1] = strs[0];
                strs[0] = sb.toString();
                times[2] = times[1];
                times[1] = times[0];
                times[0] = node.freq;
            } else if (node.freq > times[1] || 
                        node.freq == times[1] && strs[1].compareTo(sb.toString()) > 0) {
                strs[2] = strs[1];
                strs[1] = sb.toString();
                times[2] = times[1];
                times[1] = node.freq;
            } else if (node.freq > times[2] || 
                        node.freq == times[2] && strs[2].compareTo(sb.toString()) > 0) {
                strs[2] = sb.toString();
                times[2] = node.freq;
            }
        }
        for (int i = 0; i < node.nodes.length; i++) {
            if (node.nodes[i] != null) {
                sb.append(i == 26 ? ' ' : (char)('a' + i));
                helper(node.nodes[i], sb, strs, times);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
    
    class Node {
        Node[] nodes = new Node[27];
        boolean end;
        int freq;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */

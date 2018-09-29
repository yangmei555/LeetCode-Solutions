// use hash map and bucket sort (counting sort actually) 
// but there will be a hard core sort inside each bucket to ensure the lexicographical order 
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String w : words)
            map.put(w, map.getOrDefault(w, 0) + 1);
        List<String>[] maps = new List[words.length + 1];
        for (String str : map.keySet()) {
            int v = map.get(str);
            if (maps[v] == null)
                maps[v] = new LinkedList<>();
            maps[v].add(str);
        }
        for (List<String> list : maps) {
            if (list != null)
                Collections.sort(list);
        }
        List<String> res = new LinkedList<>();
        int index = maps.length-1;
        while (true) {
            if (maps[index] != null) {
                if (k > maps[index].size()) {
                    res.addAll(maps[index]);
                    k -= maps[index].size();
                } else {
                    for (int i = 0; i < k; i++)
                        res.add(maps[index].get(i));
                    break;
                }
            }
            index--;
        }
        return res;
    }
}


// use a trie to store all strings which have the same frequency 
// the words encountered during searching are under lexicographical order, so no need to sort 
// but this method does not bring significant performance gains 
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String w : words)
            map.put(w, map.getOrDefault(w, 0) + 1);
        Node[] roots = new Node[words.length + 1];
        for (int i = 0; i < roots.length; i++)
            roots[i] = new Node();
        for (String str : map.keySet()) {
            int v = map.get(str);
            Node cur = roots[v];
            for (char c : str.toCharArray()) {
                if (cur.nodes[c-'a'] == null)
                    cur.nodes[c-'a'] = new Node();
                cur = cur.nodes[c-'a'];
            }
            cur.end = true;
        }
        List<String> res = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = roots.length-1; i >= 0; i--) {
            helper(roots[i], sb, res, k);
            if (res.size() == k)
                break;
        }
        return res;
    }
    
    public void helper(Node node, StringBuilder sb, List<String> res, int k) {
        if (node.end) {
            res.add(sb.toString());
            if (res.size() == k)
                return;
        }
        for (int i = 0; i < node.nodes.length; i++) {
            if (node.nodes[i] != null) {
                sb.append((char)('a' + i));
                helper(node.nodes[i], sb, res, k);
                if (res.size() == k)
                    return;
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
    
    class Node {
        Node[] nodes = new Node[26];
        boolean end;
    }
}

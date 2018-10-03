// hash map and union find 
// actually no need to build a node for each string to build up the union find 
class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length)
            return false;
        Map<String, Node> map = new HashMap<>();
        for (String[] p : pairs) {
            if (!map.containsKey(p[0])) {
                Node node = new Node(p[0], 0);
                node.parent = node;
                map.put(p[0], node);
            }
            if (!map.containsKey(p[1])) {
                Node node = new Node(p[1], 0);
                node.parent = node;
                map.put(p[1], node);
            }
            Node node1 = map.get(p[0]), node2 = map.get(p[1]);
            union(node1, node2);
        }
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i]))
                continue;
            if (!map.containsKey(words1[i]) || !map.containsKey(words2[i]))
                return false;
            if (!findSet(map.get(words1[i])).str.equals(findSet(map.get(words2[i])).str))
                return false;
        }
        return true;
    }
    
    class Node {
        String str;
        Node parent;
        int rank;
        public Node(String str, int rank) {
            this.str = str;
            this.rank = rank;
        }
    }
    
    public void union(Node n1, Node n2) {
        Node x = findSet(n1), y = findSet(n2);
        if (!x.str.equals(y.str))
            link(x, y);
    }
    
    public Node findSet(Node node) {
        while (node.parent != node) {
            node.parent = node.parent.parent;
            node = node.parent;
        }
        return node;
    }
    
    public void link(Node n1, Node n2) {
        if (n1.rank > n2.rank) {
            n2.parent = n1;
        } else if (n1.rank < n2.rank) {
            n1.parent = n2;
        } else {
            n1.parent = n2;
            n2.rank++;
        }
    }
}


// a more concise way of union find 
class Solution {
    Map<String, String> map = new HashMap<>();
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length)
            return false;
        for (String[] p : pairs) {
            if (!map.containsKey(p[0]))
                map.put(p[0], p[0]);
            if (!map.containsKey(p[1]))
                map.put(p[1], p[1]);
            if (!find(p[0]).equals(find(p[1])))
                map.put(find(p[0]), find(p[1]));
        }
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i]))
                continue;
            if (!map.containsKey(words1[i]) || !map.containsKey(words2[i]) || 
                !find(words1[i]).equals(find(words2[i]))) 
                return false;
        }
        return true;
    }
    
    public String find(String str) {
        if (!map.get(str).equals(str))
            map.put(str, find(map.get(str)));
        return map.get(str);
    }
}


// a more more concise way of union find , no path compression or union by rank 
class Solution {
    Map<String, String> map = new HashMap<>();
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length)
            return false;
        for (String[] p : pairs) {
            if (!find(p[0]).equals(find(p[1])))
                map.put(find(p[0]), find(p[1]));
        }
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i]))
                continue;
            if (!find(words1[i]).equals(find(words2[i]))) 
                return false;
        }
        return true;
    }
    
    public String find(String str) {
        if (map.get(str) == null)
            return str;
        return find(map.get(str));
    }
}


// do a DFS for each pair of words 
class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length)
            return false;
        Map<String, List<String>> map = new HashMap<>();
        for (String[] str : pairs) {
            map.putIfAbsent(str[0], new LinkedList<>());
            map.putIfAbsent(str[1], new LinkedList<>());
            map.get(str[0]).add(str[1]);
            map.get(str[1]).add(str[0]);
        }
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i]))
                continue;
            if (!map.containsKey(words1[i]) || !map.containsKey(words2[i]))
                return false;
            Set<String> visited = new HashSet<>();
            helper(map, words1[i], visited);
            if (!visited.contains(words2[i]))
                return false;
        }
        return true;
    }
    
    public void helper(Map<String, List<String>> map, String str, Set<String> visited) {
        visited.add(str);
        for (String s : map.get(str)) {
            if (!visited.contains(s))
                helper(map, s, visited);
        }
    }
}


// same idea with above, but return immediately when the target is found 
class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length)
            return false;
        Map<String, List<String>> map = new HashMap<>();
        for (String[] str : pairs) {
            map.putIfAbsent(str[0], new LinkedList<>());
            map.putIfAbsent(str[1], new LinkedList<>());
            map.get(str[0]).add(str[1]);
            map.get(str[1]).add(str[0]);
        }
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i]))
                continue;
            if (!map.containsKey(words1[i]) || !map.containsKey(words2[i]))
                return false;
            Set<String> visited = new HashSet<>();
            if (!helper(map, words1[i], words2[i], visited))
                return false;
        }
        return true;
    }
    
    public boolean helper(Map<String, List<String>> map, String str, String target, 
                                                                        Set<String> visited) {
        if (str.equals(target))
            return true;
        visited.add(str);
        for (String s : map.get(str)) {
            if (!visited.contains(s) && helper(map, s, target, visited))
                return true;
        }
        return false;
    }
}


// give each connected component an id ! 
class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length)
            return false;
        Map<String, List<String>> map = new HashMap<>();
        for (String[] str : pairs) {
            map.putIfAbsent(str[0], new LinkedList<>());
            map.putIfAbsent(str[1], new LinkedList<>());
            map.get(str[0]).add(str[1]);
            map.get(str[1]).add(str[0]);
        }
        int id = 0;
        Map<String, Integer> cc = new HashMap<>();
        for (String str : map.keySet()) {
            if (!cc.containsKey(str)) 
                helper(map, cc, str, id);
            id++;
        }
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i]))
                continue;
            if (!cc.containsKey(words1[i]) || !cc.containsKey(words2[i]) || 
                    cc.get(words1[i]) != cc.get(words2[i]))
                return false;
        }
        return true;
    }
    
    public void helper(Map<String, List<String>> map, Map<String, Integer> cc, String str, int id) {
        cc.put(str, id);
        for (String s : map.get(str)) {
            if (!cc.containsKey(s))
                helper(map, cc, s, id);
        }
    }
}

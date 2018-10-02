// hash map and union find 
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

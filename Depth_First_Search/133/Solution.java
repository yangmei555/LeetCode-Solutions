// DFS 
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        return helper(node, map);
    }
    
    public UndirectedGraphNode helper(UndirectedGraphNode node, 
                                        Map<UndirectedGraphNode, UndirectedGraphNode> map) {
        if (node == null)
            return null;
        if (map.containsKey(node))
            return map.get(node);
        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        map.put(node, copy);
        for (int i = 0; i < node.neighbors.size(); i++)
            copy.neighbors.add(helper(node.neighbors.get(i), map));
        return copy;
    }
}


// BFS 
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null)
            return null;
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        map.put(node, new UndirectedGraphNode(node.label));
        queue.offer(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode n = queue.poll();
            for (UndirectedGraphNode u : n.neighbors) {
                if (!map.containsKey(u)) {
                    map.put(u, new UndirectedGraphNode(u.label));
                    queue.offer(u);
                }
                map.get(n).neighbors.add(map.get(u));
            }
        }
        return map.get(node);
    }
}

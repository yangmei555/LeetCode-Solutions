class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        // states[i] == 0 means unvisited 
        // states[i] == 1 means i is visited and is on a cycle 
        // states[i] == 2 means i is finished and is not on a cycle 
        int[] states = new int[graph.length];
        for (int i = 0; i < states.length; i++) {
            if (states[i] == 0)
                helper(i, graph, states);
        }
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < states.length; i++) {
            if (states[i] == 2)
                res.add(i);
        }
        return res;
    }
    
    public boolean helper(int u, int[][] graph, int[] states) {
        states[u] = 1;
        for (int v : graph[u]) {
            if (states[v] == 0) {
                if (!helper(v, graph, states))
                    return false;
            } else if (states[v] == 1) {
                return false;
            }
        }
        states[u] = 2;
        return true;
    }
}


// use a queue to contain all vertices with 0 outdegree and gradually remove edges 
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer>[] adj = new List[graph.length];
        for (int i = 0; i < adj.length; i++)
            adj[i] = new LinkedList<>();
        int[] outdegrees = new int[graph.length];
        for (int u = 0; u < graph.length; u++) {
            for (int v = 0; v < graph[u].length; v++) 
                adj[graph[u][v]].add(u);
            outdegrees[u] = graph[u].length;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < outdegrees.length; i++) {
            if (outdegrees[i] == 0)
                queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj[u]) {
                if (outdegrees[v] != 0) {
                    outdegrees[v]--;
                    if (outdegrees[v] == 0)
                        queue.offer(v);
                }
            }
        }
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < outdegrees.length; i++) {
            if (outdegrees[i] == 0)
                res.add(i);
        }
        return res;
    }
}

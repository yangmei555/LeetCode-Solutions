class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < adj.length; i++)
            adj[i] = new LinkedList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        int[] dist = new int[n], pi = new int[n];
        // first find the diameter of the tree, refer to CLRS 22.2-8 
        // the mid point(s) on that diameter is what we want 
        int max = helper(adj, dist, pi, 0);
        max = helper(adj, dist, pi, max);
        List<Integer> res = new LinkedList<>();
        int count = 0, v = max;
        while (v != -1) {
            if (count == dist[max]/2 || count == (dist[max]+1)/2)
                res.add(v);
            else if (count > (dist[max]+1)/2)
                break;
            v = pi[v];
            count++;
        }
        return res;
    }
    
    public int helper(List<Integer>[] adj, int[] dist, int[] pi, int root) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[root] = 0;
        pi[root] = -1;
        int max = root;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj[u]) {
                if (dist[v] == Integer.MAX_VALUE) {
                    dist[v] = dist[u] + 1;
                    pi[v] = u;
                    queue.offer(v);
                }
            }
            if (dist[u] > dist[max])
                max = u;
        }
        return max;
    }
}


// new idea, BFS, remove leaves level by level 
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer>[] adj = new List[n];
        int[] degree = new int[n];
        for (int i = 0; i < adj.length; i++)
            adj[i] = new LinkedList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
            degree[e[0]]++;
            degree[e[1]]++;
        }
        List<Integer> leaves = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1)
                leaves.add(i);
        }
        while (n > 2) {
            n -= leaves.size();
            List<Integer> newleaves = new LinkedList<>();
            for (int u : leaves) {
                degree[u]--;
                for (int v : adj[u]) {
                    if (degree[v] != 0) {
                        degree[v]--;
                        if (degree[v] == 1)
                            newleaves.add(v);
                    }
                }
            }
            leaves = newleaves;
        }
        if (leaves.size() == 0)
            leaves.add(0);
        return leaves;
    }
}


// an amazing idea is to use XOR to store adjacency list 
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        int[] adj = new int[n], degree = new int[n];
        for (int[] e : edges) {
            adj[e[0]] ^= e[1];
            adj[e[1]] ^= e[0];
            degree[e[0]]++;
            degree[e[1]]++;
        }
        List<Integer> leaves = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1)
                leaves.add(i);
        }
        while (n > 2) {
            n -= leaves.size();
            List<Integer> newleaves = new LinkedList<>();
            for (int u : leaves) {
                degree[u]--;
                int v = adj[u];
                // remove incident nodes by XOR 
                adj[v] ^= u;
                if (degree[v] != 0) {
                    degree[v]--;
                    if (degree[v] == 1)
                        newleaves.add(v);
                }
            }
            leaves = newleaves;
        }
        if (leaves.size() == 0)
            leaves.add(0);
        return leaves;
    }
}

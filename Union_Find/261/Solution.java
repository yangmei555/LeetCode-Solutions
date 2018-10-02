// DFS 
class Solution {
    public boolean validTree(int n, int[][] edges) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < adj.length; i++)
            adj[i] = new LinkedList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        parent[0] = -1;
        if (!helper(adj, 0, visited, parent))
            return false;
        for (boolean v : visited) {
            if (!v)
                return false;
        }
        return true;
    }
    
    public boolean helper(List<Integer>[] adj, int node, boolean[] visited, int[] parent) {
        visited[node] = true;
        for (int v : adj[node]) {
            if (!visited[v]) {
                parent[v] = node;
                if (!helper(adj, v, visited, parent))
                    return false;
            } else if (parent[node] != v) {
                return false;
            }
        }
        return true;
    }
}


// Union Find 
class Solution {
    public boolean validTree(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++)
            uf.makeSet(i);
        for (int[] e : edges) {
            if (uf.findSet(e[0]) == uf.findSet(e[1]))
                return false;
            uf.union(e[0], e[1]);
        }
        return uf.count == 1;
    }
    
    class UnionFind {
        int[] parent;
        int[] rank;
        int count;
        
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            count = 0;
        }
        
        public void makeSet(int i) {
            parent[i] = i;
            rank[i] = 0;
            count++;
        }
        
        public int findSet(int i) {
            while (i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return i;
        }
        
        public void union(int i, int j) {
            link(findSet(i), findSet(j));
        }
        
        public void link(int x, int y) {
            if (rank[x] > rank[y]) {
                parent[y] = x;
            } else if (rank[x] < rank[y]) {
                parent[x] = y;
            } else {
                parent[x] = y;
                rank[y]++;
            }
            count--;
        }
    }
}

class Solution {
    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] e : edges) {
            if (uf.findSet(e[0]) != uf.findSet(e[1]))
                uf.union(e[0], e[1]);
        }
        return uf.count;
    }
    
    class UnionFind {
        int[] parent, rank;
        int count;
        
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++)
                parent[i] = i;
            count = n;
        }
        
        public int findSet(int i) {
            if (i != parent[i]) 
                parent[i] = findSet(parent[i]);
            return parent[i];
        }
        
        public void union(int i, int j) {
            link(findSet(i), findSet(j));
            count--;
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
        }
    }
}


// DFS 
class Solution {
    public int countComponents(int n, int[][] edges) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < adj.length; i++) 
            adj[i] = new LinkedList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        boolean[] visited = new boolean[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                helper(adj, i, visited);
                res++;
            }
        }
        return res;
    }
    
    public void helper(List<Integer>[] adj, int node, boolean[] visited) {
        visited[node] = true;
        for (int v : adj[node]) {
            if (!visited[v])
                helper(adj, v, visited);
        }
    }
}

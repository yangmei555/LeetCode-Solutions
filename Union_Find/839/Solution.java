class Solution {
    public int numSimilarGroups(String[] A) {
        List<Integer>[] adj = new List[A.length];
        char[][] ch = new char[A.length][];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList<>();
            ch[i] = A[i].toCharArray();
        }
        for (int i = 0; i < A.length; i++) {
            for (int j = i+1; j < A.length; j++) {
                int diff = 0;
                for (int k = 0; k < ch[i].length; k++) {
                    if (ch[i][k] != ch[j][k]) {
                        diff++;
                        if (diff == 3)
                            break;
                    }
                }
                if (diff == 0 || diff == 2) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        boolean[] visited = new boolean[A.length];
        int res = 0;
        for (int i = 0; i < A.length; i++) {
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

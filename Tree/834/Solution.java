// int[] count : how many nodes are under each node, when rooted at 0 
class Solution {
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        List<Integer>[] adj = new List[N];
        for (int i = 0; i < N; i++)
            adj[i] = new LinkedList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        int[] res = new int[N], count = new int[N];
        boolean[] visited = new boolean[N];
        countDown(adj, 0, res, count, visited);
        Arrays.fill(visited, false);
        countUp(adj, 0, count[0], res, count, visited);
        return res;
    }
    
    public void countDown(List<Integer>[] adj, int node, int[] res, int[] count, boolean[] visited) {
        visited[node] = true;
        for (int v : adj[node]) {
            if (!visited[v]) {
                countDown(adj, v, res, count, visited);
                count[node] += count[v];
                res[node] += res[v] + count[v];
            }
        }
        count[node]++;
    }
    
    public void countUp(List<Integer>[] adj, int node, int total, int[] res, int[] count, 
                                                                            boolean[] visited) {
        visited[node] = true;
        for (int v : adj[node]) {
            if (!visited[v]) {
                res[v] += res[node] - res[v] - count[v] + total - count[v];
                countUp(adj, v, total, res, count, visited);
            }
        }
    }
}

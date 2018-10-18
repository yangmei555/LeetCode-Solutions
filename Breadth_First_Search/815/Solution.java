class Solution {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        int max = Integer.MIN_VALUE;
        for (int[] ro : routes) {
            for (int r : ro)
                max = Math.max(max, r);
        }
        boolean[][] map = new boolean[routes.length][max+1];
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++)
                map[i][routes[i][j]] = true;
        }
        List<Integer>[] adj = new List[routes.length];
        for (int i = 0; i < adj.length; i++)
            adj[i] = new LinkedList<>();
        boolean[] visited = new boolean[routes.length];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < routes.length; i++) {
            if (map[i][S]) {
                visited[i] = true;
                queue.offer(i);
                if (map[i][T])
                    return S == T ? 0 : 1;
            }
            for (int j = i+1; j < routes.length; j++) {
                for (int k = 0; k < Math.min(routes[i].length, routes[j].length); k++) {
                    if (map[j][routes[i][k]] || map[i][routes[j][k]]) {
                        adj[i].add(j);
                        adj[j].add(i);
                        break;
                    }
                }
            }
        }
        int dist = 0;
        while (!queue.isEmpty()) {
            dist++;
            for (int i = queue.size(); i > 0; i--) {
                int node = queue.poll();
                for (int v : adj[node]) {
                    if (!visited[v]) {
                        visited[v] = true;
                        queue.offer(v);
                        if (map[v][T])
                            return dist + 1;
                    }
                }
            }
        }
        return -1;
    }
}

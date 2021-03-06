class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        int[] dist = new int[N+1];
        Arrays.fill(dist, -1);
        dist[K] = 0;
        // just run Bellman-Ford  
        for (int i = 0; i < N-1; i++) {
            for (int[] t : times) {
                if (dist[t[0]] != -1 && (dist[t[1]] == -1 || dist[t[0]] + t[2] < dist[t[1]]))
                    dist[t[1]] = dist[t[0]] + t[2];
            }
        }
        int res = 0;
        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == -1)
                return -1;
            res = res > dist[i] ? res : dist[i];
        }
        return res;
    }
}


// Dijkstra 
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        List<int[]>[] adj = new List[N+1];
        for (int i = 0; i < adj.length; i++)
            adj[i] = new LinkedList<>();
        for (int t[] : times)
            adj[t[0]].add(new int[]{t[1], t[2]});
        int[] dist = new int[N+1];
        boolean[] finished = new boolean[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[1] - i2[1];
            }
        });

        // actually we don't need to add all nodes to the queue in this situation , 
        // in Java there is no build-in decrease key operation, so every time we relax a vertex 
        // we add it to the queue 

        // for (int i = 1; i <= N; i++) 
        //     queue.offer(new int[]{i, dist[i]});

        queue.offer(new int[]{K, 0});
        while (!queue.isEmpty()) {
            // int u = queue.poll()[0];
            // if (finished[u])
            //     continue;
            // else
            //     finished[u] = true;

            int[] node = queue.poll();
            int u = node[0];
            // decide whether vertex u has been dequeued before, 
            // whether this is the node before the decrease key operation on u 
            if (dist[u] < node[1]) 
                continue;

            for (int[] vw : adj[u]) {
                if (dist[u] + vw[1] < dist[vw[0]]) {
                    dist[vw[0]] = dist[u] + vw[1];
                    queue.offer(new int[]{vw[0], dist[vw[0]]});
                }
            }
        }
        int res = 0;
        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                return -1;
            else
                res = res > dist[i] ? res : dist[i];
        }
        return res;
    }
}


// running DFS directly , quite slow 
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        List<int[]>[] adj = new List[N+1];
        for (int i = 0; i < adj.length; i++)
            adj[i] = new LinkedList<>();
        for (int t[] : times)
            adj[t[0]].add(new int[]{t[1], t[2]});
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;
        helper(K, adj, dist);
        int res = 0;
        for (int i = 1; i <= N; i++) 
            res = res > dist[i] ? res : dist[i];
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    
    public void helper(int vertex, List<int[]>[] adj, int[] dist) {
        for (int[] vw : adj[vertex]) {
            if (dist[vertex] + vw[1] < dist[vw[0]]) {
                dist[vw[0]] = dist[vertex] + vw[1];
                helper(vw[0], adj, dist);
            }
        }
    }
}


// replace the PriorityQueue with array and replace adj list with adj matrix, but runs faster 
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        int[][] adj = new int[N+1][N+1];
        for (int i = 0; i < adj.length; i++)
            Arrays.fill(adj[i], Integer.MAX_VALUE);
        for (int[] t : times)
            adj[t[0]][t[1]] = t[2];
        int[] dist = new int[N+1];
        boolean[] finished = new boolean[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;
        boolean[] visited = new boolean[N+1];
        while (true) {
            int u = 0;
            for (int i = 1; i <= N; i++) {
                if (!visited[i] && dist[i] < dist[u])
                    u = i;
            }    
            if (u == 0)
                break;
            visited[u] = true;
            for (int v = 1; v <= N; v++) {
                if (adj[u][v] != Integer.MAX_VALUE && dist[u] + adj[u][v] < dist[v]) 
                    dist[v] = dist[u] + adj[u][v];
            }
        }
        int res = 0;
        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                return -1;
            else
                res = res > dist[i] ? res : dist[i];
        }
        return res;
    }
}

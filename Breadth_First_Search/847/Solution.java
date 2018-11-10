class Solution {
    public int shortestPathLength(int[][] graph) {
        if (graph.length < 2)
            return 0;
        int N = graph.length;
        int res = Integer.MAX_VALUE;
        Queue<Node> queue = new LinkedList<>();
        boolean[][] state = new boolean[N][1 << N];
        for (int i = 0; i < N; i++) {
            Node start = new Node(i, 1 << i);
            queue.offer(start);
            state[i][1 << i] = true;
        }
        int step = 0;
        boolean flag = false;
        while (!queue.isEmpty()) {
            for (int s = queue.size(); s > 0; s--) {
                Node node = queue.poll();
                if (node.visited == (1 << N)-1) 
                    return step;
                for (int v : graph[node.v]) {
                    Node newNode = new Node(v, node.visited | (1 << v));
                    if (!state[v][newNode.visited]) {
                        queue.offer(newNode);
                        state[v][newNode.visited] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }
    
    class Node {
        int v;
        int visited;
        public Node(int v, int visited) {
            this.v = v;
            this.visited = visited;
        }
    }
}


// dynamic programming approach 
class Solution {
    public int shortestPathLength(int[][] graph) {
        if (graph.length < 2)
            return 0;
        int N = graph.length;
        int[][] dp = new int[1 << N][N];
        for (int[] d : dp)
            Arrays.fill(d, Integer.MAX_VALUE);
        Arrays.fill(dp[0], -1);
        for (int state = 1; state < (1 << N); state++) {
            // for edges which are unvisited before 
            for (int i = 0; i < N; i++) {
                if ((state & (1 << i)) != 0) {
                    int preState = state & ~(1 << i);
                    for (int v : graph[i]) {
                        if (dp[preState][v] != Integer.MAX_VALUE) 
                            dp[state][i] = Math.min(dp[state][i], dp[preState][v] + 1);
                    }
                }
            }
            // for walking through visited edges 
            boolean flag = true;
            while (flag) {
                flag = false;
                for (int i = 0; i < N; i++) {
                    if ((state & (1 << i)) != 0) {
                        for (int v : graph[i]) {
                            if (dp[state][v] != Integer.MAX_VALUE && 
                                                            dp[state][i] > dp[state][v] + 1) {
                                flag = true;
                                dp[state][i] = Math.min(dp[state][i], dp[state][v] + 1);
                            }
                        }
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int d : dp[(1 << N)-1])
            res = Math.min(res, d);
        return res;
    }
}

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

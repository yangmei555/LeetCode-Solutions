class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] graph = new int[n][n];
        int[][][] memo = new int[n][n][K+1];
        for (int[] i : flights) 
            graph[i[0]][i[1]] = i[2];
        return helper(graph, src, dst, K, memo);
    }
    
    public int helper(int[][] graph, int src, int dst, int K, int[][][] memo) {
        if (src == dst)
            return 0;
        if (K == -1)
            return -1;
        if (memo[src][dst][K] != 0)
            return memo[src][dst][K];
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < graph[0].length; i++) {
            if (graph[src][i] != 0) {
                int ret = helper(graph, i, dst, K-1, memo);
                if (ret != -1)
                    res = res < graph[src][i]+ret ? res : graph[src][i]+ret;
            }
        }
        memo[src][dst][K] = (res == Integer.MAX_VALUE ? -1 : res);
        return memo[src][dst][K];
    }
}


class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][][] graph = new int[n][n][2], memo = new int[n][n][K+1];
        int[] pointers = new int[n];
        for (int[] i : flights) {
            graph[i[0]][pointers[i[0]]][0] = i[1];
            graph[i[0]][pointers[i[0]]][1] = i[2];
            pointers[i[0]]++;
        }
        return helper(graph, src, dst, K, pointers, memo);
    }
    
    public int helper(int[][][] graph, int src, int dst, int K, int[] pointers, int[][][] memo) {
        if (src == dst)
            return 0;
        if (K == -1)
            return -1;
        if (memo[src][dst][K] != 0)
            return memo[src][dst][K];
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < pointers[src]; i++) {    
            int ret = helper(graph, graph[src][i][0], dst, K-1, pointers, memo);
            if (ret != -1)
                res = res < graph[src][i][1]+ret ? res : graph[src][i][1]+ret;
        }
        memo[src][dst][K] = (res == Integer.MAX_VALUE ? -1 : res);
        return memo[src][dst][K];
    }
}


class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] cur = new int[n], pre = new int[n];
        Arrays.fill(cur, Integer.MAX_VALUE);
        Arrays.fill(pre, Integer.MAX_VALUE);
        pre[src] = 0;
        for (int t = 0; t <= K; t++) {
            for (int[] f : flights) {
                if (pre[f[0]] != Integer.MAX_VALUE) {
                    cur[f[1]] = cur[f[1]] < pre[f[0]] + f[2] ? cur[f[1]] : pre[f[0]] + f[2];
                }
            }
            pre = cur.clone();
        }
        return pre[dst] == Integer.MAX_VALUE ? -1 : pre[dst];
    }
}

class Solution {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int[] res = new int[quiet.length];
        Arrays.fill(res, -1);
        List<Integer>[] adj = new List[quiet.length];
        for (int i = 0; i < quiet.length; i++)
            adj[i] = new LinkedList<>();
        for (int[] r : richer) 
            adj[r[0]].add(r[1]);
        for (int i = 0; i < quiet.length; i++)
            helper(i, adj, quiet, res, i);
        return res;
    }
    
    public void helper(int vertex, List<Integer>[] adj, int[] quiet, int[] res, int cand) {
        if (res[vertex] != -1 && quiet[res[vertex]] <= quiet[cand])
            return;
        cand = quiet[vertex] < quiet[cand] ? vertex : cand;
        res[vertex] = cand;
        for (int v : adj[vertex])
            helper(v, adj, quiet, res, cand);
    }
}


// construct the graph in the converse way 
class Solution {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int[] res = new int[quiet.length];
        Arrays.fill(res, -1);
        List<Integer>[] adj = new List[quiet.length];
        for (int i = 0; i < quiet.length; i++)
            adj[i] = new LinkedList<>();
        for (int[] r : richer) 
            adj[r[1]].add(r[0]);
        for (int i = 0; i < quiet.length; i++)
            helper(i, adj, quiet, res);
        return res;
    }
    
    public int helper(int vertex, List<Integer>[] adj, int[] quiet, int[] res) {
        if (res[vertex] != -1)
            return res[vertex];
        res[vertex] = vertex;
        for (int v : adj[vertex]) {
            int ret = helper(v, adj, quiet, res);
            if (quiet[res[vertex]] > quiet[ret])
                res[vertex] = ret;
        }
        return res[vertex];
    }
}


// Bellman-Ford algorithm 
class Solution {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int[] res = new int[quiet.length];
        for (int i = 0; i < res.length; i++)
            res[i] = i;
        boolean flag = true;
        for (int i = 0; i < quiet.length && flag; i++) {
            flag = false;
            for (int[] r : richer) {
                if (quiet[res[r[0]]] < quiet[res[r[1]]]) {
                    flag = true;
                    res[r[1]] = res[r[0]];
                }
            }
        }
        return res;
    }
}

// do a dp according to the total steps, rather than according to the turns 
// borrow from yubowen 
class Solution {
    public int catMouseGame(int[][] graph) {
        Integer[][][] dp = new Integer[graph.length][graph.length][graph.length];
        return helper(graph, 1, 2, 0, dp);
    }
    
    public int helper(int[][] graph, int mouse, int cat, int step, Integer[][][] dp) {
        if (step >= dp[mouse][cat].length)
            return 0;
        if (dp[mouse][cat][step] != null) 
            return dp[mouse][cat][step];
        if (step % 2 == 0) {
            boolean lose = true, win = false;
            for (int v : graph[mouse]) {
                if (v == cat)
                    continue;
                if (v == 0) {
                    win = true;
                    break;
                }
                int ret = helper(graph, v, cat, step + 1, dp);
                if (ret == 0) {
                    lose = false;
                } else if (ret == 1) {
                    win = true;
                    break;
                }
            }
            dp[mouse][cat][step] = win ? 1 : (lose ? 2 : 0);
        } else {
            boolean lose = true, win = false;
            for (int v : graph[cat]) {
                if (v == 0)
                    continue;
                if (v == mouse) {
                    win = true;
                    break;
                }
                int ret = helper(graph, mouse, v, step + 1, dp);
                if (ret == 0) {
                    lose = false;
                } else if (ret == 2) {
                    win = true;
                    break;
                }
            }
            dp[mouse][cat][step] = win ? 2 : (lose ? 1 : 0);
        }
        return dp[mouse][cat][step];
    }
}


// same idea with above, but more concise 
class Solution {
    public int catMouseGame(int[][] graph) {
        Integer[][][] dp = new Integer[graph.length][graph.length][graph.length];
        int ret = helper(graph, 1, 2, 0, dp);
        return ret == 0 ? 0 : (ret == -1 ? 2 : 1);
    }
    
    public int helper(int[][] graph, int mouse, int cat, int step, Integer[][][] dp) {
        if (step >= dp[mouse][cat].length)
            return 0;
        if (mouse == 0)
            return -1;
        if (mouse == cat)
            return step % 2 == 0 ? -1 : 1;
        if (dp[mouse][cat][step] != null) 
            return dp[mouse][cat][step];
        boolean lose = true, win = false;
        for (int v : graph[step % 2 == 0 ? mouse : cat]) {
            if (v == 0 && step % 2 == 1) 
                continue;
            int ret = step % 2 == 0 ? helper(graph, v, cat, step + 1, dp) : 
                                        helper(graph, mouse, v, step + 1, dp); 
            if (ret == 0) {
                lose = false;
            } else if (ret == -1) {
                win = true;
                break;
            }
        }
        dp[mouse][cat][step] = win ? 1 : (lose ? -1 : 0);
        return dp[mouse][cat][step];
    }
}

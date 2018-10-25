class Solution {
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        int mod = 1000000007;
        int[][][] dp = new int[group.length+1][G+1][P+1];
        for (int g = 0; g <= G; g++)
            dp[0][g][0] = 1;
        for (int i = 1; i <= group.length; i++) {
            for (int g = 0; g <= G; g++) {
                for (int p = 0; p <= P; p++) 
                    dp[i][g][p] = (dp[i-1][g][p] + 
                    (g < group[i-1] ? 0 : dp[i-1][g-group[i-1]][Math.max(0, p-profit[i-1])])) % mod;
            }
        }
        return dp[group.length][G][P];
    }
}


// optimize the space complexity 
class Solution {
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        int mod = 1000000007;
        int[][] dp = new int[G+1][P+1];
        for (int g = 0; g <= G; g++)
            dp[g][0] = 1;
        for (int i = 1; i <= group.length; i++) {
            for (int g = G; g >= group[i-1]; g--) {
                for (int p = P; p >= 0; p--) 
                    dp[g][p] = (dp[g][p] + dp[g-group[i-1]][Math.max(0, p-profit[i-1])]) % mod;
            }
        }
        return dp[G][P];
    }
}

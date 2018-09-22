class Solution {
    public boolean splitArraySameAverage(int[] A) {
        int sum = 0;
        for (int a : A)
            sum += a;
        for (int len = 1; len < A.length; len++) {
            if (sum * len % A.length == 0) {
                int total = sum * len / A.length;
                boolean[][][] dp = new boolean[A.length+1][len+1][total+1];
                dp[0][0][0] = true;
                for (int i = 1; i <= A.length; i++) {
                    for (int j = 0; j <= i && j <= len; j++) {
                        for (int k = 0; k <= total; k++) {
                            dp[i][j][k] = dp[i-1][j][k];
                            if (j != 0 && k >= A[i-1])
                                dp[i][j][k] |= dp[i-1][j-1][k-A[i-1]]; // bitwise operation 
                        }
                    }
                }
                if (dp[A.length][len][total])
                    return true;
            }
        }
        return false;
    }
}

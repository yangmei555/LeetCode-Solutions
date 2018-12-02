// notice that the two arrays used in determining dp[k][n] are both monotonic 
class Solution {
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K+1][N+1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = 0;
        for (int k = 1; k <= K; k++) {
            for (int n = 1; n <= N; n++) {
                int left = 0, right = n;
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (dp[k-1][mid] >= dp[k][n-1-mid])
                        right = mid;
                    else
                        left = mid + 1;
                }
                int cand1 = left == 0 ? Integer.MAX_VALUE : dp[k][n-left];
                int cand2 = left == n ? Integer.MAX_VALUE : dp[k-1][left];
                dp[k][n] = Math.min(cand1, cand2) + 1;
            }
        }
        return dp[K][N];
    }
}


// base on the above observation, further more we can notice that the first point 'p' that 
// dp[k-1][p] >= dp[k][n-1-p] is non decreasing with the increase of n. also, it can increase by 
// at most 1. so, every round, either 'p' keeps the same, or it increases by 1. 
class Solution {
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K+1][N+1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = 0;
        for (int k = 1; k <= K; k++) {
            int optimalPoint = 0;
            for (int n = 1; n <= N; n++) {
                if (dp[k-1][optimalPoint] < dp[k][n-1-optimalPoint])
                    optimalPoint++;
                int cand1 = optimalPoint == 0 ? Integer.MAX_VALUE : dp[k][n-optimalPoint];
                int cand2 = optimalPoint == n ? Integer.MAX_VALUE : dp[k-1][optimalPoint];
                dp[k][n] = Math.min(cand1, cand2) + 1;
            }
        }
        return dp[K][N];
    }
}


// an excellent idea is to binary search on the times of moves 
// a length 'm' move is a sequence of m trails with results of either success or fail, just the 
// Bernoulli experiment, but the times of fails is at most the number of eggs. so, to test whether 
// a length 'm' move can solve N floors, just sum up the combination numbers "select i fails from 
// a length m sequence" to see whether the sum is >= N+1, because N floors have N+1 possibilities, 
// each possibility map to one sequence and each sequence map to one possibility, under the optimal 
// arrangement 
class Solution {
    public int superEggDrop(int K, int N) {
        int left = 1, right = N;
        while (left < right) {
            int mid = (left + right) / 2;
            if (helper(K, mid, N))
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
    
    public boolean helper(int egg, int trial, int N) {
        if (egg >= trial) {
            if (trial >= 31)
                return true;
            else
                return 1 << trial >= N + 1;
        } else {
            int sum = 1, cur = 1;
            for (int i = 1; i <= egg; i++) {
                cur = cur * (trial - i + 1) / i;
                sum += cur;
                if (sum >= N+1)
                    return true;
            }
            return false;
        }
    }
}

class Solution {
    public int splitArray(int[] nums, int m) {
        int[][] dp = new int[nums.length][m];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            for (int j = 0; j <= i && j < m; j++) {
                if (j == 0) {
                    dp[i][j] = sum;
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                    int last = nums[i];
                    for (int k = i-1; k >= j-1; k--) {
                        dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j-1], last));
                        last += nums[k];
                    }
                }
            }
        }
        return dp[nums.length-1][m-1];
    }
}


// another way of writing the same dp idea 
// there are latent integer overflows ...
class Solution {
    public int splitArray(int[] nums, int m) {
        int[][] dp = new int[nums.length+1][m+1];
        for (int[] d : dp)
            Arrays.fill(d, Integer.MAX_VALUE);
        dp[0][0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= i && j <= m; j++) {
                int last = 0;
                for (int k = i; k-1 >= j-1; k--) {
                    last += nums[k-1];
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k-1][j-1], last));
                }
            }
        }
        return dp[nums.length][m];
    }
}


// another way of writing the same dp idea 
class Solution {
    public int splitArray(int[] nums, int m) {
        int[][] dp = new int[m+1][nums.length+1];
        for (int[] d : dp)
            Arrays.fill(d, Integer.MAX_VALUE);
        dp[0][0] = 0;        
        for (int j = 1; j <= m; j++) {
            for (int i = nums.length; i >= j; i--) {
                int last = 0;
                for (int k = i; k-1 >= j-1; k--) {
                    last += nums[k-1];
                    dp[j][i] = Math.min(dp[j][i], Math.max(dp[j-1][k-1], last));
                }
            }
        }
        return dp[m][nums.length];
    }
}


// 1 dimension dp version 
class Solution {
    public int splitArray(int[] nums, int m) {
        long[] dp = new long[nums.length+1];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;
        for (int j = 1; j <= m; j++) {
            for (int i = nums.length; i >= j; i--) {  
                long last = 0;
                for (int k = i; k-1 >= j-1; k--) {
                    last += nums[k-1];
                    dp[i] = Math.min(dp[i], Math.max(dp[k-1], last));
                }
            }
        }
        return (int)dp[nums.length];
    }
}


// binary search, fantastic idea 
class Solution {
    public int splitArray(int[] nums, int m) {
        long left = 0, right = 0;
        for (int n : nums) {
            right += n;
            left = left > n ? left : n;
        }
        while (left < right) {
            long mid = (left + right) / 2;
            if (helper(nums, m, mid))
                right = mid;
            else
                left = mid + 1;
        }
        return (int)left;
    }
    
    public boolean helper(int[] nums, int m, long maxsum) {
        long sum = maxsum, count = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > maxsum) {
                sum = nums[i];
                count++;
            }
        }
        return count <= m;
    }
}

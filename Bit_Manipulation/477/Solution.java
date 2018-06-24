class Solution {
    public int totalHammingDistance(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                int n = nums[i] ^ nums[j];
                res += Integer.bitCount(n);
            }
        }
        return res;
    }
}


class Solution {
    public int totalHammingDistance(int[] nums) {
        int res = 0, len = nums.length, max = 0;
        for (int n : nums)
            max = max > n ? max : n;
        for (int i = 0; i < 32; i++) {
            int mask = 1 << i, zeros = 0;
            if (mask > max)
                break;
            for (int n : nums) {
                if ((n & mask) == 0)
                    zeros++;
            }
            res += zeros * (len - zeros);
        }
        return res;
    }
}


// much faster than the above solution, don't know why
class Solution {
    public int totalHammingDistance(int[] nums) {
        int res = 0, len = nums.length;
        for (int i = 0; i < 32; i++) {
            res += helper(nums, i);
        }
        return res;
    }
    
    public int helper(int[] nums, int i) {
        int mask = 1 << i, ones = 0;
        for (int n : nums) {
            n >>= i;
            ones += n & 1;
        }
        return ones * (nums.length - ones);
    }
}

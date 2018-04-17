class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int[] sort = new int[nums.length];
        int[] num = nums.clone();
        Arrays.sort(nums);
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i-1]) {
                sort[index] = nums[i];
                index++;
            }
        }
        int[] seq = new int[nums.length];
        int temp1 = 0, temp2 = 0, pre = 0;
        for (int i = 0; i < index; i++) {
            temp1 = 0;
            for (int j = 0; j < num.length; j++) {
                temp2 = seq[j];
                if (sort[i] == num[j]) {
                    seq[j] = temp1 + 1;
                } else {
                    seq[j] = j == 0 ? seq[j] : (seq[j] > seq[j-1] ? seq[j] : seq[j-1]);
                }
                temp1 = temp2;
            }
        }
        return seq[nums.length - 1];
    }
}


class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int[] dp = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = dp[j] > dp[i] ? dp[j] : dp[i];
                }
            }
            dp[i]++;
            if (dp[i] > max)
                max = dp[i];
        }
        return max;
    }
}


class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int[] inc = new int[nums.length];
        int size = 0, index = 0;
        for (int i : nums) {
            for (index = size - 1; index >= 0; index--) {
                if (inc[index] < i)
                    break;
            }
            inc[index + 1] = i;
            if (index == size - 1)
                size++;
        }
        return size;
    }
}


class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int[] inc = new int[nums.length];
        int size = 0, index = 0;
        for (int n : nums) {
            int i = 0, j = size - 1;
            while (i <= j) {
                index = (i + j) / 2;
                if (inc[index] == n) {
                    i = index;
                    break;
                } else if (inc[index] > n) {
                    j = index - 1;
                } else {
                    i = index + 1;
                }
            }
            inc[i] = n;
            if (i == size)
                size++;
        }
        return size;
    }
}

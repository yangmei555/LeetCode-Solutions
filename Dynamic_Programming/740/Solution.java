class Solution {
    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0)
            return 0;
        Arrays.sort(nums);
        int cur = 0, pre = 0, cont = 0, temp = 0, last = nums[0];
        for (int i = 0; i < nums.length; i++) {
            cont += nums[i];
            if (i == nums.length-1 || nums[i] != nums[i+1]) {
                temp = cur;
                if (last + 1 == nums[i])
                    cur = cur > pre + cont ? cur : pre + cont;
                else
                    cur += cont;
                pre = temp;
                cont = 0;
                last = nums[i];
            }
        }
        return cur;
    }
}


class Solution {
    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0)
            return 0;
        int[] index = new int[10001];
        for (int n : nums)
            index[n] += n;
        int cur = 0, pre = 0, temp = 0, last = 0;
        for (int i = 0; i < index.length; i++) {
            if (index[i] != 0) {
                temp = cur;
                if (i == last + 1)
                    cur = cur > pre + index[i] ? cur : pre + index[i];
                else
                    cur += index[i];
                pre = temp;
                last = i;
            }
        }
        return cur;
    }
}


class Solution {
    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0)
            return 0;
        int max = 0;
        for (int n : nums)
            max = Math.max(max, n);
        int[] count = new int[max + 1], dp = new int[max + 1];
        for (int n : nums)
            count[n]++;
        dp[1] = count[1];
        for (int i = 2; i < dp.length; i++)
            dp[i] = Math.max(i * count[i] + dp[i-2], dp[i-1]);
        return dp[max];
    }
}

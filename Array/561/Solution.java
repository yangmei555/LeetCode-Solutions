class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i += 2)
            res += nums[i];
        return res;
    }
}


class Solution {
    public int arrayPairSum(int[] nums) {
        int[] map = new int[20001];
        for (int n : nums)
            map[n + 10000]++;
        // remain is to record how many map[i] numbers we should use 
        int res = 0, remain = 1;
        for (int i = 0; i < map.length; i++) {
            res += (map[i] + remain) / 2 * (i - 10000);
            remain = (map[i] + remain) % 2;
        }
        return res;
    }
}

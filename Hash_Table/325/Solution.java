class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums.length == 0)
            return 0;
        Map<Integer, Integer> map = new HashMap<>();
        int[] sum = new int[nums.length];
        map.put(0, -1);
        sum[0] = nums[0];
        map.putIfAbsent(sum[0], 0);
        for (int i = 1; i < nums.length; i++) {
            sum[i] = nums[i] + sum[i-1];
            map.putIfAbsent(sum[i], i);
        }
        int res = 0;
        for (int i = 0; i < sum.length; i++) {
            int temp = i - map.getOrDefault(sum[i]-k, i);
            res = res > temp ? res : temp;
        }
        return res;
    }
}


class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums.length == 0)
            return 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            nums[i] += i == 0 ? 0 : nums[i-1];
            int temp = i - map.getOrDefault(nums[i]-k, i);
            res = res > temp ? res : temp;
            map.putIfAbsent(nums[i], i);
        }
        return res;
    }
}

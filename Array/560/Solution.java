class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums.length == 0)
            return 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            map.putIfAbsent(sum, new LinkedList<>());
            map.get(sum).add(i);
        }
        int res = 0;
        sum = 0;
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = map.getOrDefault(sum+k, new LinkedList<>());
            for (int j : list)
                if (j >= i)
                    res++;
            sum += nums[i];
        }
        return res;
    }
}


class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums.length == 0)
            return 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k)
                    res++;
            }
        }
        return res;
    }
}


class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums.length == 0)
            return 0;
        int res = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int n : nums) {
            sum += n;
            res += map.getOrDefault(sum-k, 0);
            map.put(sum , map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}

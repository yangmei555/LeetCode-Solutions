class Solution {
    public int findPairs(int[] nums, int k) {
        if (nums.length < 2 || k < 0)
            return 0;
        Arrays.sort(nums);
        int res = 0, left = 0, right = 1;
        while (right < nums.length) {
            if (nums[right] - nums[left] == k) {
                res++;
                right++;
                while (right < nums.length && nums[right] == nums[right-1]) 
                    right++;
                left++;
                while (left < right && nums[left] == nums[left-1]) 
                    left++;
            } else if (nums[right] - nums[left] < k) {
                right++;
            } else {
                left++;
            }
            if (left == right)
                right++;
        }
        return res;
    }
}


class Solution {
    public int findPairs(int[] nums, int k) {
        if (nums.length < 2 || k < 0)
            return 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        int res = 0;
        for (int n : map.keySet()) {
            if (map.containsKey(n + k)) {
                if (k != 0 || (k == 0 && map.get(n) != 1))
                    res++;
            }
        }
        return res;
    }
}

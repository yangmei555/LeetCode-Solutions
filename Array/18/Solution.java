class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums.length < 4)
            return res;
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len-3; i++) {
            if (i > 0 && nums[i] == nums[i-1])
                continue;
            if (nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target)
                break;
            if (nums[i] + nums[len-3] + nums[len-2] + nums[len-1] < target)
                continue;
            for (int j = i+1; j < len-2; j++) {
                if (j > i+1 && nums[j] == nums[j-1])
                    continue;
                if (nums[i] + nums[j] + nums[j+1] + nums[j+2] > target)
                    break;
                if (nums[i] + nums[j] + nums[len-2] + nums[len-1] < target)
                    continue;
                int want = target - nums[i] - nums[j];
                int start = j+1, end = len-1;
                while (start < end) {
                    if (nums[start] + nums[end] == want) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[start], nums[end]));
                        start++;
                        while (start < end && nums[start] == nums[start-1])
                            start++;
                        end--;
                        while (start < end && nums[end] == nums[end+1])
                            end--;
                    } else if (nums[start] + nums[end] < want) {
                        start++;
                    } else {
                        end--;
                    }
                }
            }
        }
        return res;
    }
}

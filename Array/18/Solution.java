class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4)
            return new LinkedList<>();
        Arrays.sort(nums);

        List<List<Integer>> res = new LinkedList<>();
        int len = nums.length;
        for (int i = 0; i < len - 3; i++) {
            if (i > 0 && nums[i] == nums[i-1])
                continue;
            if (nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target)
                break;
            if (nums[i] + nums[len-1] + nums[len-2] + nums[len-3] < target)
                continue;
            for (int j = i + 1; j < len - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j-1])
                    continue;
                if (nums[i] + nums[j] + nums[j+1] + nums[j+2] > target)
                    break;
                if (nums[i] + nums[len-1] + nums[len-2] + nums[j] < target)
                    continue;
                int low = j + 1, high = len - 1;
                while (low < high) {
                    if (low > j + 1 && nums[low] == nums[low-1]) {
                        low++;
                        continue;
                    }
                    if (nums[i]+nums[j]+nums[low]+nums[high] < target)
                        low++;
                    else if (nums[i]+nums[j]+nums[low]+nums[high] > target)
                        high--;
                    else {
                        LinkedList<Integer> list = new LinkedList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[low]);
                        list.add(nums[high]);
                        res.add(list);
                        low++;
                        high--;
                    }
                }
            }
        }

        return res;
    }
}

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0)
            return res;
        Arrays.sort(nums);
        int i = 0;
        while (i < nums.length) {
            if (nums[i] > 0)
                break;
            int j = i+1, k = nums.length-1, target = -nums[i];
            while (j < k) {
                if (nums[j] + nums[k] < target) {
                    j++;
                } else if (nums[j] + nums[k] > target) {
                    k--;
                } else {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    while (j < k && nums[j] == nums[j-1])
                        j++;
                    k--;
                    while (j < k && nums[k] == nums[k+1])
                        k--;
                }
            }
            i++;
            while (i < nums.length && nums[i] == nums[i-1])
                i++;
        }
        return res;
    }
}

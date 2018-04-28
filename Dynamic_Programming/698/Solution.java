class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        if (sum == 0 || sum % k != 0)
            return false;
        int addup = sum / k;
        for (int n : nums) {
            if (n > addup)
                return false;
        }
        int used = 0;
        HashMap<String, Boolean> map = new HashMap<>();
        return helper(nums, k, addup, 0, used, map);
    }
    
    public boolean helper(int[] nums, int k, int addup, int cur, int used, HashMap<String, Boolean> map) {
        if (cur == addup) {
            cur = 0;
            k--;
        }
        String str = used + " " + cur + " " + k;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        if ((1 << nums.length)-1 == used) {
            return k == 0 && cur == 0 ? true : false;
        }
        boolean res = false;
        for (int i = 0; i < nums.length; i++) {
            if ((used & (1 << i)) == 0 && cur + nums[i] <= addup) {
                res = res || helper(nums, k, addup, cur+nums[i], used | (1 << i), map);
                if (res)
                    break;
            }
        }
        map.put(str, res);
        return res;
    }
}


class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        if (sum % k != 0)
            return false;
        int addup = sum / k;
        for (int n : nums)
            if (n > addup)
                return false;
        int[] groups = new int[k];
        Arrays.sort(nums);
        return helper(nums, addup, groups, nums.length-1);
    }
    
    public boolean helper(int[] nums, int addup, int[] groups, int index) {
        if (index == -1)
            return true;
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] + nums[index] <= addup) {
                groups[i] += nums[index];
                if (helper(nums, addup, groups, index - 1))
                    return true;
                groups[i] -= nums[index];
            }
            if (groups[i] == 0)
                break;
            // if (index == nums.length-1)
            //     break;
        }
        return false;
    }
}

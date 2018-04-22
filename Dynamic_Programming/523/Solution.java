class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return false;
        for (int i = 1; i < nums.length; i++) {
            int sum = nums[i];
            for (int j = i-1; j >= 0; j--) {
                sum += nums[j];
                if ((k != 0 && sum % k == 0) || (k == 0 && sum == 0))
                    return true;
            }
        }
        return false;
    }
}


class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2)
            return false;
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++){
            if (k != 0) {
                sum += nums[i];
                if (i - map.getOrDefault(sum%k, i) > 1)
                    return true;
                else if (!map.containsKey(sum%k))
                    map.put(sum%k, i);
            } else {
                if (i != 0 && nums[i] == 0 && nums[i-1] == 0)
                    return true;
            }
        }
        return false;
    }
}


class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2)
            return false;
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0)
                sum %= k;
            if (map.getOrDefault(sum, i) < i-1)
                return true;
            else if (!map.containsKey(sum))
                map.put(sum, i);
        }
        return false;
    }
}

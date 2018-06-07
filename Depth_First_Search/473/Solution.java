class Solution {
    public boolean makesquare(int[] nums) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        if (sum % 4 != 0)
            return false;
        int length = sum / 4;
        for (int n : nums)
            if (n > length)
                return false;
        boolean[] used = new boolean[nums.length];
        Set<String> set = new HashSet<>();
        return helper(nums, used, length,  0, 0, set);
    }
    
    public boolean helper(int[] nums, boolean[] used, int length, int addup, int total, 
                            Set<String> set) {
        if (total == 3)
            return true;
        if (set.contains(Arrays.toString(used)))
            return false;
        for (int i = 0; i < nums.length; i++) {
            if (!used[i] && addup + nums[i] <= length) {
                used[i] = true;
                addup += nums[i];
                if (addup == length) {
                    addup = 0;
                    total++;
                }
                if (helper(nums, used, length, addup, total, set))
                    return true;
                used[i] = false;
                addup -= nums[i];
                if (addup < 0) {
                    addup += length;
                    total--;
                }
            }
        }
        set.add(Arrays.toString(used));
        return false;
    }
}


class Solution {
    public boolean makesquare(int[] nums) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        if (sum % 4 != 0)
            return false;
        int length = sum / 4;
        for (int n : nums)
            if (n > length)
                return false;
        int used = 0;
        boolean[] memo = new boolean[1 << nums.length];
        return helper(nums, used, length,  0, 0, memo);
    }
    
    public boolean helper(int[] nums, int used, int length, int addup, int total, boolean[] memo) {
        if (total == 3)
            return true;
        if (memo[used])
            return false;
        int mask = 1;
        for (int i = 0; i < nums.length; i++) {
            mask = 1 << i;
            if ((used & mask) == 0 && addup + nums[i] <= length) {
                addup += nums[i];
                if (addup == length) {
                    addup = 0;
                    total++;
                }
                if (helper(nums, used | mask, length, addup, total, memo))
                    return true;
                addup -= nums[i];
                if (addup < 0) {
                    addup += length;
                    total--;
                }
            }
        }
        memo[used] = true;
        return false;
    }
}


class Solution {
    public boolean makesquare(int[] nums) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        if (sum % 4 != 0)
            return false;
        int length = sum / 4;
        for (int n : nums)
            if (n > length)
                return false;
        int used = 0;
        Arrays.sort(nums);
        boolean[] memo = new boolean[1 << nums.length];
        return helper(nums, used, length,  0, 0, memo);
    }
    
    public boolean helper(int[] nums, int used, int length, int addup, int total, boolean[] memo) {
        if (addup == length) {
            addup = 0;
            total++;
        }
        if (total == 3)
            return true;
        if (memo[used])
            return false;
        int mask = 1;
        for (int i = 0; i < nums.length; i++) {
            if (addup + nums[i] > length)
                break;
            mask = 1 << i;
            if ((used & mask) == 0) {
                addup += nums[i];
                if (helper(nums, used | mask, length, addup, total, memo))
                    return true;
                addup -= nums[i];
                while (i+1 < nums.length && nums[i] == nums[i+1])
                    i++;
            }
        }
        memo[used] = true;
        return false;
    }
}


class Solution {
    public boolean makesquare(int[] nums) {
        if (nums.length < 4)
            return false;
        int sum = 0;
        for (int n : nums)
            sum += n;
        if (sum % 4 != 0)
            return false;
        int length = sum / 4;
        Arrays.sort(nums);
        int[] buckets = new int[4];
        return helper(nums, buckets, nums.length-1, length);
    }
    
    public boolean helper(int[] nums, int[] buckets, int index, int length) {
        if (index == -1) 
            return buckets[0] == length && buckets[1] == length && 
                    buckets[2] == length && buckets[3] == length;
        for (int i = 0; i < 4; i++) {
            if (buckets[i] + nums[index] <= length) {
                buckets[i] += nums[index];
                if (helper(nums, buckets, index-1, length))
                    return true;
                buckets[i] -= nums[index];
                if (buckets[i] == 0)
                    break;
            }
        }
        return false;
    }
}

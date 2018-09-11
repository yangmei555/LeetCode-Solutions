public class Solution {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] >= 1 && nums[i] <= len && nums[i] != nums[nums[i] - 1]) {
                int tmp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
                i--;
            }
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1)
                return i + 1;
        }
        return len + 1;
    }
}


class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0)
            return 1;
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] >= 1 && nums[i] <= nums.length && nums[i] != nums[nums[i]-1]) {
                temp = nums[i];
                nums[i] = nums[nums[i]-1];
                nums[temp-1] = temp;
            }
        }
        int res = 0, i = 0;
        for (; i < nums.length; i++) {
            res = i+1;
            if (nums[i] != res)
                break;
        }
        return i == nums.length ? i + 1 : res;
    }
}


class Solution {
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] >= 1 && nums[i] <= nums.length && nums[nums[i]-1] != nums[i]) {
                int temp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i+1)
                return i+1;
        }
        return nums.length+1;
    }
}


class Solution {
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] > nums.length)
                nums[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]);
            if (index != Integer.MAX_VALUE) {
                if (nums[index-1] > 0)
                    nums[index-1] = -nums[index-1];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                return i+1;
        }
        return nums.length+1;
    }
}

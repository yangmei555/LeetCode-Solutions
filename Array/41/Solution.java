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

class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;
        int index = 1, pre = nums[0], run = 1, count = 0;
        while (run < nums.length) {
            if (nums[run] != pre) {
                nums[index] = nums[run];
                index++;
                count = 0;
                pre = nums[run];
            } else {
                if (count == 0) {
                    nums[index] = nums[run];
                    index++;
                }
                count++;
            }
            run++;
        }
        return index;
    }
}


class Solution {
    public int removeDuplicates(int[] nums) {
        int index = 0, i = 0;
        while (i < nums.length) {
            int start = i++;
            while (i < nums.length && nums[start] == nums[i])
                i++;
            for (int j = start; j < i && j < start + 2; j++)
                nums[index++] = nums[j];
        }
        return index;
    }
}

class Solution {
    public void nextPermutation(int[] nums) {
        if (nums.length < 2)
            return;
        int i = nums.length-2, temp = 0;
        for (; i >= 0; i--) {
            if (nums[i] < nums[i+1]) {
                int index = i+1;
                while (index < nums.length && nums[i] < nums[index])
                    index++;
                index--;
                temp = nums[index];
                nums[index] = nums[i];
                nums[i] = temp;
                break;
            }
        }
        for (int j = i+1, k = nums.length-1; j < k; j++, k--) {
            temp = nums[k];
            nums[k] = nums[j];
            nums[j] = temp;
        }
        return;
    }
}

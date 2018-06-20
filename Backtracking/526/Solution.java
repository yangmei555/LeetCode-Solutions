class Solution {
    public int countArrangement(int N) {
        int[] nums = new int[N+1];
        for (int i = 0; i < nums.length; i++)
            nums[i] = i;
        return helper(nums, N);
    }
    
    public int helper(int[] nums, int index) {
        if (index == 0)
            return 1;
        int res = 0;
        for (int i = index; i > 0; i--) {
            if (nums[i] % index == 0 || index % nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                res += helper(nums, index-1);
                temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
            }
        }
        return res;
    }
}


class Solution {
    public int countArrangement(int N) {
        boolean[] used = new boolean[N+1];
        return helper(used, N);
    }
    
    public int helper(boolean[] used, int index) {
        if (index == 0)
            return 1;
        int res = 0;
        for (int i = used.length-1; i > 0; i--) {
            if (!used[i] && (i % index == 0 || index % i == 0)) {
                used[i] = true;
                res += helper(used, index-1);
                used[i] = false;
            }
        }
        return res;
    }
}

// I don't fully understand why the map function works, borrowed from others 
class Solution {
    public void wiggleSort(int[] nums) {
        int len = nums.length;
        int mid = helper(nums, 0, nums.length-1, (nums.length + 1) / 2);
        // do a 3 way partition 
        int left = 0, right = nums.length-1;
        for (int i = 0; i <= right; i++) {
            if (nums[map(i, len)] < mid) {
                int temp = nums[map(i, len)];
                nums[map(i, len)] = nums[map(left, len)];
                nums[map(left++, len)] = temp;
            } else if (nums[map(i, len)] > mid) {
                int temp = nums[map(i, len)];
                nums[map(i, len)] = nums[map(right, len)];
                nums[map(right--, len)] = temp;
                i--;
            }
        }
    }
    
    // magical map function 
    public int map(int i, int len) {
        if (i <= (len-1)/2)
            return 2*((len-1)/2 - i);
        else
            return (len-1-i)*2+1;
    }
    
    public int helper(int[] nums, int left, int right, int k) {
        if (left == right)
            return nums[left];
        int mid = (left + right) / 2;
        int temp = nums[mid];
        nums[mid] = nums[right];
        nums[right] = temp;
        int index = left;
        for (int i = left; i <= right; i++) {
            if (nums[i] <= nums[right]) {
                temp = nums[i];
                nums[i] = nums[index];
                nums[index++] = temp;
            }
        }
        if (index - left == k)
            return nums[index-1];
        else if (index - left > k)
            return helper(nums, left, index-2, k);
        else
            return helper(nums, index, right, k-index+left);
    }
}

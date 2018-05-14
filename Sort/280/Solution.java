class Solution {
    public void wiggleSort(int[] nums) {
        int temp = 0;
        for (int i = 0; i < nums.length-1; i++) {
            if ((i&1)==0 && nums[i] > nums[i+1] || (i&1)==1 && nums[i] < nums[i+1]) {
                temp = nums[i];
                nums[i] = nums[i+1];
                nums[i+1] = temp;
            }
        }
    }
}

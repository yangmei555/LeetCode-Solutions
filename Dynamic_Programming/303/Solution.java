class NumArray {
    
    int[] arr;
    public NumArray(int[] nums) {
        arr = new int[nums.length + 1];
        arr[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            arr[i + 1] = arr[i] + nums[i];
        }
    }
    
    public int sumRange(int i, int j) {
        return arr[j + 1] - arr[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */

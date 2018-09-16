class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        int order = k + k + nums.length;
        Arrays.sort(nums);
        int left = 0, right = nums[nums.length-1] - nums[0];
        while (left < right) {
            int mid = (left + right) / 2;
            int row = nums.length-2, col = nums.length-1;
            int count = 0;
            while (row >= 0 && col >= 1) {
                if (nums[col] - nums[row] <= mid) {
                    count += col - row;
                    row--;
                } else {
                    col--;
                }
            }
            if (count < k)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}


// another way of traversing the matrix 
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        int order = k + k + nums.length;
        Arrays.sort(nums);
        int left = 0, right = nums[nums.length-1] - nums[0];
        while (left < right) {
            int mid = (left + right) / 2;
            int row = 0, col = 1;
            int count = 0;
            while (row <= nums.length-2 && col <= nums.length-1) {
                if (nums[col] - nums[row] <= mid) {
                    count += col - row;
                    col++;
                } else {
                    row++;
                }
            }
            if (count < k)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}

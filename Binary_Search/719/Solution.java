// bucket sort , or counting sort 
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int[] diffs = new int[nums[nums.length-1]-nums[0]+1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++)
                diffs[nums[j]-nums[i]]++;
        }
        int count = 0, index = 0;
        while (count + diffs[index] < k) 
            count += diffs[index++];
        return index;
    }
}


class Solution {
    public int smallestDistancePair(int[] nums, int k) {
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


// modify the value of k, complement the matrix, still two dimensional sorted 
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        k += nums.length * (nums.length-1) / 2 + nums.length;
        int left = 0, right = nums[nums.length-1] - nums[0];
        while (left < right) {
            int mid = (left + right) / 2;
            int row = 0, col = 0;
            int count = 0;
            while (row < nums.length && col < nums.length) {
                if (nums[col] - nums[row] <= mid) {
                    count += nums.length - row;
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


// same idea with the above solution, but different way of traversing 
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        k += nums.length * (nums.length-1) / 2 + nums.length;
        int left = 0, right = nums[nums.length-1] - nums[0];
        while (left < right) {
            int mid = (left + right) / 2;
            int row = nums.length-1, col = nums.length-1;
            int count = 0;
            while (row >= 0 && col >= 0) {
                if (nums[col] - nums[row] <= mid) {
                    count += col + 1;
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


// another way of counting , this should be the most natural way to count , sliding window 
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0, right = nums[nums.length-1] - nums[0];
        while (left < right) {
            int mid = (left + right) / 2;
            int count = 0;
            for (int start = 0, end = 0; end < nums.length; end++) {
                while (nums[end] - nums[start] > mid)
                    start++;
                count += end - start;
            }
            if (count < k)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}

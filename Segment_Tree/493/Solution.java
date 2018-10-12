class Solution {
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length-1);    
    }
    
    public int mergeSort(int[] nums, int left, int right) {
        int res = 0;
        if (left < right) {
            int mid = (left + right) / 2;
            res += mergeSort(nums, left, mid) + mergeSort(nums, mid+1, right);
            int ptr1 = left, ptr2 = mid+1;
            while (ptr1 <= mid && ptr2 <= right) {
                if (nums[ptr1] > nums[ptr2] * 2L) {
                    res += mid + 1 - ptr1;
                    ptr2++;
                } else {
                    ptr1++;
                }
            }
            int[] A = new int[mid-left+1], B = new int[right-mid];
            for (int i = 0; i < A.length; i++)
                A[i] = nums[left+i];
            for (int i = 0; i < B.length; i++)
                B[i] = nums[mid+1+i];
            ptr1 = 0;
            ptr2 = 0;
            for (int i = left; i <= right; i++) {
                if (ptr2 == B.length || ptr1 < A.length && A[ptr1] < B[ptr2]) 
                    nums[i] = A[ptr1++];
                else 
                    nums[i] = B[ptr2++];
            }
        }
        return res;
    }
}

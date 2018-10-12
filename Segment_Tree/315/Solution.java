// merge sort, divide and conquer 
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        Integer[] res = new Integer[nums.length];
        Arrays.fill(res, 0);
        int[] index = new int[nums.length];
        for (int i = 0; i < index.length; i++)
            index[i] = i;
        mergeSort(nums, index, res, 0, nums.length-1);
        return Arrays.asList(res);
    }
    
    public void mergeSort(int[] nums, int[] index, Integer[] res, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(nums, index, res, left, mid);
            mergeSort(nums, index, res, mid+1, right);
            int[] A = new int[mid-left+1], B = new int[right-mid];
            for (int i = 0; i < A.length; i++)
                A[i] = index[left+i];
            for (int i = 0; i < B.length; i++)
                B[i] = index[mid+1+i];
            int ptr1 = 0, ptr2 = 0;
            for (int i = left; i <= right; i++) {
                if (ptr2 == B.length || ptr1 < A.length && nums[A[ptr1]] <= nums[B[ptr2]]) {
                    res[A[ptr1]] += i - left - ptr1;
                    index[i] = A[ptr1++];
                } else {
                    index[i] = B[ptr2++];
                }
            }
        }
    }
}

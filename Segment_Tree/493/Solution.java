class Solution {
    public int reversePairs(int[] nums) {
        int[] aux = new int[nums.length];
        return mergeSort(nums, 0, nums.length-1, aux);    
    }
    
    public int mergeSort(int[] nums, int left, int right, int[] aux) {
        int res = 0;
        if (left < right) {
            int mid = (left + right) / 2;
            res += mergeSort(nums, left, mid, aux) + mergeSort(nums, mid+1, right, aux);
            int ptr1 = left, ptr2 = mid+1;
            while (ptr1 <= mid && ptr2 <= right) {
                if (nums[ptr1] > nums[ptr2] * 2L) {
                    res += mid + 1 - ptr1;
                    ptr2++;
                } else {
                    ptr1++;
                }
            }
            for (int i = left; i <= right; i++)
                aux[i] = nums[i];
            ptr1 = left;
            ptr2 = mid+1;
            for (int i = left; i <= right; i++) {
                if (ptr2 > right || ptr1 <= mid && aux[ptr1] < aux[ptr2]) 
                    nums[i] = aux[ptr1++];
                else 
                    nums[i] = aux[ptr2++];
            }
        }
        return res;
    }
}


// traditional BIT. has to use Map because there are inputs where min == MIN and max == MAX 
class Solution {
    public int reversePairs(int[] nums) {
        long min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int n : nums) {
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        int res = 0;
        // int[] tree = new int[max-min+2];
        Map<Long, Integer> tree = new HashMap<>();
        long upper = Math.max(max-min+1, (max-2*min+1)/2);
        for (int i = nums.length-1; i >= 0; i--) {
            for (long j = (nums[i]-2*min+1)/2; j > 0; j &= j-1)
                res += tree.getOrDefault(j, 0);
            for (long j = nums[i]-min+1; j <= upper; j += (j&-j))
                tree.put(j, tree.getOrDefault(j, 0) + 1);
        }
        return res;
    }
}


// another way of using BIT. no need to find the value range , sort the array first 
class Solution {
    public int reversePairs(int[] nums) {
        int[] tree = new int[nums.length+1];
        int[] record = nums.clone();
        Arrays.sort(record);
        int res = 0;
        for (int i = nums.length-1; i >= 0; i--) {
            int index = binarySearch(record, nums[i] % 2 == 0 ? nums[i]/2 : (nums[i]+1)/2);
            for (int j = index; j > 0; j &= j-1)
                res += tree[j];
            index = binarySearch(record, nums[i]);
            for (int j = index+1; j < tree.length; j += (j&-j))
                tree[j]++;
        }
        return res;
    }
    
    public int binarySearch(int[] record, int val) {
        int left = 0, right = record.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (record[mid] < val)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}

// use binary indexed tree 
class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] prefix = new long[nums.length+1];
        for (int i = 0; i < nums.length; i++)
            prefix[i+1] = prefix[i] + nums[i];
        long[] record = prefix.clone();
        Arrays.sort(record);
        int[] tree = new int[prefix.length+1];
        int res = 0;
        for (long p : prefix) {
            int index1 = binarySearch(record, p - lower + 1);
            for (int i = index1; i > 0; i &= i-1)
                res += tree[i];
            int index2 = binarySearch(record, p - upper);
            for (int i = index2; i > 0; i &= i-1)
                res -= tree[i];
            int index3 = binarySearch(record, p);
            for (int i = index3+1; i < tree.length; i += i&-i)
                tree[i]++;
        }
        return res;
    }
    
    public int binarySearch(long[] record, long val) {
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


// divide and conquer. faster than the above solution 
class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] prefix = new long[nums.length+1];
        for (int i = 0; i < nums.length; i++)
            prefix[i+1] = prefix[i] + nums[i];
        long[] aux = new long[prefix.length];
        return mergeSort(prefix, 0, prefix.length-1, lower, upper, aux);
    }
    
    public int mergeSort(long[] prefix, int left, int right, int lower, int upper, long[] aux) {
        int res = 0;
        if (left < right) {
            int mid = (left + right) / 2;
            res = mergeSort(prefix, left, mid, lower, upper, aux) + 
                    mergeSort(prefix, mid+1, right, lower, upper, aux);
            for (int i = left; i <= right; i++)
                aux[i] = prefix[i];
            int count1 = 0, count2 = 0;
            int ptr1 = left, ptr2 = mid+1;
            while (ptr1 <= mid && ptr2 <= right) {
                if (aux[ptr2] - aux[ptr1] <= upper) {
                    count1 += mid + 1 - ptr1;
                    ptr2++;
                } else {
                    ptr1++;
                }
            }
            ptr1 = left;
            ptr2 = mid+1;
            while (ptr1 <= mid && ptr2 <= right) {
                if (aux[ptr2] - aux[ptr1] <= lower-1) {
                    count2 += mid + 1 - ptr1;
                    ptr2++;
                } else {
                    ptr1++;
                }
            }
            res += count1 - count2;
            ptr1 = left;
            ptr2 = mid+1;
            for (int i = left; i <= right; i++) {
                if (ptr2 > right || ptr1 <= mid && aux[ptr1] < aux[ptr2]) 
                    prefix[i] = aux[ptr1++];
                else 
                    prefix[i] = aux[ptr2++];
            }            
        }
        return res;
    }
}

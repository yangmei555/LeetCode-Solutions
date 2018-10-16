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
                    // res[A[ptr1]] += i - left - ptr1;
                    res[A[ptr1]] += ptr2;
                    index[i] = A[ptr1++];
                } else {
                    index[i] = B[ptr2++];
                }
            }
        }
    }
}


// binary search and then insert, but O(N^2) 
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> list = new ArrayList<>();
        List<Integer> res = new LinkedList<>();
        for (int i = nums.length-1; i >= 0; i--) {
            int left = 0, right = list.size();
            while (left < right) {
                int mid = (left + right) / 2;
                if (list.get(mid) >= nums[i])
                    right = mid;
                else
                    left = mid+1;
            }
            list.add(left, nums[i]);
            res.add(0, left);
        }
        return res;
    }
}


// binary indexed tree , use the tree as a hashmap. this is the fastest solution 
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int n : nums) {
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        List<Integer> res = new LinkedList<>();
        int[] tree = new int[max-min+2];
        for (int i = nums.length-1; i >= 0; i--) {
            int index = nums[i] - min;
            int sum = 0;
            while (index != 0) {
                sum += tree[index];
                index &= index-1;
            }
            res.add(0, sum);
            index = nums[i] - min + 1;
            while (index < tree.length) {
                tree[index]++;
                index += (index & -index);
            }
        }
        return res;
    }
}


// another way of using BIT. index the index of the sorted array, rather than index the values 
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new LinkedList<>();
        int[] record = nums.clone();
        Arrays.sort(record);
        int[] tree = new int[nums.length+1];
        for (int i = nums.length-1; i >= 0; i--) {
            int index = binarySearch(record, nums[i]);
            int sum = 0;
            for (int j = index; j > 0; j &= j-1)
                sum += tree[j];
            res.add(0, sum);
            for (int j = index+1; j < tree.length; j += j&-j)
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

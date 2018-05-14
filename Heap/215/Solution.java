class Solution {
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>((x, y)->y-x);
        for (int n : nums)
            queue.offer(n);
        for (int i = 1; i < k; i++)
            queue.poll();
        return queue.poll();
    }
}


class Solution {
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
                return y - x;
            }
        });
        for (int n : nums)
            queue.offer(n);
        for (int i = 1; i < k; i++)
            queue.poll();
        return queue.poll();
    }
}


class Solution {
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>();
        for (int n : nums)
            queue.offer(n);
        for (int i = 0; i < nums.length-k; i++)
            queue.poll();
        return queue.poll();
    }
}


class Solution {
    public int findKthLargest(int[] nums, int k) {
        return partition(nums, 0, nums.length-1, k);
    }
    
    public int partition(int[] nums, int left, int right, int k) {
        int mid = (left+right)/2;
        int temp = 0;
        temp = nums[right];
        nums[right] = nums[mid];
        nums[mid] = temp;
        int i = left - 1;
        for (int j = left; j <= right; j++) {
            if (nums[j] >= nums[right]) {
                i++;
                temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            }
        }
        int order = i - left + 1;
        if (order == k)
            return nums[i];
        else if (k < order)
            return partition(nums, left, i-1, k);
        else
            return partition(nums, i+1, right, k-order);
    }
}


class Solution {
    public int findKthLargest(int[] nums, int k) {
        return partition(nums, 0, nums.length-1, nums.length-k+1);
    }
    
    public int partition(int[] nums, int left, int right, int k) {
        int mid = (left+right)/2;
        int temp = 0;
        temp = nums[right];
        nums[right] = nums[mid];
        nums[mid] = temp;
        int i = left - 1;
        for (int j = left; j <= right; j++) {
            if (nums[j] <= nums[right]) {
                i++;
                temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            }
        }
        int order = i - left + 1;
        if (order == k)
            return nums[i];
        else if (k < order)
            return partition(nums, left, i-1, k);
        else
            return partition(nums, i+1, right, k-order);
    }
}


class Solution {
    public int findKthLargest(int[] nums, int k) {
        for (int i = nums.length/2; i >= 0; i--)
            maxHeapify(nums, i, nums.length);
        int size = nums.length;
        for (int i = 1; i < k; i++) {
            extractMax(nums, size);
            size--;
        }
        return max(nums);
    }
    
    public int max(int[] nums) {
        return nums[0];
    }
    
    public int extractMax(int[] nums, int size) {
        int max = nums[0];
        nums[0] = nums[size-1];
        size--;
        maxHeapify(nums, 0, size);
        return max;
    }
    
    public void maxHeapify(int[] nums, int i, int size) {
        int largest = 0, l = left(i), r = right(i);
        if (l < size && nums[l] > nums[i])
            largest = l;
        else
            largest = i;
        if (r < size && nums[r] > nums[largest])
            largest = r;
        if (largest != i) {
            int temp = nums[largest];
            nums[largest] = nums[i];
            nums[i] = temp;
            maxHeapify(nums, largest, size);
        }
    }
    
    public int left(int i) {
        return 2*i+1;
    }
    
    public int right(int i) {
        return 2*i+2;
    }
    
    public int parent(int i) {
        return (i-1)/2;
    }
}

class Solution {
    
    Random random = new Random();
    
    public int minMoves2(int[] nums) {
        if (nums.length == 0)
            return 0;
        int level = randomized_select(nums, 0, nums.length-1, (nums.length+1)/2);
        int res = 0;
        for (int n : nums)
            res += n > level ? n - level : level - n;
        return res;
    }
    
    public int randomized_select(int[] nums, int p, int r, int i) {
        if (p == r)
            return nums[p];
        int q = randomized_partition(nums, p, r);
        int k = q - p + 1;
        if (i == k)
            return nums[q];
        else if (i < k)
            return randomized_select(nums, p, q-1, i);
        else 
            return randomized_select(nums, q+1, r, i-k);
    }
    
    public int randomized_partition(int[] nums, int p, int r) {
        int i = random.nextInt(r-p+1) + p;
        int temp = nums[r];
        nums[r] = nums[i];
        nums[i] = temp;
        return partition(nums, p, r);
    }
    
    public int partition(int[] nums, int p, int r) {
        int x =  nums[r], i = p - 1, temp = 0;
        for (int j = p; j < r; j++) {
            if (nums[j] <= x) {
                i++;
                temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            }
        }
        i++;
        temp = nums[r];
        nums[r] = nums[i];
        nums[i] = temp;
        return i;
    }
}


class Solution {
    public int minMoves2(int[] nums) {
        if (nums.length == 0)
            return 0;
        Arrays.sort(nums);
        int level = nums[nums.length/2];
        int res = 0;
        for (int n : nums)
            res += n > level ? n - level : level - n;
        return res;
    }
}


class Solution {    
    public int minMoves2(int[] nums) {
        if (nums.length == 0)
            return 0;
        int level = select(nums, 0, nums.length-1, (nums.length+1)/2);
        int res = 0;
        for (int n : nums)
            res += n > level ? n - level : level - n;
        return res;
    }
    
    public int select(int[] nums, int p, int r, int i) {
        if (p == r)
            return nums[p];
        int q = partition(nums, p, r);
        int k = q - p + 1;
        if (i == k)
            return nums[q];
        else if (i < k)
            return select(nums, p, q-1, i);
        else 
            return select(nums, q+1, r, i-k);
    }
    
    public int partition(int[] nums, int p, int r) {
        int i = (p + r) / 2, temp = nums[r], j = 0;
        nums[r] = nums[i];
        nums[i] = temp;
        int x =  nums[r];
        for (i = p-1, j = p; j <= r; j++) {
            if (nums[j] <= x) {
                i++;
                temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            }
        }
        return i;
    }
}

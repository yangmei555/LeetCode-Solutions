// brainless solution 
class Solution {
    public int maximumGap(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 1; i < nums.length; i++)
            res = Math.max(res, nums[i] - nums[i-1]);
        return res;
    }
}


// put nums into buckets 
// notice that, the maximum gap >= (max-min)/(len-1), so we make the size of buckets to be 
// approximately (max-min)/(len-1), in this way, numbers in the same bucket will not constitute 
// the desired answer 
class Solution {
    public int maximumGap(int[] nums) {
        if (nums.length <= 1)
            return 0;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int n : nums) {
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        int bucketSize = Math.max(1, (max - min) / (nums.length - 1));
        int[][] bucket = new int[(max-min)/bucketSize+1][];
        for (int i = 0; i < bucket.length; i++)
            bucket[i] = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        for (int n : nums) {
            int index = (n - min) / bucketSize;
            bucket[index][0] = Math.min(bucket[index][0], n);
            bucket[index][1] = Math.max(bucket[index][1], n);
        }
        int res = 0, prev = bucket[0][1];
        for (int i = 1; i < bucket.length; i++) {
            if (bucket[i][0] != Integer.MAX_VALUE) {
                res = Math.max(res, bucket[i][0] - prev);
                prev = bucket[i][1];
            }
        }
        return res;
    }
}

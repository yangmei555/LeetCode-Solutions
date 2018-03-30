public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        int closetSum = 0;
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < len - 2; i++){
            int j = i + 1;
            int k = len - 1;
            while (j < k){
                if (nums[i] + nums[j] + nums[k] == target){
                    return target;
                } else if (nums[i] + nums[j] + nums[k] < target){
                    if (diff > Math.abs(nums[i] + nums[j] + nums[k] - target)){
                        diff = Math.abs(nums[i] + nums[j] + nums[k] - target);
                        closetSum = nums[i] + nums[j] + nums[k];
                    }
                    j++;
                } else {
                    if (diff > Math.abs(nums[i] + nums[j] + nums[k] - target)) {
                        diff = Math.abs(nums[i] + nums[j] + nums[k] - target);
                        closetSum = nums[i] + nums[j] + nums[k];
                    }
                    k--;
                }
            }
        }
        return closetSum;
    }
}

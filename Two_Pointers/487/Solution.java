class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int pre = 0, cons = 0, res = 0;
        for (int n : nums) {
            if (n == 0) {
                pre = cons == 0 ? 1 : cons + 1;
                cons = 0;
            } else {
                cons++;
            }
            res = res > pre + cons ? res : pre + cons;
        }
        return res;
    }
}


class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int pre = -1, cur = -1, res = 0;
        for (int i = 0; i <= nums.length; i++) {
            if (i == nums.length || nums[i] == 0) {
                res = res > i - 1 - pre ? res : i - 1 - pre;
                pre = cur;
                cur = i;
            }
        }
        return res;
    }
}


class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int start = 0, zeros = 0, res = 0;
        int k = 1;    // we can flip at most k zeros
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                zeros++;
            if (zeros > k) {
                while (nums[start] != 0)
                    start++;
                zeros--;
                start++;
            }
            res = res > i - start + 1 ? res : i - start + 1;
        }
        return res;
    }
}

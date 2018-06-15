class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0, cons = 0;
        for (int n : nums) {
            if (n != 1) {
                cons = 0;
            } else {
                cons++;
                res = res > cons ? res : cons;
            }
        }
        return res;
    }
}

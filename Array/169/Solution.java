class Solution {
    public int majorityElement(int[] nums) {
        int count = 0, cand = nums[0];
        for (int n : nums) {
            if (n == cand)
                count++;
            else {
                count--;
                if (count == 0) {
                    cand = n;
                    count = 1;
                }
            }
        }
        return cand;
    }
}


class Solution {
    public int majorityElement(int[] nums) {
        int count = 0, cand = nums[0];
        for (int n : nums) {
            if (cand == n) {
                count++;
            } else {
                if (count == 0) {
                    count++;
                    cand = n;
                } else {
                    count--;
                }
            }
        }
        return cand;
    }
}

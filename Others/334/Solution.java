// s1, s2 is the smallest two values, s3 is the candidate for the smallest value 
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int s1 = Integer.MAX_VALUE, s2 = Integer.MAX_VALUE, s3 = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > s2) {
                return true;
            } else if (nums[i] > s1) {
                s2 = nums[i];
            } else if (nums[i] > s3) {
                s1 = s3;
                s2 = nums[i];
                s3 = Integer.MAX_VALUE;
            } else {
                s3 = nums[i];
            }
        }
        return false;
    }
}


// the more proper and concise way . when updating s1, no need to update s2 
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int s1 = Integer.MAX_VALUE, s2 = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > s2) {
                return true;
            } else if (nums[i] > s1) {
                s2 = nums[i];
            } else {
                // s2 = s1;
                s1 = nums[i];
            } 
        }
        return false;
    }
}


class Solution {
    public boolean increasingTriplet(int[] nums) {
        int n1 = Integer.MAX_VALUE, n2 = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= n1) 
                n1 = n;
            else if (n <= n2) 
                n2 = n;
            else 
                return true;
        }
        return false;
    }
}

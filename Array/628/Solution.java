class Solution {
    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = max1, max3 = max1;
        int min1 = Integer.MAX_VALUE;
        int min2 = min1;
        for (int num : nums) {
            if (num > max3) {
                max1 = max2;
                max2 = max3;
                max3 = num;
            } else if (num > max2) {
                max1 = max2;
                max2 = num;
            } else if (num > max1)
                max1 = num;
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }
        int res1 = max1 * max2 * max3;
        int res2 = min1 * min2 * max3;
        return res1 > res2 ? res1 : res2;
    }
}

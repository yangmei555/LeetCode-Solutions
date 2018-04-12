class Solution {
    public int minMoves(int[] nums) {
        int res = 0, min = Integer.MAX_VALUE;
        for (int i : nums)
            if (i < min)
                min = i;
        for (int i : nums)
            res += i - min;
        return res;
    }
}


class Solution {
    public int minMoves(int[] nums) {
        int sum = 0, min = Integer.MAX_VALUE;
        for (int i : nums) {
            if (i < min)
                min = i;
            sum += i;
        }
        return sum - nums.length * min;
    }
}

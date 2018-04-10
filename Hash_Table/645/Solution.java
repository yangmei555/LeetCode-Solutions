class Solution {
    public int[] findErrorNums(int[] nums) {
        int len = nums.length;
        Set<Integer> set = new HashSet<>();
        int sum = 0, find = 0;
        for (int i : nums) {
            sum += i;
            if (!set.add(i))
                find = i;
        }
        return new int[]{find, len*(len+1)/2 - sum + find};
    }
}


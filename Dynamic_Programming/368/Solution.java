class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0)
            return new LinkedList<>();
        Arrays.sort(nums);
        List<Integer> list[] = new List[nums.length];
        int maxlen = 1, index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                list[i] = new LinkedList<>();
                list[i].add(nums[i]);
            } else {
                int dup = -1, max = 0;
                for (int j = 0; j < i; j++) {
                    if (nums[i] % nums[j] == 0 && list[j].size() > max) {
                        dup = j;
                        max = list[dup].size();
                    }
                }
                if (dup != -1)
                    list[i] = new LinkedList<>(list[dup]);
                else
                    list[i] = new LinkedList<>();
                list[i].add(nums[i]);
                if (list[i].size() > maxlen) {
                    maxlen = list[i].size();
                    index = i;
                }
            }
        }
        return list[index];
    }
}


class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> list = new LinkedList<>();
        if (nums == null || nums.length == 0)
            return list;
        Arrays.sort(nums);
        int[] index = new int[nums.length];
        int[] dp = new int[nums.length];
        int maxindex = 0;
        for (int i = 0; i < nums.length; i++) {
            int pre = -1;
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    pre = j;
                }
            }
            index[i] = pre;
            if (dp[i] > dp[maxindex])
                maxindex = i;
        }
        while (maxindex != -1) {
            list.add(nums[maxindex]);
            maxindex = index[maxindex];
        }
        return list;
    }
}

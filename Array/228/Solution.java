class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new LinkedList<>();
        if (nums.length == 0)
            return res;
        int start = nums[0];
        for (int i = 1; i <= nums.length; i++) {
            if (i == nums.length || nums[i-1] + 1 < nums[i]) {
                if (start == nums[i-1])
                    res.add(start + "");
                else
                    res.add(start + "->" + nums[i-1]);
                if (i != nums.length)
                    start = nums[i];
            }
        }
        return res;
    }
}

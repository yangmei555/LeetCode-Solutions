public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        long a = lower, b = lower;
        List<String> list = new ArrayList<>();
        for (int num : nums) {
            if (num == Integer.MIN_VALUE){
                a = num + 1;
                continue;
            }
            b = num - 1;
            if (a == b) 
                list.add(a + "");
            else if (a < b)
                list.add(a + "->" + b);
            if (num == Integer.MAX_VALUE)
                return list;
            a = num + 1;
        }
        if (a == upper)
            list.add(a + "");
        else if (a < upper)
            list.add(a + "->" + upper);
        return list;
    }
}


class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new LinkedList<>();
        if (nums.length == 0) {
            if (lower == upper)
                res.add(lower + "");
            else 
                res.add(lower + "->" + upper);
            return res;
        }
        if (lower != nums[0]) {
            if (lower + 1 < nums[0])
                res.add(lower + "->" + (nums[0] - 1));
            else if (lower + 1 == nums[0])
                res.add(lower + "");
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] == nums[i] || nums[i-1] + 1 == nums[i])
                continue;
            if (nums[i-1] + 2 == nums[i])
                res.add(nums[i] - 1 + "");
            else
                res.add(nums[i-1] + 1 + "->" + (nums[i] - 1));
        }
        if (upper != nums[nums.length-1]) {
            if (nums[nums.length-1] < upper - 1)
                res.add(nums[nums.length-1] + 1 + "->" + upper);
            else if (nums[nums.length-1] == upper - 1)
                res.add(upper + "");
        }
        return res;
    }
}

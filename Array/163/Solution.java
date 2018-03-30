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

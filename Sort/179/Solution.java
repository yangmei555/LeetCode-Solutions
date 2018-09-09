class Solution {
    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++)
            strs[i] = nums[i] + "";
        Arrays.sort(strs, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return (s2 + s1).compareTo(s1 + s2);
            }
        });
        if (strs[0].equals("0"))
            return "0";
        StringBuilder sb = new StringBuilder();
        for (String s : strs)
            sb.append(s);
        return sb.toString();
    }
}

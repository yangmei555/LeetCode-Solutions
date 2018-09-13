class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        char[] ch = s.toCharArray();
        int[] count = new int[128];
        int res = 0, left = 0, right = 0, dist = 0;
        while (right < ch.length) {
            if (count[ch[right]] == 0)
                dist++;
            count[ch[right]]++;
            if (dist > k) {
                while (--count[ch[left]] != 0)
                    left++;
                left++;
                dist--;
            }
            res = Math.max(res, right-left+1);
            right++;
        }
        return res;
    }
}

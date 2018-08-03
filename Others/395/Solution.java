class Solution {
    int maxlen = 0;
    public int longestSubstring(String s, int k) {
        char[] ch = s.toCharArray();
        return helper(ch, 0, ch.length-1, k);
    }
    
    public int helper(char[] ch, int left, int right, int k) {
        if (right - left + 1 < maxlen || right - left + 1 < k)
            return 0;
        int[] map = new int[26];
        for (int i = left; i <= right; i++)
            map[ch[i]-'a']++;
        int res = Integer.MIN_VALUE, start = left, end = left;
        while (end <= right) {
            if (map[ch[end]-'a'] < k) {
                res = Math.max(res, helper(ch, start, end-1, k));
                start = end + 1;
            }
            end++;
        }
        if (start == left)
            res = right - left + 1;
        else
            res = Math.max(res, helper(ch, start, end-1, k));
        maxlen = maxlen > res ? maxlen : res;
        return res;
    }
}


class Solution {
    public int longestSubstring(String s, int k) {
        if (k == 0 || k == 1)
            return s.length();
        char[] ch = s.toCharArray();
        int res = 0;
        int[] map = new int[26];
        for (char c : ch)
            map[c-'a']++;
        int count = 0;
        for (int m : map) {
            if (m >= k)
                count++;
        }
        // in each loop, find out longest substring which contains "nums" unique characters 
        // and each character appears at least k times
        for (int nums = 1; nums <= count; nums++) {
            int unique = 0, atLeastK = 0, left = 0, right = 0;
            int[] record = new int[26];
            while (right < ch.length) {
                if (unique <= nums) {
                    if (record[ch[right]-'a']++ == 0)
                        unique++;
                    if (record[ch[right]-'a'] == k)
                        atLeastK++;
                    right++;
                } else {
                    if (--record[ch[left]-'a'] == 0)
                        unique--;
                    if (record[ch[left]-'a'] == k-1)
                        atLeastK--;
                    left++;
                }
                if (unique == nums && unique == atLeastK)
                    res = res > right - left ? res : right - left;
            }
        }
        return res;
    }
}

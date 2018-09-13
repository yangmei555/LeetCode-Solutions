class Solution {
    public int characterReplacement(String s, int k) {
        char[] ch = s.toCharArray();
        int left = 0, right = 0, res = 0, max = 0;
        int[] count = new int[26];
        while (right < ch.length) {
            count[ch[right]-'A']++;
            if (count[ch[right]-'A'] > count[max])
                max = ch[right]-'A';
            if (right-left+1-count[max] <= k) {
                res = Math.max(res, right-left+1);
            } else {
                count[ch[left++]-'A']--;
                // for (int i = 0; i < count.length; i++) {
                //     if (count[i] > count[max])
                //         max = i;
                // }
            }
            right++;
        }
        return res;
    }
}

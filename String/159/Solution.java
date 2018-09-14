class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        char[] ch = s.toCharArray();
        int[] map = new int[128];
        Arrays.fill(map, -1);
        char c1 = 0, c2 = 0;
        int res = 0, left = 0, right = 0;
        while (right < ch.length) {
            if (map[ch[right]] == -1) {
                left = map[c1]+1;
                map[c1] = -1;
            }
            if (c2 != ch[right]) {
                c1 = c2;
                c2 = ch[right];
            }
            map[ch[right]] = right;
            res = Math.max(res, right-left+1);
            right++;
        }
        return res;
    }
}


// the same logic with Problem 340 "Longest Substring with At Most K Distinct Characters" 
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        char[] ch = s.toCharArray();
        int[] count = new int[128];
        int res = 0, left = 0, right = 0, diff = 0;
        while (right < ch.length) {
            if (count[ch[right]] == 0)
                diff++;
            count[ch[right]]++;
            while (diff > 2) {
                if (--count[ch[left]] == 0)
                    diff--;
                left++;
            }
            res = Math.max(res, right-left+1);
            right++;
        }
        return res;
    }
}


class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        char[] ch = s.toCharArray();
        char c1 = 0, c2 = 0;
        int left = 0, right = 0, index1 = -1, index2 = -1, res = 0;
        while (right < ch.length) {
            if (ch[right] == c1) {
                index1 = right;
            } else if (ch[right] == c2) {
                index2 = right;
            } else {
                int t1 = index1 < index2 ? index1 : index2;
                int t2 = index1 < index2 ? index2 : index1;
                left = t1 + 1;
                index1 = t2;
                index2 = right;
                c1 = index1 >= 0 ? ch[index1] : 0;
                c2 = ch[right];
            }
            res = Math.max(res, right-left+1);
            right++;
        }
        return res;
    }
}


class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        char[] ch = s.toCharArray();
        int left = 0, mid = -1, right = 0, res = 0;
        while (right < ch.length) {
            if (right == 0 || ch[right-1] == ch[right]) {
                right++;
                continue;
            }
            if (mid >= 0 && ch[mid] != ch[right]) {
                res = Math.max(res, right-left);
                left = mid + 1;
            }
            mid = right - 1;
            right++;
        }
        return Math.max(res, right-left);
    }
}

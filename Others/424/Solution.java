// actually the 'max' does not need to be updated when the left of the window slides , 
// even if the 'max' is no longer the 'max'. but don't know how to prove this fact 
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


// seems no need to record the res every turn . but can not prove this fact 
// seems the window only expands, and will not shrink 
class Solution {
    public int characterReplacement(String s, int k) {
        char[] ch = s.toCharArray();
        int left = 0, right = 0, res = 0, max = 0;
        int[] count = new int[26];
        while (right < ch.length) {
            count[ch[right]-'A']++;
            if (count[ch[right]-'A'] > count[max])
                max = ch[right]-'A';
            if (right-left+1-count[max] > k) {
                count[ch[left++]-'A']--;
                // for (int i = 0; i < count.length; i++) {
                //     if (count[i] > count[max])
                //         max = i;
                // }
            }
            right++;
        }
        return right-left;
    }
}


// exam 'A' to 'Z' one by one 
class Solution {
    public int characterReplacement(String s, int k) {
        char[] ch = s.toCharArray();
        int res = 0;
        for (int i = 0; i < 26; i++) {
            char c = (char)('A' + i);
            int local = 0, remain = k;
            for (int j = 0, m = 0; m < ch.length; m++) {
                if (ch[m] != c)
                    remain--;
                while (remain < 0) {
                    if (ch[j++] != c)
                        remain++;
                }
                local = Math.max(local, m-j+1);
            }
            res = Math.max(res, local);
        }
        return res;
    }
}


// binary search. find the smallest size that does not meet the requirement, 
// then minus 1. notice that the initial right bound should be set as ch.length+1, 
// becuase it is possible that ch.length also meets the requirement 
class Solution {
    public int characterReplacement(String s, int k) {
        char[] ch = s.toCharArray();
        int left = 1, right = ch.length+1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (helper(ch, mid, k))
                left = mid + 1;
            else
                right = mid;
        }
        return left - 1;
    }
    
    public boolean helper(char[] ch, int size, int k) {
        int[] count = new int[26];
        int index = 0;
        while (index < ch.length) {
            count[ch[index]-'A']++;
            if (index >= size)
                count[ch[index-size]-'A']--;
            if (index >= size-1) {
                int max = 0;
                for (int c : count)
                    max = Math.max(max, c);
                if (size - max <= k) 
                    return true;
            }
            index++;
        }
        return false;
    }
}

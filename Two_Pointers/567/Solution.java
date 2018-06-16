class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        char[] ch1 = s1.toCharArray(), ch2 = s2.toCharArray();
        int[] map = new int[26];
        int flag = 0;
        for (char c : ch1)
            map[c-'a']++;
        for (int i = 0; i < ch1.length; i++) {
            map[ch2[i]-'a']--;
            flag = map[ch2[i]-'a'] == 0 ? flag & ~(1 << (ch2[i]-'a')) : flag | (1 << (ch2[i]-'a'));
        }
        if (flag == 0)
            return true;
        for (int l = 0, r = ch1.length; r < ch2.length; l++, r++) {
            map[ch2[l]-'a']++;
            flag = map[ch2[l]-'a'] == 0 ? flag & ~(1 << (ch2[l]-'a')) : flag | (1 << (ch2[l]-'a'));
            map[ch2[r]-'a']--;
            flag = map[ch2[r]-'a'] == 0 ? flag & ~(1 << (ch2[r]-'a')) : flag | (1 << (ch2[r]-'a'));
            if (flag == 0)
                return true;
        }
        return false;
    }
}


class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        char[] ch1 = s1.toCharArray(), ch2 = s2.toCharArray();
        int[] map = new int[26];
        for (int i = 0; i < ch1.length; i++) {
            map[ch1[i]-'a']++;
            map[ch2[i]-'a']--;
        }
        int count = 0;
        for (int m : map)
            if (m == 0)
                count++;
        if (count == 26)
            return true;
        for (int l = 0, r = ch1.length; r < ch2.length; l++, r++) {
            map[ch2[l]-'a']++;
            if (map[ch2[l]-'a'] == 0)
                count++;
            else if (map[ch2[l]-'a'] == 1)
                count--;
            map[ch2[r]-'a']--;
            if (map[ch2[r]-'a'] == 0)
                count++;
            else if (map[ch2[r]-'a'] == -1)
                count--;
            if (count == 26)
                return true;
        }
        return false;
    }
}


class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        char[] ch1 = s1.toCharArray(), ch2 = s2.toCharArray();
        int[] map = new int[26];
        for (char c : ch1)
            map[c-'a']++;
        int count = ch1.length, left = 0;
        for (int right = 0; right < ch2.length; right++) {
            if (map[ch2[right]-'a'] > 0)
                count--;
            map[ch2[right]-'a']--;
            while (count == 0) {
                if (right - left + 1 == ch1.length) 
                    return true;
                map[ch2[left]-'a']++;
                if (map[ch2[left]-'a'] > 0)
                    count++;
                left++;
            }
        }
        return false;
    }
}

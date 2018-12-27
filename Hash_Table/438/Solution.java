class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new LinkedList<>();
        if (s.length() < p.length())
            return list;
        char[] ch1 = s.toCharArray();
        char[] ch2 = p.toCharArray();
        int[] index = new int[26];
        int notz = 0;
        for (char c : ch2)
            index[c - 'a']++;
        for (int i = 0; i < ch2.length; i++)
            index[ch1[i] - 'a']--;
        for (int i = 0; i < index.length; i++)
            if (index[i] != 0)
                notz++;
        for (int i = 0; i < ch1.length + 1 - ch2.length; i++) {
            if (notz == 0)
                list.add(i);
            if (i != ch1.length - ch2.length) {
                if (index[ch1[i] - 'a'] == 0)
                    notz++;
                if (index[ch1[i + ch2.length] - 'a'] == 0)
                    notz++;
                index[ch1[i] - 'a']++;
                index[ch1[i + ch2.length] - 'a']--;
                if (index[ch1[i] - 'a'] == 0)
                    notz--;
                if (index[ch1[i + ch2.length] - 'a'] == 0)
                    notz--;
            }
            
        }
        return list;
    }
}


class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        char[] ch1 = s.toCharArray(), ch2 = p.toCharArray();
        int[] count = new int[26];
        List<Integer> res = new LinkedList<>();
        for (char c : ch2)
            count[c-'a']++;
        int left = 0, right = 0, match = 0;
        while (right < ch1.length) {
            if (count[ch1[right++]-'a']-- > 0)
                match++;
            while (match == ch2.length) {
                if (right - left == ch2.length)
                    res.add(left);
                if (count[ch1[left++]-'a']++ == 0)
                    match--;
            }
        }
        return res;
    }
}


// most concise version 
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        char[] ch1 = s.toCharArray(), ch2 = p.toCharArray();
        int[] count = new int[26];
        List<Integer> res = new LinkedList<>();
        for (char c : ch2)
            count[c-'a']++;
        int left = 0, right = 0;
        while (right < ch1.length) {
            count[ch1[right]-'a']--;
            while (count[ch1[right]-'a'] < 0) 
                count[ch1[left++]-'a']++;
            if (right - left + 1 == ch2.length)
                res.add(left);
            right++;
        }
        return res;
    }
}

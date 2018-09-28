// get the "power range" for each position 
class Solution {
    public int uniqueLetterString(String S) {
        int mod = 1000000007;
        char[] ch = S.toCharArray();
        int[] prev = new int[ch.length], next = new int[ch.length];
        int[] record = new int[26];
        Arrays.fill(record, -1);
        for (int i = 0; i < ch.length; i++) {
            prev[i] = record[ch[i]-'A'];
            record[ch[i]-'A'] = i;
        }
        Arrays.fill(record, ch.length);
        for (int i = ch.length-1; i >= 0; i--) {
            next[i] = record[ch[i]-'A'];
            record[ch[i]-'A'] = i;
        }
        int res = 0;
        for (int i = 0; i < ch.length; i++) 
            res = (res + (i - prev[i]) * (next[i] - i)) % mod;
        return res;
    }
}


// same idea with above, but only 1 pass. 
class Solution {
    public int uniqueLetterString(String S) {
        int mod = 1000000007;
        char[] ch = S.toCharArray();
        int[][] prev = new int[26][];
        for (int i = 0; i < prev.length; i++)
            prev[i] = new int[]{-1, -1};
        int res = 0;
        for (int i = 0; i < ch.length; i++) {
            res = res + (prev[ch[i]-'A'][1] - prev[ch[i]-'A'][0]) * (i - prev[ch[i]-'A'][1]);
            prev[ch[i]-'A'][0] = prev[ch[i]-'A'][1];
            prev[ch[i]-'A'][1] = i;
        }
        for (int i = 0; i < prev.length; i++)
            res = (res + (prev[i][1] - prev[i][0]) * (ch.length - prev[i][1])) % mod;
        return res;
    }
}


// dynamic programming approach, cur is the value of all substrings ended at i 
class Solution {
    public int uniqueLetterString(String S) {
        int mod = 1000000007;
        char[] ch = S.toCharArray();
        int[] contributions = new int[26], pos = new int[26];
        Arrays.fill(pos, -1);
        int res = 0, cur = 0;
        for (int i = 0; i < ch.length; i++) {
            cur -= contributions[ch[i]-'A'];
            contributions[ch[i]-'A'] = i - pos[ch[i]-'A'];
            cur += contributions[ch[i]-'A'];
            pos[ch[i]-'A'] = i;
            res = (res + cur) % mod;
        }
        return res;
    }
}

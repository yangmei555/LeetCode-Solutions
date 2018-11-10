class Solution {
    public String getHint(String secret, String guess) {
        char[] se = secret.toCharArray(), gu = guess.toCharArray();
        int a = 0, b = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : se) 
            map.put(c, map.getOrDefault(c, 0) + 1);
        for (int i = 0; i < gu.length; i++) {
            if (gu[i] == se[i]) {
                a++;
                map.put(gu[i], map.get(gu[i])-1);
            }
        }
        for (int i = 0; i < gu.length; i++) {
            if (gu[i] != se[i]) {
                if (map.getOrDefault(gu[i], 0) != 0) {
                    b++;
                    map.put(gu[i], map.get(gu[i])-1);
                }
            }
        }
        return a + "A" + b + "B";
    }
}


class Solution {
    public String getHint(String secret, String guess) {
        char[] se = secret.toCharArray(), gu = guess.toCharArray();
        int a = 0, b = 0;
        int[] map = new int[10];
        for (char c : se) 
            map[c-'0']++;
        for (int i = 0; i < gu.length; i++) {
            if (gu[i] == se[i]) {
                a++;
                map[gu[i]-'0']--;
            }
        }
        for (int i = 0; i < gu.length; i++) {
            if (gu[i] != se[i]) {
                if (map[gu[i]-'0'] != 0) {
                    b++;
                    map[gu[i]-'0']--;
                }
            }
        }
        return a + "A" + b + "B";
    }
}


// one pass solution 
class Solution {
    public String getHint(String secret, String guess) {
        char[] s = secret.toCharArray(), g = guess.toCharArray();
        int[] map = new int[10];
        int a = 0, b = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == g[i]) {
                a++;
            } else {
                if (map[s[i]-'0'] > 0)
                    b++;
                if (map[g[i]-'0'] < 0)
                    b++;
                map[s[i]-'0']--;
                map[g[i]-'0']++;
            }
        }
        return a + "A" + b + "B";
    }
}

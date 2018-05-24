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

class Solution {
    public int maxProduct(String[] words) {
        int res = 0;
        int[][] maps = new int[words.length][26];
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray())
                maps[i][c-'a']++;
        }
        for (int i = 0; i < words.length-1; i++) {
            for (int j = i+1; j < words.length; j++) {
                boolean collide = false;
                for (int k = 0; k < 26; k++) {
                    if (maps[i][k] != 0 && maps[j][k] != 0) {
                        collide = true;
                        break;
                    }
                }
                if (!collide) 
                    res = Math.max(res, words[i].length()*words[j].length());
            }
        }
        return res;
    }
}


class Solution {
    public int maxProduct(String[] words) {
        int res = 0;
        int[] maps = new int[words.length], lens = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray())
                maps[i] |= 1 << (c - 'a');
            lens[i] = words[i].length();
        }
        for (int i = 0; i < words.length-1; i++) {
            for (int j = i+1; j < words.length; j++) {
                if ((maps[i] & maps[j]) == 0 && lens[i] * lens[j] > res) {
                    res = lens[i] * lens[j];
                }
            }
        }
        return res;
    }
}

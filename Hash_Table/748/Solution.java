class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        char[] license = licensePlate.toCharArray();
        int[] index = new int[26];
        for (char l : license) {
            if (l >= 'a' && l <= 'z')
                index[l-'a']++;
            else if (l >= 'A' && l <= 'Z')
                index[l-'A']++;
        }
        String res = "";
        for (String w : words) {
            if (res.length() != 0 && res.length() <= w.length())
                continue;
            int[] count = new int[26];
            for (char c : w.toCharArray())
                count[c-'a']++;
            int j = 0;
            for (; j < index.length; j++)
                if (index[j] > count[j])
                    break;
            if (j == index.length) 
                res = w;
        }
        return res;
    }
}

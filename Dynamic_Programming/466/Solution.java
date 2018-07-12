class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (s1.length() * n1 < s2.length() * n2)
            return 0;
        char[] ch1 = s1.toCharArray(), ch2 = s2.toCharArray();
        int[] map1 = new int[26], map2 = new int[26];
        for (char c : ch1)
            map1[c-'a']++;
        for (char c : ch2)
            map2[c-'a']++;
        for (int i = 0; i < 26; i++) {
            if (map1[i] * n1 < map2[i] * n2)
                return 0;
        }
        if (ch2.length == 1)
            return map1[ch2[0]-'a'] * n1 / (map2[ch2[0]-'a'] * n2);
        int[] count = new int[ch1.length];
        for (int i = 0; i < count.length; i++) {
            int index1 = i, index2 = 0;
            while (index2 < ch2.length) {
                if (ch1[index1] == ch2[index2])
                    index2++;
                index1++;
                if (index1 == ch1.length)
                    index1 = 0;
                count[i]++;
            }
        }
        int len = 0, start = 0, num = 0;
        while (len + count[len % ch1.length] <= ch1.length * n1) {
            len += count[len % ch1.length];
            num++;
            if (count[len % ch1.length] == ch1.length)
                break;
        }
        num += (ch1.length * n1 - len) / count[len % ch1.length];
        return num / n2;
    }
}


class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        char[] ch1 = s1.toCharArray(), ch2 = s2.toCharArray();
        int index1 = 0, index2 = 0, times = 0, count = 0;
        while (times < n1) {
            if (ch1[index1] == ch2[index2]) {
                index2++;
                if (index2 == ch2.length) {
                    index2 = 0;
                    count++;
                }
            }
            index1++;
            if (index1 == ch1.length) {
                index1 = 0;
                times++;
            }
        }
        return count / n2;
    }
}


class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        char[] ch1 = s1.toCharArray(), ch2 = s2.toCharArray();
        int[] pos = new int[ch2.length+1], sum = new int[ch2.length+1];
        int index2 = 0, count = 0;
        for (int i = 1; i <= n1; i++) {
            for (int j = 0; j < ch1.length; j++) {
                if (ch1[j] == ch2[index2]) {
                    index2++;
                    if (index2 == ch2.length) {
                        index2 = 0;
                        count++;
                    }
                }
            }
            pos[i] = index2;
            sum[i] = count;
            for (int j = 0; j < i; j++) {
                if (pos[j] == pos[i]) {
                    int total = sum[j] + (n1 - j) / (i - j) * (sum[i] - sum[j]);
                    total += sum[j + (n1 - j) % (i - j)] - sum[j];
                    return total / n2;
                }
            }
        }
        return sum[n1] / n2;
    }
}

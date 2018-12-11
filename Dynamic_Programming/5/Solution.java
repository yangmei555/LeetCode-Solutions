class Solution {
    public String longestPalindrome(String s) {
        if (s.length() == 0)
            return new String("");
        char[] ch = s.toCharArray();
        int len = ch.length, span = 1, start = 0, end = 0;
        boolean[][] track = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = i; j >= 0; j--) {
                if (i == j) {
                    track[j][i] = true;
                } else {
                    if (ch[j] == ch[i] && (i==j+1 || track[j+1][i-1])) {
                        track[j][i] = true;
                        if (i - j + 1 > span) {
                            span = i - j + 1;
                            start = j;
                            end = i;
                        }
                    } else {
                        track[j][i] = false;
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }
}


class Solution {
    public String longestPalindrome(String s) {
        String res = "";
        char[] ch = s.toCharArray();
        boolean[][] dp = new boolean[ch.length][ch.length];
        for (int j = 0; j < ch.length; j++) {
            for (int i = j; i >= 0; i--) {
                if (i == j) {
                    dp[i][j] = true;
                } else if (ch[i] == ch[j]) {
                    dp[i][j] = i+1 == j || dp[i+1][j-1];
                }
                if (dp[i][j] && j-i+1 > res.length())
                    res = s.substring(i, j+1);
            }
        }
        return res;
    }
}


// 1 dimension dp 
class Solution {
    public String longestPalindrome(String s) {
        String res = "";
        char[] ch = s.toCharArray();
        boolean[] dp = new boolean[ch.length];
        for (int j = 0; j < ch.length; j++) {
            for (int i = 0; i <= j; i++) {
                if (i == j) {
                    dp[i] = true;
                } else if (ch[i] == ch[j]) {
                    dp[i] = i+1 == j || dp[i+1];
                } else {
                    dp[i] = false;
                }
                if (dp[i] && j-i+1 > res.length())
                    res = s.substring(i, j+1);
            }
        }
        return res;
    }
}


// the manacher algorithm! maintain a center C and a right boundary R . O(n) time and space 
class Solution {
    public String longestPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray())
            sb.append('#').append(c);
        char[] ch = sb.append('#').toString().toCharArray();
        int[] span = new int[ch.length];
        int C = 0, R = 0, center = -1;
        for (int i = 0; i < ch.length; i++) {
            int j = 2 * C - i;
            span[i] = j < 0 ? 0 : Math.min(R-i, span[j]);
            // in every loop the right boundary R is increased by 1 
            while (i-span[i]-1 >= 0 && i+span[i]+1 < ch.length && ch[i-span[i]-1] == ch[i+span[i]+1])
                span[i]++;
            if (i + span[i] > R) {
                C = i;
                R = i + span[i];
            }
            if (center == -1 || span[i] > span[center])
                center = i;
        }
        // System.out.println(Arrays.toString(span));
        return s.substring(center/2-span[center]/2, center/2-span[center]/2+span[center]);
    }
}


// manacher algorithm, without using the auxiliary string 
class Solution {
    public String longestPalindrome(String s) {
        char[] ch = s.toCharArray();
        int[] span = new int[ch.length*2+1];
        int C = 0, R = 0, center = -1;
        for (int i = 0; i < span.length; i++) {
            int j = 2 * C - i;
            span[i] = j < 0 ? 0 : Math.min(R-i, span[j]);
            while (i-span[i]-1 >= 0 && i+span[i]+1 < span.length && 
                   ((i+span[i]+1) % 2 == 0 || ch[(i-span[i]-1)/2] == ch[(i+span[i]+1)/2]))
                span[i]++;
            if (i + span[i] > R) {
                C = i;
                R = i + span[i];
            }
            if (center == -1 || span[i] > span[center])
                center = i;
        }
        // System.out.println(Arrays.toString(span));
        return s.substring(center/2-span[center]/2, center/2-span[center]/2+span[center]);
    }
}


// another writing style of Manacher 
class Solution {
    public String longestPalindrome(String s) {
        char[] ch = s.toCharArray();
        int[] span = new int[ch.length*2+1];
        int C = 0, R = 0, center = -1;
        for (int i = 0; i < span.length; i++) {
            int j = 2 * C - i;
            if (j >= 0 && R-i > span[j])
                span[i] = span[j];
            else {
                // span[i] = j < 0 ? 0 : R-i;
                span[i] = Math.max(0, R-i);
                while (i-span[i]-1 >= 0 && i+span[i]+1 < span.length && 
                       ((i+span[i]+1) % 2 == 0 || ch[(i-span[i]-1)/2] == ch[(i+span[i]+1)/2]))
                    span[i]++;
            }
            if (i + span[i] > R) {
                C = i;
                R = i + span[i];
            }
            if (center == -1 || span[i] > span[center])
                center = i;
        }
        // System.out.println(Arrays.toString(span));
        return s.substring(center/2-span[center]/2, center/2-span[center]/2+span[center]);
    }
}

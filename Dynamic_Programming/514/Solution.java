class Solution {
    public int findRotateSteps(String ring, String key) {
        char[] rchar = ring.toCharArray(), kchar = key.toCharArray();
        int[][] pos = new int[26][rchar.length];
        int[] indices = new int[26];
        for (int i = 0; i < rchar.length; i++) 
            pos[rchar[i]-'a'][indices[rchar[i]-'a']++] = i + 1;
        int[][] dp = new int[kchar.length][rchar.length];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < kchar.length; i++) {
            min = Integer.MAX_VALUE;
            for (int j : pos[kchar[i]-'a']) {
                if (j == 0)
                    break;
                j--;
                dp[i][j] = Integer.MAX_VALUE;
                if (i == 0) {
                    dp[i][j] = Math.min(j, rchar.length-j);
                } else {
                    for (int jj : pos[kchar[i-1]-'a']) {
                        if (jj == 0)
                            break;
                        jj--;
                        int dist = Math.min(Math.abs(j-jj), rchar.length - Math.abs(j-jj));
                        dp[i][j] = Math.min(dp[i][j], dp[i-1][jj] + dist);
                    }
                }
                min = Math.min(min, dp[i][j]);
            }
        }
        return min + kchar.length;
    }
}


// optimize the space complexity to be 1 dimension DP 
class Solution {
    public int findRotateSteps(String ring, String key) {
        char[] rchar = ring.toCharArray(), kchar = key.toCharArray();
        int[][] pos = new int[26][rchar.length];
        int[] indices = new int[26];
        for (int i = 0; i < rchar.length; i++) 
            pos[rchar[i]-'a'][indices[rchar[i]-'a']++] = i + 1;
        int[][] dp = new int[2][rchar.length];
        int[] cur = dp[0], pre = dp[1];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < kchar.length; i++) {
            min = Integer.MAX_VALUE;
            for (int j : pos[kchar[i]-'a']) {
                if (j == 0)
                    break;
                j--;
                cur[j] = Integer.MAX_VALUE;
                if (i == 0) {
                    cur[j] = Math.min(j, rchar.length-j);
                } else {
                    for (int jj : pos[kchar[i-1]-'a']) {
                        if (jj == 0)
                            break;
                        jj--;
                        int dist = Math.min(Math.abs(j-jj), rchar.length - Math.abs(j-jj));
                        cur[j] = Math.min(cur[j], pre[jj] + dist);
                    }
                }
                min = Math.min(min, cur[j]);
            }
            int[] temp = cur;
            cur = pre;
            pre = temp;
        }
        return min + kchar.length;
    }
}

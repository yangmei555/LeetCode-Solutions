class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        if (m == 0 && n == 0)
            return 0;
        int[][] index = new int[m+1][n+1];
        for (int l = 0; l < strs.length; l++) {
            String s = strs[l];
            int start = 0, count = 0, len = s.length();
            while ((start=s.indexOf('0', start)) != -1) {
                count++;
                start++;
            } 
            for (int i = m; i >= count; i--) {
                for (int j = n; j >= len-count ;j--) {
                    index[i][j] = index[i][j] > 1+index[i-count][j-len+count] ? 
                                    index[i][j] : 1+index[i-count][j-len+count];
                }
            }
        }
        return index[m][n];
    }
}


class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        if (m == 0 && n == 0)
            return 0;
        Integer track[][][] = new Integer[strs.length][m+1][n+1];
        return helper(strs, m, n, 0, track);
    }
    
    public int helper(String[] strs, int m, int n, int index, Integer[][][] track) {
        if (m < 0 || n < 0)
            return -1;
        if (index == strs.length)
            return 0;    
        if (track[index][m][n] != null)
            return track[index][m][n];
        String s = strs[index];
        int start = 0, count = 0, len = s.length();
        while ((start=s.indexOf('0', start)) != -1) {
            count++;
            start++;
        } 
        int include = helper(strs, m - count, n - len + count, index + 1, track);
        int notinclude = helper(strs, m, n, index + 1, track);
        track[index][m][n] = 1 + include > notinclude ? 1 + include : notinclude;
        return track[index][m][n];
    }
}

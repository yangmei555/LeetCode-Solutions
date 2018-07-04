class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int res = 0;
        int[] dp = new int[matrix[0].length], stack = new int[matrix[0].length+1];
        int index = 0;
        stack[index++] = -1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                dp[j] = matrix[i][j] == '0' ? 0 : dp[j] + 1;
                while (index > 1 && dp[stack[index-1]] > dp[j]) {
                    int pre = dp[stack[--index]];
                    int area = pre * (j - 1 - stack[index-1]);
                    res = res > area ? res : area;
                }
                stack[index++] = j;
            }
            while (index > 1) {
                int pre = dp[stack[--index]];
                int area = pre * (dp.length - 1 - stack[index-1]);
                res = res > area ? res : area;
            }
        }
        return res;
    }
}

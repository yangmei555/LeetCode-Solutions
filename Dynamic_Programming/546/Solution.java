class Solution {
    public int removeBoxes(int[] boxes) {
        List<int[]> list = new LinkedList<>();
        int index = 0;
        while (index < boxes.length) {
            int start = index++;
            while (index < boxes.length && boxes[start] == boxes[index])
                index++;
            list.add(new int[]{boxes[start], index-start});
        }
        int[][] input = new int[list.size()][];
        for (int i = 0; i < list.size(); i++)
            input[i] = list.get(i);
        int[][] dp = new int[input.length][input.length];
        for (int len = 1; len <= input.length; len++) {
            for (int i = 0; i + len - 1 < input.length; i++) {
                int j = i + len - 1;
                if (i == j) {
                    dp[i][j] = input[i][1] * input[i][1];
                } else {
                    int count = input[i][1], other = 0, start = i+1;
                    for (int k = i+1; k <= j; k++) {
                        dp[i][j] = Math.max(dp[i][j], dp[i][k-1] + dp[k][j]);
                        if (input[i][0] == input[k][0]) {
                            count += input[k][1];
                            other += dp[start][k-1];
                            if (input[i][0] == input[j][0] && k != j)
                                dp[i][j] = Math.max(dp[i][j], other + 
                                            (count+input[j][1])*(count+input[j][1]) + dp[k+1][j-1]);
                            start = k+1;
                        } 
                    }
                    if (start < input.length)
                        other += dp[start][j];
                    dp[i][j] = Math.max(dp[i][j], other + count * count);
                    count = input[j][1];
                    other = 0;
                    int end = j-1;
                    for (int k = j-1; k >= i; k--) {
                        dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k+1][j]);
                        if (input[j][0] == input[k][0]) {
                            count += input[k][1];
                            other += dp[k+1][end];
                            if (input[i][0] == input[j][0] && k != i)
                                dp[i][j] = Math.max(dp[i][j], other + 
                                            (count+input[i][1])*(count+input[i][1]) + dp[i+1][k-1]);
                            end = k-1;
                        }
                    }
                    if (end >= 0)
                        other += dp[i][end];
                    dp[i][j] = Math.max(dp[i][j], other + count * count);
                    if (input[i][0] == input[j][0])
                        dp[i][j] = Math.max(dp[i][j], dp[i+1][j-1] + 
                                                (input[i][1]+input[j][1])*(input[i][1]+input[j][1]));
                }
            }
        }
        // System.out.println(Arrays.deepToString(input));
        return dp[0][input.length-1];
    }
}

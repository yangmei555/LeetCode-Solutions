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


// key observation: the normal definition of the subproblem is not self-contained, i.e., only 
// dp[left][right] is not enough. its solution relies on information external to the subproblem 
// itself. (but from some complex points of view, if you just define dp[left][right] to be the 
// result totally ignoring its left side and right side, it is also OK, but will need to pay more 
// work on correctly resolving the dp recurrence, just like the above solution)
// so, add 1 dimension: 'suffix', how many elements behind [left, right] are the same as the 'right' 
// element. actually, do not bother thinking which elements should be removed first or should be 
// removed last. the sequence of removing is not that important here. which is important is to find 
// out a way to record some information beyond the current sub array. 
class Solution {
    public int removeBoxes(int[] boxes) {
        List<int[]> list = new ArrayList<>();
        int index = 0;
        while (index < boxes.length) {
            int start = index++;
            while (index < boxes.length && boxes[start] == boxes[index])
                index++;
            list.add(new int[]{boxes[start], index - start});
        }
        int[][] reBoxes = new int[list.size()][];
        for (int i = 0; i < reBoxes.length; i++) 
            reBoxes[i] = list.get(i);
        int[][][] memo = new int[reBoxes.length][reBoxes.length][boxes.length];
        return helper(reBoxes, 0, reBoxes.length-1, 0, memo);
    }
    
    public int helper(int[][] boxes, int left, int right, int suffix, int[][][] memo) {
        if (left > right)
            return 0;
        if (memo[left][right][suffix] != 0)
            return memo[left][right][suffix];
        int res = helper(boxes, left, right-1, 0, memo) + 
                        (boxes[right][1] + suffix) * (boxes[right][1] + suffix);
        for (int i = left; i < right; i++) {
            if (boxes[i][0] == boxes[right][0]) {
                int ret = helper(boxes, left, i, boxes[right][1] + suffix, memo) + 
                            helper(boxes, i+1, right-1, 0, memo);
                res = Math.max(res, ret);
            }
        }
        memo[left][right][suffix] = res;
        return res;
    }
}

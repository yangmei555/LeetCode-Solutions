class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int[] dp = new int[pairs.length];
        int max = 0;
        for (int i = 1; i < pairs.length; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0])
                    dp[i] = dp[i] > 1 + dp[j] ? dp[i] : 1 + dp[j];
            }
            max = max > dp[i] ? max : dp[i];
        }
        return max + 1;
    }
}


class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        int res = 0, max = Integer.MIN_VALUE;
        for (int[] in : pairs) {
            if (in[0] > max) {
                res++;
                max = in[1];
            }
        }
        return res;
    }
}


class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        int res = 0;
        for (int i = 0; i < pairs.length; i++) {
            res++;
            int cur = pairs[i][1];
            while (i < pairs.length && pairs[i][0] <= cur)
                i++;
            i--;
        }
        return res;
    }
}

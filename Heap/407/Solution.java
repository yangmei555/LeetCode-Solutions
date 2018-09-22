// similar to the 1 dimension situation, process the cell with the lowest value 
class Solution {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length == 0 || heightMap[0].length == 0)
            return 0;
        int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int row = heightMap.length, col = heightMap[0].length;
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[2] - i2[2];
            }
        });
        for (int j = 0; j < col; j++) {
            queue.offer(new int[]{0, j, heightMap[0][j]});
            heightMap[0][j] = Integer.MAX_VALUE;
            queue.offer(new int[]{row-1, j, heightMap[row-1][j]});
            heightMap[row-1][j] = Integer.MAX_VALUE;
        }
        for (int i = 1; i < row-1; i++) {
            queue.offer(new int[]{i, 0, heightMap[i][0]});
            heightMap[i][0] = Integer.MAX_VALUE;
            queue.offer(new int[]{i, col-1, heightMap[i][col-1]});
            heightMap[i][col-1] = Integer.MAX_VALUE;
        }
        int res = 0, max = 0;
        while (!queue.isEmpty()) {
            int[] rc = queue.poll();
            if (rc[2] > max)
                max = rc[2];
            else
                res += max - rc[2];
            for (int[] d : dir) {
                int x = rc[0] + d[0], y = rc[1] + d[1];
                if (x >= 0 && x < row && y >= 0 && y < col && heightMap[x][y] != Integer.MAX_VALUE) {
                    queue.offer(new int[]{x, y, heightMap[x][y]});
                    heightMap[x][y] = Integer.MAX_VALUE;
                }
            }
        }
        return res;
    }
}


// do the exact same thing as the 1 dimension version, and then subtract the spilled water 
class Solution {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length == 0 || heightMap[0].length == 0)
            return 0;
        int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int[][] dp = new int[heightMap.length][heightMap[0].length];
        for (int i = 0; i < dp.length; i++) {
            int lmax = 0;
            for (int j = 0; j < dp[0].length; j++) {
                lmax = Math.max(lmax, heightMap[i][j]);
                dp[i][j] = lmax;
            }
            int rmax = 0;
            for (int j = dp[0].length-1; j >= 0; j--) {
                rmax = Math.max(rmax, heightMap[i][j]);
                dp[i][j] = Math.min(dp[i][j], rmax);
            }
        }
        for (int j = 0; j < dp[0].length; j++) {
            int umax = 0;
            for (int i = 0; i < dp.length; i++) {
                umax = Math.max(umax, heightMap[i][j]);
                dp[i][j] = Math.min(dp[i][j], umax);
            }
            int dmax = 0;
            for (int i = dp.length-1; i >= 0; i--) {
                dmax = Math.max(dmax, heightMap[i][j]);
                dp[i][j] = Math.min(dp[i][j], dmax);
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++)
                res += dp[i][j] - heightMap[i][j];
        }
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 1; i < heightMap.length-1; i++) {
                for (int j = 1; j < heightMap[0].length-1; j++) {
                    if (dp[i][j] - heightMap[i][j] != 0) {
                        for (int[] d : dir) {
                            int x = i + d[0], y = j + d[1];
                            if (dp[i][j] > dp[x][y]) {
                                int temp = dp[i][j];
                                dp[i][j] = Math.max(heightMap[i][j], dp[x][y]);
                                res -= temp - dp[i][j];
                                flag = true;
                            }
                        }
                    }
                }
            }
        }
        return res;
    }
}

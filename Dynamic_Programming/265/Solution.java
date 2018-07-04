class Solution {
    public int minCostII(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0)
            return 0;
        int n = costs.length, k = costs[0].length;
        int minindex = -1, min1 = 0, min2 = 0;
        for (int i = 0; i < n; i++) {
            int curindex = 0, curmin1 = Integer.MAX_VALUE, curmin2 = Integer.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                int cost = costs[i][j] + (j == minindex ? min2 : min1);
                if (cost < curmin1) {
                    curindex = j;
                    curmin2 = curmin1;
                    curmin1 = cost;
                } else if (cost < curmin2) {
                    curmin2 = cost;
                }
            }
            minindex = curindex;
            min1 = curmin1;
            min2 = curmin2;
        }
        return min1;
    }
}

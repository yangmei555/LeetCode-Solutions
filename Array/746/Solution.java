class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int fst = cost[0], snd = cost[1], min = 0;
        for (int i = 2; i < cost.length; i++) {
            min = fst < snd ? fst : snd;
            fst = snd;
            snd = min + cost[i];
        }
        return fst < snd ? fst : snd;
    }
}


class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n1 = 0, n2 = 0;
        for (int i = 2; i <= cost.length; i++) {
            int temp = n2;
            n2 = Math.min(n2 + cost[i-1], n1 + cost[i-2]);
            n1 = temp;
        }
        return n2;
    }
}

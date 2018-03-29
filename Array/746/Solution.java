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

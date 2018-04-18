class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] index = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            index[i] = Integer.MAX_VALUE;
            for (int c : coins) {
                if (i < c || index[i - c] == Integer.MAX_VALUE)
                    continue;
                if (1 + index[i - c] < index[i])
                    index[i] = 1 + index[i - c];
            }
        }
        return index[amount] == Integer.MAX_VALUE ? -1 : index[amount];
    }
}

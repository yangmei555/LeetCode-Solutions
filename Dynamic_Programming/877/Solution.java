class Solution {
    public boolean stoneGame(int[] piles) {
        Integer[][] memo = new Integer[piles.length][piles.length];
        return helper(piles, 0, piles.length-1, memo) > 0 ? true : false;
    }
    
    public int helper(int[] piles, int left, int right, Integer[][] memo) {
        if (left == right)
            return piles[left];
        if (memo[left][right] != null)
            return memo[left][right];
        memo[left][right] = Math.max(piles[left] - helper(piles, left+1, right, memo), 
                                        piles[right] - helper(piles, left, right-1, memo));
        return memo[left][right];
    }
}

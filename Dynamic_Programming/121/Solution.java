class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int res = 0, min = Integer.MAX_VALUE;
        for (int p : prices) {
            if (p < min) {
                min = p;
            } else {
                res = res > p - min ? res : p - min;
            }
        }
        return res;
    }
}

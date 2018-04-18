class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int sell = 0, presell = 0, buy = Integer.MIN_VALUE, prebuy = Integer.MIN_VALUE;
        for (int p : prices) {
            prebuy = buy;
            buy = buy > presell - p ? buy : presell - p;
            presell = sell;
            sell = sell > prebuy + p ? sell : prebuy + p;
        }
        return sell;
    }
}

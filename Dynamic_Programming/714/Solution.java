class Solution {
    public int maxProfit(int[] prices, int fee) {
        int buy = Integer.MIN_VALUE, sell = 0, temp;
        for (int p : prices) {
            temp = buy;
            buy = sell - p > buy ? sell - p : buy;
            sell = temp > sell - p + fee ? temp + p - fee : sell;
        }
        return sell;
    }
}

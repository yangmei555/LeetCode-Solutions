class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int min = prices[0], res = 0;
        for (int i = 0; i < prices.length; i++) {
            if (i == prices.length - 1) {
                if (prices[i] > min)
                    res += prices[i] - min;
            } else if (prices[i] > prices[i+1]) {
                res += prices[i] - min;
                min = prices[i+1];
            }
        }
        return res;
    }
}


class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int buy = Integer.MIN_VALUE, sell = 0, temp = 0;
        for (int p : prices) {
            temp = buy;
            buy = buy > sell - p ? buy : sell - p;
            sell = sell > temp + p ? sell : temp + p;
        }
        return sell;
    }
}


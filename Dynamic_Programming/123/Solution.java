class Solution {
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            int ret1 = helper(prices, 0, i), ret2 = helper(prices, i+1, prices.length-1);
            res = res > ret1 + ret2 ? res : ret1 + ret2;
        }
        return res;
    }
    
    public int helper(int[] prices, int start, int end) {
        if (start > end)
            return 0;
        int min = Integer.MAX_VALUE, res = 0;
        for (int i = start; i <= end; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else {
                res = res > prices[i] - min ? res : prices[i] - min;
            }
        }
        return res;
    }
}


class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0)
            return 0;
        int[] left = new int[prices.length], right = new int[prices.length];
        int min = prices[0], max = prices[prices.length-1];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min)
                min = prices[i];
            left[i] = left[i-1] > prices[i] - min ? left[i-1] : prices[i] - min;
        }
        for (int i = prices.length-2; i >= 0; i--) {
            if (prices[i] > max)
                max = prices[i];
            right[i] = right[i+1] > max - prices[i] ? right[i+1] : max - prices[i];
        }
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            res = res > left[i] + right[i] ? res : left[i] + right[i];
        }
        return res;
    }
}


class Solution {
    public int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE, sell1 = 0, sell2 = 0;
        for (int p : prices) {
            sell2 = sell2 > buy2 + p ? sell2 : buy2 + p;
            buy2 = buy2 > sell1 - p ? buy2 : sell1 - p;
            sell1 = sell1 > buy1 + p ? sell1 : buy1 + p;
            buy1 = buy1 > -p ? buy1 : -p;
        }
        return sell2;
    }
}

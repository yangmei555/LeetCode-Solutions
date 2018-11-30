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


// use two more prev variables 
class Solution {
    public int maxProfit(int[] prices) {
        int buy = Integer.MIN_VALUE, sell = 0, prev1 = 0, prev2 = 0;
        for (int p : prices) {
            // int temp = buy;
            buy = Math.max(buy, prev2 - p);
            sell = Math.max(sell, buy + p);
            prev2 = prev1;
            prev1 = sell;
        }
        return sell;
    }
}


// use only 1 more prev variable 
class Solution {
    public int maxProfit(int[] prices) {
        int buy = Integer.MIN_VALUE, sell = 0, prev = 0;
        for (int p : prices) {
            // int temp = buy;
            buy = Math.max(buy, prev - p);
            prev = sell;
            sell = Math.max(sell, buy + p);
        }
        return sell;
    }
}

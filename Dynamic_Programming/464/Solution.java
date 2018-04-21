class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (sum < desiredTotal)
            return false;
        else if (desiredTotal == 0)
            return true;
        if (sum == desiredTotal)
            return maxChoosableInteger % 2 == 1 ? true : false;
        int used = 0;
        Boolean[] index = new Boolean[1 << maxChoosableInteger];
        return helper(maxChoosableInteger, desiredTotal, used, index);
    }
    
    public boolean helper(int max, int total, int used, Boolean[] index) {
        if (index[used] != null) 
            return index[used];
        if (total <= 0) {
            index[used] = false;
            return false;
        }
        for (int i = 1; i <= max; i++) {
            if ((used & (1 << (i-1))) == 0) {
                if (helper(max, total - i, used | (1 << (i-1)), index) == false) {
                    index[used] = true;
                    return true;
                }
            }
        }
        index[used] = false;
        return false;
    }
}

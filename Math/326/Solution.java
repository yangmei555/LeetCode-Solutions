class Solution {
    public boolean isPowerOfThree(int n) {
        if (n == 0)
            return false;
        while (n != 1) {
            if (n % 3 == 0)
                n /= 3;
            else 
                return false;
        }
        return true;
    }
}


class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0)
            return false;
        // 1162261467 is 3 ^ 19
        return 1162261467 % n == 0;
    }
}

class Solution {
    public int primePalindrome(int N) {
        if (N <= 2)
            return 2;
        char[] ch = String.valueOf(N).toCharArray();
        int half = (ch.length+1)/2;
        int len = half * 2 - 1, bound = (int)Math.pow(10, half);
        // the first half of the palindrome
        int start = bound / 10;
        while (start <= 19999) {
            int dup = bound / 10;
            for (int i = 0; i < 2; i++, len++, start = dup) {
                if (len >= ch.length) {
                    while (start < bound) {
                        int ret = helper(start, len, N);
                        if (ret != -1)
                            return ret;
                        start++;
                    }
                }
            }
            bound *= 10;
        }
        return N;
    }
    
    public int helper(int start, int len, int N) {
        int n = len % 2 == 0 ? start : start / 10;
        while (n != 0) {
            start = start * 10 + n % 10;
            n /= 10;
        }
        if (start < N)
            return -1;
        for (int i = 2; i * i <= start; i++) {
            if (start % i == 0)
                return -1;
        }
        return start;
    }
}

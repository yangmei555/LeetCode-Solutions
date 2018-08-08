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


class Solution {
    public int primePalindrome(int N) {
        if (N <= 2)
            return 2;
        for (int i = (N&1) == 0 ? N+1 : N; i < 200000000 ; i+= 2) {
            if (helper1(i) && helper2(i))
                return i;
            // all palindromes with even number digits are dividable by 11, so not primes
            if (i > 10000000 && i < 100000000)
                i = 100000001 - 2;
        }
        return N;
    }
    
    public boolean helper1(int n) {
        char[] ch = String.valueOf(n).toCharArray();
        for (int left = 0, right = ch.length-1; left < right; left++, right--) {
            if (ch[left] != ch[right])
                return false;
        }
        return true;
    }
    
    public boolean helper2(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}


class Solution {
    public int primePalindrome(int N) {
        if (N >= 8 && N <= 11)
            return 11;
        char[] ch = String.valueOf(N).toCharArray();
        int start = 0;
        for (int i = 0; i < (ch.length+1)/2; i++)
            start = start * 10 + ch[i] - '0';
        for (int i = start; ; i++) {
            int num = i, temp = i / 10;
            while (temp != 0) {
                num = num * 10 + temp % 10;
                temp /= 10;
            }
            if (num >= N && helper(num))
                return num;
        }
        // return -1;
    }
    
    public boolean helper(int n) {
        if (n == 1)
            return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}

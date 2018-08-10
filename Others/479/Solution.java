class Solution {
    public int largestPalindrome(int n) {
        if (n == 1)
            return 9;
        long bound = (long)Math.pow(10, n) - 1;
        long start = bound * bound;
        for (int i = 0; i < n; i++)
            start /= 10;
        // search for palindromes with 2*n digits
        for (long i = start; i >= Math.pow(10, n-1); i--) {
            long palin = i, temp = i;
            while (temp != 0) {
                palin = palin * 10 + temp % 10;
                temp /= 10;
            }
            for (long j = bound; j * j >= palin; j--) {
                if (palin % j == 0)
                    return (int)(palin % 1337);
            }
        }

        // search for palindromes with 2*n-1 digits

        // for (long i = bound; i >= Math.pow(10, n-1); i--) {
        //     long palin = i, temp = i/10;
        //     while (temp != 0) {
        //         palin = palin * 10 + temp % 10;
        //         temp /= 10;
        //     }
        //     for (long j = bound; j * j >= palin; j--) {
        //         if (palin % j == 0)
        //             return (int)(palin % 1337);
        //     }
        // }
        return -1;
    }
}


class Solution {
    public int largestPalindrome(int n) {
        if (n == 1)
            return 9;
        long bound = (long)Math.pow(10, n) - 1;
        long start = bound * bound;
        long upper = bound, lower = (long)Math.pow(10, n-1);
        for (int i = 0; i < n; i++)
            start /= 10;
        for (long i = start; i >= Math.pow(10, n-1); i--) {
            long palin = i, temp = i;
            while (temp != 0) {
                palin = palin * 10 + temp % 10;
                temp /= 10;
            }
            if (helper(palin, lower, upper))
                return (int)(palin % 1337);
        }
        // for (long i = bound; i >= Math.pow(10, n-1); i--) {
        //     long palin = i, temp = i/10;
        //     while (temp != 0) {
        //         palin = palin * 10 + temp % 10;
        //         temp /= 10;
        //     }
        //     if (helper(palin, lower, upper))
        //         return (int)(palin % 1337);
        // }
        return -1;
    }
    
    // using this way to search is much faster than the first solution 
    public boolean helper(long palin, long lower, long upper) {
        long left = (long)Math.sqrt(palin), right = (long)Math.sqrt(palin);
        while (left * right != palin) {
            if (left * right < palin) {
                right++;
                if (right > upper)
                    return false;
            } else {
                left--;
                if (left < lower)
                    return false;
            }
        }
        return true;
    }
}

class Solution {
    public int integerReplacement(int n) {
        if (n <= 1)
            return 0;
        int res = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                n >>>= 1;
                res++;
            } else {
                if (n == 3 || (n-1) % 4 == 0) {
                    n -= 1;
                } else {
                    n += 1;
                }
                res++;
            }
        }
        return res;
    }
}


class Solution {
    public int integerReplacement(int n) {
        if (n == 1 || n == -1)
            return 0;
        if (n % 2 == 0) {
            return 1 + integerReplacement(n/2);
        } else {
            int n1 = integerReplacement(n+1);
            int n2 = integerReplacement(n-1);
            return (n1 < n2 ? n1 : n2) + 1;
        }
    }
}

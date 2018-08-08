class Solution {
    public int consecutiveNumbersSum(int N) {
        int res = 0;
        for (int i = 1; i * i <= N; i++) {
            if (N % i == 0) {
                for (int j = 0, a = i; j < 2; j++, a = N / a) {
                    if (a % 2 != 0) {
                        if (N / a - a / 2 >= 1)
                            res++;
                    }
                    if (N / a % 2 == 1) {
                        int r = N / a / 2;
                        if (r >= a)
                            res++;
                    }
                    if (a == N / a)
                        break;
                }
            }
        }
        return res;
    }
}


// actually, in the inner for loop of the first solution, those two 'if' will meet 
// exactly once .    if N = a * b, and b = 2 * k + 1, then 
// if a - k >= 1, N = (a-k) + ... + a + ... + (a+k) 
// if k - a + 1 >= 1, N = k-a+1 + ... + k + (k+1) + ... + (k+a) 
// these two cases are disjoint 
// so it eqauls to find the number of odd factors of N 
class Solution {
    public int consecutiveNumbersSum(int N) {
        int res = 0;
        for (int i = 1; i * i <= N; i++) {
            if (N % i == 0) 
                res += (i % 2 == 1 ? 1 : 0) + (N / i % 2 == 1 && N != i * i ? 1 : 0);
        }
        return res;
    }
}


class Solution {
    public int consecutiveNumbersSum(int N) {
        int res = 0, len = 1;
        // (x+1)+(x+2)+...+(x+len) == N 
        // the length of such consecutive numbers can not exceed sqrt(N*2) 
        while (len * len < N * 2) {
            if ((N - len * (len + 1) / 2) % len == 0)
                res++;
            len++;
        }
        return res;
    }
}


// just count the number of odd factors 
class Solution {
    public int consecutiveNumbersSum(int N) {
        while (N % 2 == 0)
            N /= 2;
        int res = 1, d = 3;
        while (N != 1) {
            int count = 0;
            while (N % d == 0) {
                N /= d;
                count++;
            }
            res *= count + 1;
            d += 2;
        }
        return res;
    }
}


// the optimized version of last solution, optimize large primes 
class Solution {
    public int consecutiveNumbersSum(int N) {
        while (N % 2 == 0)
            N /= 2;
        int res = 1, d = 3;
        while (N >= d * d) {
            int count = 0;
            while (N % d == 0) {
                N /= d;
                count++;
            }
            res *= count + 1;
            d += 2;
        }
        return N == 1 ? res : res * 2;
    }
}

class Solution {
    public int fib(int N) {
        int a = 0, b = 1;
        if (N == 0)
            return a;
        for (int i = 2; i <= N; i++) {
            int temp = b;
            b += a;
            a = temp;
        }
        return b;
    }
}


// O(logN) solution 
class Solution {
    public int fib(int N) {
        if (N == 0)
            return 0;
        return helper(N-1)[0];
    }
    
    public int[] helper(int n) {
        if (n == 0)
            return new int[]{1, 0, 0, 1};
        int[] ret = helper(n/2);
        int[] res = new int[]{ret[0] * ret[0] + ret[1] * ret[2], ret[0] * ret[1] + ret[1] * ret[3], 
                                ret[2] * ret[0] + ret[3] * ret[2], ret[2] * ret[1] + ret[3] * ret[3]};
        if (n % 2 == 0)
            return res;
        else
            return new int[]{res[0] + res[2], res[0], res[2] + res[3], res[2]};
    }
}

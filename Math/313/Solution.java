class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n == 1)
            return 1;
        int[] res = new int[n];
        res[0] = 1;
        int[] index = new int[primes.length], temp = new int[primes.length];
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                temp[j] = primes[j] * res[index[j]];
                min = min < temp[j] ? min : temp[j];
            }
            res[i] = min;
            for (int j = 0; j < primes.length; j++) {
                if (min == temp[j])
                    index[j]++;
            }
        }
        return res[n-1];
    }
}


class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n == 1)
            return 1;
        int[] res = new int[n];
        res[0] = 1;
        int[] index = new int[primes.length], temp = new int[primes.length];
        Arrays.fill(temp, 1);
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                if (temp[j] == res[i-1]) {
                    temp[j] = primes[j] * res[index[j]];
                    index[j]++;
                }
                min = min < temp[j] ? min : temp[j];
            }
            res[i] = min;
        }
        return res[n-1];
    }
}

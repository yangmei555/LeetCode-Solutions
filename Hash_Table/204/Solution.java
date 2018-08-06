class Solution {
    public int countPrimes(int n) {
        if (n < 3)
            return 0;
        int res = 1;
        int[] p = new int[n];
        for (int i = 3; i*i <= n; i += 2) {
            if (p[i] == 0) {
                for (int j = i; j * i < n; j += 2)
                    p[j * i] = 1;
            }
        }
        for (int i = 3; i < n; i += 2) {
            if (p[i] == 0) {
                // System.out.println(i);
                res++;
            }
        }
        return res;
    }
}


class Solution {
    public int countPrimes(int n) {
        if (n < 3)
            return 0;
        int res = 1;
        // space reduced compared with int[] 
        boolean[] p = new boolean[n];
        for (int i = 3; i*i <= n; i += 2) {
            if (!p[i]) {
                for (int j = i; j * i < n; j += 2)
                    p[j * i] = true;
            }
        }
        for (int i = 3; i < n; i += 2) {
            if (!p[i]) {
                // System.out.println(i);
                res++;
            }
        }
        return res;
    }
}

class Solution {
    int[] digits = new int[]{0, 1, 6, 8, 9, 6, 8, 9, 1, 0};
    public int strobogrammaticInRange(String low, String high) {
        long l = Long.valueOf(low), h = Long.valueOf(high), a = l, b = h;
        int ldigit = 0, hdigit = 0;
        while (l != 0) {
            ldigit++;
            l /= 10;
        }
        while (h != 0) {
            hdigit++;
            h /= 10;
        }
        if (ldigit == 0)
            ldigit++;
        if (hdigit == 0)
            hdigit++;
        int res = 0;
        long base = 1;
        for (int i = 1; i < ldigit; i++)
            base *= 10;
        for (int i = ldigit; i <= hdigit; i++) {
            List<Long> list = helper(i, i, base, 1);
            for (long n : list) {
                if (a <= n && n <= b)
                    res++;
            }
            base *= 10;
        }
        return res;
    }
    
    public List<Long> helper(int len, int curlen, long base1, long base2) {
        List<Long> res = new LinkedList<>();
        if (curlen == 0) {
            res.add(0 * base1);
        } else if (curlen == 1) {
            res.add(0 * base1);
            res.add(1 * base1);
            res.add(8 * base1);
        } else {
            List<Long> ret = helper(len, curlen-2, base1/10, base2*10);
            for (long r : ret) {
                for (int i = len == curlen ? 1 : 0; i < digits.length/2; i++) 
                    res.add(digits[i] * base1 + r  + digits[digits.length-1-i] * base2);   
            }
        }
        return res;
    }
}

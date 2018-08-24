class Solution {
    public String smallestGoodBase(String n) {
        long num = Long.valueOf(n);
        // reduce the search space to where the length of the 1's are >= 4
        for (long i = 2; i * i * i < num; i++) {
            if ((num-1) % i == 0) {
                long temp = num, k = i;
                while (temp != 0) {
                    if (temp % k != 1)
                        break;
                    temp /= k;
                }
                if (temp == 0)
                    return k + "";
            }
        }
        // when 1 + k + k*k == num 
        double val = (Math.sqrt(num * 4 - 3) - 1) / 2;
        if ((long)val != 1) {
            if (Math.abs(val - Math.round(val)) <= 1e-6)
                return (long)Math.round(val) + "";
        }
        return num - 1 + "";
    }
}


class Solution {
    public String smallestGoodBase(String n) {
        long num = Long.valueOf(n);
        // reduce the search space to where the length of the 1's are >= 5
        for (long i = 2; i * i * i * i < num; i++) {
            if ((num-1) % i == 0) {
                long temp = num, k = i;
                while (temp != 0) {
                    if (temp % k != 1)
                        break;
                    temp /= k;
                }
                if (temp == 0)
                    return k + "";
            }
        }
        // when 1 + k + k*k + k*k*k == num
        long val = (long)Math.pow(num, 1.0/3);
        while (val * val * val < num) {
            if (val * val * val + val * val + val + 1 == num && val != 1)
                return val + "";
            val++;
        }
        // when 1 + k + k*k == num 
        val = (long)((Math.sqrt(num * 4 - 3) - 1) / 2);
        if (val * val + val + 1 == num && val != 1)
            return val + "";
        return num - 1 + "";
    }
}

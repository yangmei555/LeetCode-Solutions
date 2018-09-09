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


// notice that, if 1 + b + b^2 + b^3 + ... + b^(k-1) == num, 
// then, b^(k-1) < num < (1+b)^(k-1), num^(1/(k-1)) - 1 < b < num^(1/(k-1)) 
// so b can only possibly be (int)num^(1/(k-1)) . 
// this will take O(logN * logN), since the upper bound of k is logN and each round take O(k) 
class Solution {
    public String smallestGoodBase(String n) {
        long num = Long.valueOf(n);
        int k = (int)(Math.log(num + 1) / Math.log(2));
        while (k >= 3) {
            // System.out.println(k);
            int cand = (int)Math.pow(num, 1.0 / (k-1));
            if ((num - 1) % cand == 0) {
                long sum = 0, cur = 1;
                for (int i = 0; i < k; i++) {
                    sum += cur;
                    cur *= cand;
                }
                if (sum == num)
                    return cand + "";
            }
            k--;
        }
        return num - 1 + "";
    }
}


// use binary search to locate the base 
class Solution {
    public String smallestGoodBase(String n) {
        long num = Long.valueOf(n);
        int k = (int)(Math.log(num + 1) / Math.log(2));
        while (k >= 3) {
            // System.out.println(k);
            int upper = (int)Math.pow(num, 1.0 / (k-1));
            int lower = (int)Math.pow(num + 1, 1.0 / k);
            while (lower <= upper) {
                int mid = (lower + upper) / 2;
                long sum = 0, cur = 1;
                for (int i = 0; i < k; i++) {
                    sum += cur;
                    cur *= mid;
                }
                if (sum == num)
                    return mid + "";
                else if (sum < num)
                    lower = mid + 1;
                else
                    upper = mid - 1;
            }
            k--;
        }
        return num - 1 + "";
    }
}

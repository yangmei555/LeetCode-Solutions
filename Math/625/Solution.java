class Solution {
    int[][] require = {{0, 0, 0, 0}, {1, 0, 0, 0}, {0, 1, 0, 0}, {2, 0, 0, 0}, 
                       {0, 0, 1, 0}, {1, 1, 0, 0}, {0, 0, 0, 1}, {3, 0, 0, 0}, {0, 2, 0, 0}};
    public int smallestFactorization(int a) {
        int[] counts = new int[4], res = new int[10];
        int origin = a;
        a = divide(counts, a, 2);
        a = divide(counts, a, 3);
        a = divide(counts, a, 5);
        a = divide(counts, a, 7);
        if (a != 1)
            return 0;
        int val = helper(counts, origin, 0, res);
        return val == Integer.MAX_VALUE ? 0 : val;
    }
    
    public int helper(int[] counts, int a, int index, int[] res) {
        int val = Integer.MAX_VALUE;
        if (a == 1 || index == res.length) {
            if (index == res.length && a != 1)
                return Integer.MAX_VALUE;
            if (index == 0 && a == 1)
                return 1;
            val = 0; 
            for (int i = 0; i < index; i++) { 
                if (val != (val * 10 + res[i]) / 10)
                    return Integer.MAX_VALUE;
                val = val * 10 + res[i];
            }
            return val;
        }
        for (int i = 2; i <= 9; i++) {
            if (index != 0 && i < res[index-1])
                continue;
            if (require[i-1][0] <= counts[0] && require[i-1][1] <= counts[1] && 
                require[i-1][2] <= counts[2] && require[i-1][3] <= counts[3]) {
                counts[0] -= require[i-1][0];
                counts[1] -= require[i-1][1];
                counts[2] -= require[i-1][2];
                counts[3] -= require[i-1][3];
                res[index] = i;
                int ret = helper(counts, a / i, index + 1, res);
                val = val < ret ? val : ret;
                counts[0] += require[i-1][0];
                counts[1] += require[i-1][1];
                counts[2] += require[i-1][2];
                counts[3] += require[i-1][3];
            }
        }
        return val;
    }
    
    public int divide(int[] counts, int a, int prime) {
        while (a % prime == 0) {
            counts[(prime-1)/2]++;
            a /= prime;
        }
        return a;
    }
}


class Solution {
    int[][] require = {{0, 0, 0, 0}, {1, 0, 0, 0}, {0, 1, 0, 0}, {2, 0, 0, 0}, 
                       {0, 0, 1, 0}, {1, 1, 0, 0}, {0, 0, 0, 1}, {3, 0, 0, 0}, {0, 2, 0, 0}};
    public int smallestFactorization(int a) {
        int[] counts = new int[4];
        int origin = a;
        a = divide(counts, a, 2);
        a = divide(counts, a, 3);
        a = divide(counts, a, 5);
        a = divide(counts, a, 7);
        if (a != 1)
            return 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 2; i--) {
            while (counts[0] >= require[i-1][0] && counts[1] >= require[i-1][1] && 
                   counts[2] >= require[i-1][2] && counts[3] >= require[i-1][3]) {
                counts[0] -= require[i-1][0];
                counts[1] -= require[i-1][1];
                counts[2] -= require[i-1][2];
                counts[3] -= require[i-1][3];
                sb.append(i);
            }
        }
        sb.reverse();
        if (sb.length() == 0) {
            return 1;
        } else if (sb.length() > 10) {
            return 0;
        } else if (sb.length() == 10) {
            if (Integer.valueOf(sb.toString().substring(0, 9)) > Integer.MAX_VALUE/10) {
                return 0;
            } else if (Integer.valueOf(sb.toString().substring(0, 9)) == Integer.MAX_VALUE/10 && 
                        sb.charAt(9) > '0'+Integer.MAX_VALUE%10)
                return 0;
            else
                return Integer.valueOf(sb.toString());
        } else {
            return Integer.valueOf(sb.toString());
        }
        
    }
    
    public int divide(int[] counts, int a, int prime) {
        while (a % prime == 0) {
            counts[(prime-1)/2]++;
            a /= prime;
        }
        return a;
    }
}


class Solution {
    int[][] require = {{0, 0, 0, 0}, {1, 0, 0, 0}, {0, 1, 0, 0}, {2, 0, 0, 0}, 
                       {0, 0, 1, 0}, {1, 1, 0, 0}, {0, 0, 0, 1}, {3, 0, 0, 0}, {0, 2, 0, 0}};
    public int smallestFactorization(int a) {
        int[] counts = new int[4];
        int origin = a;
        a = divide(counts, a, 2);
        a = divide(counts, a, 3);
        a = divide(counts, a, 5);
        a = divide(counts, a, 7);
        if (a != 1)
            return 0;
        int res = 0, base = 1;
        for (int i = 9; i >= 2; i--) {
            while (counts[0] >= require[i-1][0] && counts[1] >= require[i-1][1] && 
                   counts[2] >= require[i-1][2] && counts[3] >= require[i-1][3]) {
                counts[0] -= require[i-1][0];
                counts[1] -= require[i-1][1];
                counts[2] -= require[i-1][2];
                counts[3] -= require[i-1][3];
                if ((res+base*i)/base == i && (res+base*i)%base == res)
                    res += base * i;
                else
                    return 0;
                base *= 10;
            }
        }
        return res == 0 ? 1 : res;
    }
    
    public int divide(int[] counts, int a, int prime) {
        while (a % prime == 0) {
            counts[(prime-1)/2]++;
            a /= prime;
        }
        return a;
    }
}


class Solution {
    public int smallestFactorization(int a) {
        if (a == 1)
            return 1;
        int res = 0, base = 1;
        for (int i = 9; i >= 2; i--) {
            while (a % i == 0) {
                a /= i;
                if ((res+base*i)/base == i)
                    res += base * i;
                else
                    return 0;
                base *= 10;
            }
            if (a == 1)
                break;
        }
        return a == 1 ? res : 0;
    }
}

class Solution {
    public String nearestPalindromic(String n) {
        long num = Long.parseLong(n);
        int len = n.length();
        int half = Integer.parseInt(n.substring(0, (len+1)/2));
        int floor = 1, ceiling = 1;
        for (int i = 0; i < (len-1)/2; i++) {
            floor *= 10;
            ceiling *= 10;
        }
        ceiling *= 10;
        long palin = helper(half, len), prev = 0, next = 0;
        if (palin < num) {
            prev = palin;
        } else {
            if (half != floor) {
                prev = helper(half - 1, len);
            } else {
                if (len % 2 == 0)
                    prev = helper(ceiling - 1, len - 1);
                else
                    prev = helper(floor - 1, len - 1);
            }
        }
        if (palin > num) {
            next = palin;
        } else {
            if (half + 1 != ceiling) {
                next = helper(half + 1, len);
            } else {
                if (len % 2 == 0) 
                    next = helper(ceiling, len + 1);
                else
                    next = helper(ceiling / 10, len + 1);
            }
        }
        return num - prev <= next - num ? prev + "" : next + "";
    }
    
    public long helper(int half, int len) {
        long res = half, temp = len % 2 == 0 ? half : half / 10;
        while (temp != 0) {
            res = res * 10 + temp % 10;
            temp /= 10;
        }
        return res;
    }
}

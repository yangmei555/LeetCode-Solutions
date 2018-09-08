// base 10 number convert to base 9 number 
class Solution {
    public int newInteger(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            sb.append(n % 9);
            n /= 9;
        }
        return Integer.valueOf(sb.reverse().toString());
    }
}


class Solution {
    public int newInteger(int n) {
        int res = 0, base = 1;
        while (n != 0) {
            res += n % 9 * base;
            base *= 10;
            n /= 9;
        }
        return res;
    }
}

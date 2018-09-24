// seems so easy but tried lots of times before accepted 
class Solution {
    public int countDigitOne(int n) {
        if (n <= 0)
            return 0;
        int res = 0, a = n, base = 1;
        while (a != 0) {
            if (a % 10 == 1)
                res += a / 10 * base + n % base + 1;
            else if (a % 10 == 0)
                res += a / 10 * base;
            else
                res += (a / 10 + 1) * base;
            base *= 10;
            a /= 10;
        }
        return res;
    }
}

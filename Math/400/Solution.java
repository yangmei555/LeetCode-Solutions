class Solution {
    public int findNthDigit(int n) {
        long base = 1, count = 1;
        while (n > 9 * base * count) {
            n -= 9 * base * count;
            base *= 10;
            count++;
        }
        n--;
        long x = n / count + base;
        long remain = n % count;
        long res = 0;
        for (int i = 0; i < remain; i++) {
            x = x - x / base * base;
            base /= 10;
        }
        return (int)(x / base);
    }
}

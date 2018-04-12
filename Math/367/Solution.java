class Solution {
    public boolean isPerfectSquare(int num) {
        int a = 1, b = num;
        while (a < b) {
            int x = (a + b) / 2;
            if (x == num / x && x * x == num)
                return true;
            else if (x > num / x) {
                b = x - 1;
            } else {
                a = x + 1;
            }
        }
        return num == a*a || num == b*b;
    }
}

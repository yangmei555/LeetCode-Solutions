class Solution {
    public boolean judgeSquareSum(int c) {
        int a = 0, b = (int)Math.sqrt(c);
        while (a <= b) {
            if (a * a + b * b == c)
                return true;
            else if (a * a + b * b < c)
                a++;
            else
                b--;
        }
        return false;
    }
}

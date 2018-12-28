// two pointers !!! 
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


class Solution {
    public boolean judgeSquareSum(int c) {
        for (int i = 0; i * i <= c / 2; i++) {
            int num = c - i * i;
            int left = 1, right = num + 1;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (mid > num / mid)
                    right = mid;
                else
                    left = mid + 1;
            }
            if ((left-1) * (left-1) == num)
                return true;
        }
        return false;
    }
}

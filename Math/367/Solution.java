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


class Solution {
    public boolean isPerfectSquare(int num) {
        if (num < 0)
            return false;
        int left = 1, right = num;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid > num / mid)
                right = mid;
            else
                left = mid + 1;
        }
        if (left > num / left)
            return (left-1) * (left-1) == num;
        else
            return left * left == num;
    }
}

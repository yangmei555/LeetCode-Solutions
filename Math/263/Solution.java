class Solution {
    public boolean isUgly(int num) {
        if (num == 0)
            return false;
        while (num % 2 == 0)
            num /= 2;
        while (num % 3 == 0)
            num /= 3;
        while (num % 5 == 0)
            num /= 5;
        return num == 1;
    }
}


class Solution {
    public boolean isUgly(int num) {
        if (num == 0)
            return false;
        while (num != 1) {
            if (num % 2 == 0)
                num /= 2;
            else if (num % 3 == 0)
                num /= 3;
            else if (num % 5 == 0)
                num /= 5;
            else
                return false;
        }
        return true;
    }
}


class Solution {
    public boolean isUgly(int num) {
        if (num == 0)
            return false;
        int x = 2;
        while (num != 1) {
            if (x > 5)
                return false;
            while (num % x == 0)
                num /= x;
            x++;
        }
        return true;
    }
}

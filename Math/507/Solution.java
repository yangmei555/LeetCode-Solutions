class Solution {
    public boolean checkPerfectNumber(int num) {
        if (num == 0)
            return false;
        int n = 1, sum = 0;
        for (; n * n < num; n++) {
            if (num % n == 0)
                sum += n + num / n;
        }
        if (n * n == num)
            sum += n;
        return sum == num * 2;
    }
}

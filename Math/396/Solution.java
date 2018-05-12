class Solution {
    public int maxRotateFunction(int[] A) {
        if (A.length < 2)
            return 0;
        int f0 = 0, sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            f0 += i * A[i];
        }
        int res = f0;
        for (int i = 1; i < A.length; i++) {
            f0 += sum - A.length * A[A.length-i];
            res = res > f0 ? res : f0;
        }
        return res;
    }
}

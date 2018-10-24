class Solution {
    public int bestRotation(int[] A) {
        int[] diff = new int[A.length], mark = new int[A.length+1];
        for (int i = 0; i < A.length; i++)
            diff[i] = A[i] - i;
        for (int i = 0; i < diff.length; i++) {
            if (diff[i] <= 0) {
                mark[0]++;
                mark[Math.min(i, -diff[i])+1]--;
            }
            if (i <= A.length-2) {
                diff[i] += i - A.length + 1;
                if (diff[i] <= 0) {
                    mark[i+1]++;
                    mark[Math.min(i+1-diff[i], A.length-1)+1]--;
                }
            }
        }
        int res = -1, max = 0, sum = 0;
        for (int i = 0; i < mark.length; i++) {
            sum += mark[i];
            if (res == -1 || sum > max) {
                res = i;
                max = sum;
            }
        }
        return res;
    }
}

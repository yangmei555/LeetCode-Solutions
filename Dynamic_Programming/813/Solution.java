class Solution {
    public double largestSumOfAverages(int[] A, int K) {
        Double[][] memo = new Double[A.length][K+1];
        return helper(A, K, 0, memo);
    }
    
    public double helper(int[] A, int K, int index, Double[][] memo) {
        if (index == A.length)
            return 0;
        if (memo[index][K] != null)
            return memo[index][K];
        if (K == 1) {
            int sum = 0; 
            for (int i = index; i < A.length; i++) 
                sum += A[i];
            memo[index][K] = sum / (A.length - index + .0);
            return memo[index][K];
        }
        int sum = 0;
        double res = 0;
        for (int i = index; i < A.length; i++) {
            sum += A[i];
            double ret = helper(A, K-1, i+1, memo);
            ret += sum / (i - index + 1.0);
            res = res > ret ? res : ret;
        }
        memo[index][K] = res;
        return memo[index][K];
    }
}


class Solution {
    public double largestSumOfAverages(int[] A, int K) {
        double[][] index = new double[A.length][K+1];
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            index[i][1] = sum / (i + 1.0);
        }
        for (int i = 0; i < A.length; i++) {
            for (int k = 2; k <= K && k <= i+1; k++) {
                if (k == i + 1) {
                    index[i][k] = index[i][1] * (i+1.0);
                }
                int local = 0;
                for (int j = i; j >= k-1 && j > 0; j--) {
                    local += A[j];
                    index[i][k] = Math.max(index[i][k], local/(i-j+1.0) + index[j-1][k-1]);
                }
            }
        }
        return index[A.length-1][K];
    }
}

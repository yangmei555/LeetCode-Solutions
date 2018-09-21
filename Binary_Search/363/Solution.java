class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int[][] prefix = helper(matrix);
        int row = matrix.length, col = matrix[0].length;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < col; i++) {
            for (int j = i; j < col; j++) {
                int sum = 0;
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                for (int r = 0; r < row; r++) {
                    sum += prefix[r][j+1] - prefix[r][i];
                    Integer ceil = set.ceiling(sum - k);
                    if (ceil != null)
                        res = Math.max(res, sum - ceil);
                    set.add(sum);
                }
            }
        }
        return res;
    }
    
    public int[][] helper(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        int[][] prefix = new int[row][col+1];
        for (int i = 0; i < row; i++) {
            for (int j = 1; j <= col; j++)
                prefix[i][j] = prefix[i][j-1] + matrix[i][j-1];
        }
        return prefix;
    }
}


// if max <= k, use max. otherwise, use the naive way to find the largest below k 
// this solution runs much faster than the above(on OJ), even with worse asymptotic time 
// also, the space complexity can be reduced to O(row) 
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < col; i++) {
            int[] rowSum = new int[row];
            for (int j = i; j < col; j++) {
                int sum = 0, max = Integer.MIN_VALUE;
                for (int r = 0; r < row; r++) {
                    rowSum[r] += matrix[r][j];
                    if (sum < 0)
                        sum = 0;
                    sum += rowSum[r];
                    max = max > sum ? max : sum;
                }
                if (max <= k) {
                    res = res > max ? res : max;
                } else {
                    max = Integer.MIN_VALUE;
                    for (int m = 0; m < row; m++) {
                        sum = 0;
                        for (int n = m; n < row; n++) {
                            sum += rowSum[n];
                            if (sum <= k)
                                max = max > sum ? max : sum;
                        }
                    }
                    res = res > max ? res : max;
                }
                if (res == k)
                    return k;
            }
        }
        return res;
    }
}

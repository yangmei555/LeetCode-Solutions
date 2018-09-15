class Solution {
    public int findKthNumber(int m, int n, int k) {
        int left = 1, right = m * n;
        while (left < right) {
            int mid = (left + right) / 2;
            int row = 1, col = n, count = 0;
            while (row <= m && col >= 1) {
                if (row * col <= mid) {
                    count += col;
                    row++;
                } else {
                    col--;
                }
            }
            if (count < k)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}


// another way of traversing the table 
class Solution {
    public int findKthNumber(int m, int n, int k) {
        int left = 1, right = m * n;
        while (left < right) {
            int mid = (left + right) / 2;
            int row = m, col = 1, count = 0;
            while (row >= 1 && col <= n) {
                if (row * col <= mid) {
                    count += row;
                    col++;
                } else {
                    row--;
                }
            }
            if (count < k)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}

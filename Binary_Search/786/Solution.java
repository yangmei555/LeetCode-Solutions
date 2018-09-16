// notice that epsilon has to be set to smaller than or equal to 1e-7 
class Solution {
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        double left = 1.0 / A[A.length-1], right = 1.0;
        while (Math.abs(left - right) >= 1e-7) {
            double mid = (left + right) / 2;
            // System.out.println(left + "  " + mid + "  " + right);
            int row = 0, col = 1;
            int count = 0;
            while (row <= A.length-2 && col <= A.length-1) {
                if ((A[row]+.0)/A[col] <= mid) {
                    count += A.length - col;
                    row++;
                } else {
                    col++;
                }
            }
            if (count < K) {
                left = mid;
            } else {
                right = mid;
            }
        }
        // System.out.println(left);
        int row = 0, col = 1;
        while (row <= A.length-2 && col <= A.length-1) {
            if (Math.abs((A[row]+.0)/A[col] - left) <= 1e-7)
                return new int[]{A[row], A[col]};
            else if ((A[row]+.0)/A[col] < left)
                row++;
            else
                col++;
        }
        return null;
    }
}


// another way of traversing the matrix 
// but the epsilon has to be lowered to 1e-8, don't know why , odd 
class Solution {
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        double left = 1.0 / A[A.length-1], right = 1.0;
        while (Math.abs(left - right) >= 1e-8) {
            double mid = (left + right) / 2;
            // System.out.println(left + "  " + mid + "  " + right);
            int row = A.length-2, col = A.length-1;
            int count = 0;
            while (row >= 0 && col >= 1) {
                if ((A[row]+.0)/A[col] <= mid) {
                    count += row + 1;
                    col--;
                } else {
                    row--;
                }
            }
            if (count < K) {
                left = mid;
            } else {
                right = mid;
            }
        }
        // System.out.println(left);
        int row = A.length-2, col = A.length-1;
        while (row >= 0 && col >= 1) {
            if (Math.abs((A[row]+.0)/A[col] - left) <= 1e-8)
                return new int[]{A[row], A[col]};
            else if ((A[row]+.0)/A[col] < left)
                col--;
            else
                row--;
        }
        return null;
    }
}

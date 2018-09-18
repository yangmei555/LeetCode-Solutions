// priority queue step by step method , quite slow 
class Solution {
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                // double d1 = (A[i1[0]] + .0) / A[i1[1]];
                // double d2 = (A[i2[0]] + .0) / A[i2[1]];
                // if (d1 < d2)
                //     return -1;
                // else if (d1 == d2)
                //     return 0;
                // else
                //     return 1;
                return A[i1[0]] * A[i2[1]] - A[i1[1]] * A[i2[0]];
            }
        });
        for (int j = A.length-1; j > 0 && A.length-j <= K; j--)
            queue.offer(new int[]{0, j});
        int count = 0;
        while (count++ < K-1) {
            int[] node = queue.poll();
            if (node[0]+1 < node[1]) {
                node[0]++;
                queue.offer(node);
            }
        }
        return new int[]{A[queue.peek()[0]], A[queue.peek()[1]]};
    }
}


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


// complement the matrix, it is still two dimensional sorted 
class Solution {
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        double left = 1.0 / A[A.length-1], right = 1.0;
        while (Math.abs(left - right) >= 1e-7) {
            double mid = (left + right) / 2;
            // System.out.println(left + "  " + mid + "  " + right);
            int row = 0, col = 0;
            int count = 0;
            while (row < A.length && col < A.length) {
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
        int row = 0, col = 0;
        while (row < A.length && col < A.length) {
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


// start picking indices 0 and 1, and move forward step by step 
// notice that if initially choose 0 and A.length-1, there will be two directions to 
// increase the value, so do not choose 0 and A.length-1 
// also notice that there are no duplicates in the possible pairs 
class Solution {
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        int x = 0, y = 1;
        while (true) {
            int count = 0;
            for (int i = 0, j = 0; j < A.length; j++) {
                while (i < j && A[i] * A[y] <= A[j] * A[x])
                    i++;
                count += i;
            }
            if (count < K) {
                x++;
                if (x == y)
                    y++;
            }
            else if (count > K)
                y++;
            else
                break;
        }
        return new int[]{A[x], A[y]};
    }
}


// use sliding window to count 
// p/q is the largest possible value smaller than or equal to mid 
// there are no duplicate values, so once count == K we can return {p, q} 
class Solution {
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        double left = (A[0]+.0) / A[A.length-1], right = 1;
        while (Math.abs(left-right) >= 1e-9) {
            double mid = (left + right) / 2;
            int p = 0, q = 1;
            int count = 0;
            for (int i = 0, j = 0; j < A.length; j++) {
                while (A[i] <= A[j] * mid)
                    i++;
                if (i >= 1 && A[i-1] * q > A[j] * p) {
                    p = A[i-1];
                    q = A[j];
                } 
                count += i;
            }
            if (count < K)
                left = mid;
            else if (count > K)
                right = mid;
            else 
                return new int[]{p, q};
        }
        return null;
    }
}

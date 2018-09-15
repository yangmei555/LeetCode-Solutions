class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int[] arr = new int[matrix.length * matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++)
                arr[i * matrix[0].length + j] = matrix[i][j];
        }
        Arrays.sort(arr);
        return arr[k-1];
    }
}


class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return matrix[i1[0]][i1[1]] - matrix[i2[0]][i2[1]];
            }
        });
        for (int i = 0; i < matrix.length; i++)
            queue.offer(new int[]{i, 0});
        int count = 0;
        while (++count < k) {
            int[] node = queue.poll();
            node[1]++;
            if (node[1] < matrix[0].length)
                queue.offer(node);
        }
        int[] indices = queue.poll();
        return matrix[indices[0]][indices[1]];
    }
}


// O(n * log(max-min)). 
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int left = matrix[0][0], right = matrix[matrix.length-1][matrix[0].length-1];
        while (left < right) {
            int mid = (left + right) / 2;
            int count = 0;
            int row = 0, col = matrix[0].length-1;
            // notice that this process takes O(n), because row and col sweep only once 
            while (row < matrix.length) {
                while (col >= 0 && matrix[row][col] > mid)
                    col--;
                count += col + 1;
                row++;
            }
            if (count < k)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}


// another way of walking through the matrix 
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int left = matrix[0][0], right = matrix[matrix.length-1][matrix[0].length-1];
        while (left < right) {
            int mid = (left + right) / 2;
            int count = 0;
            int row = matrix.length-1, col = 0;
            while (row >= 0 && col < matrix[0].length) {
                if (matrix[row][col] <= mid) {
                    count += row + 1;
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

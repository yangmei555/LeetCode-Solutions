class Solution {
    
    Map<Integer, Integer> map;
    int size, row, col;
    Random random;
    public Solution(int n_rows, int n_cols) {
        row = n_rows;
        col = n_cols;
        size = row * col;
        map = new HashMap<>();
        random = new Random();
    }
    
    public int[] flip() {
        int index = random.nextInt(size);
        int res = map.getOrDefault(index, index);
        if (index != size-1) 
            map.put(index, map.getOrDefault(size-1, size-1));
        size--;
        return new int[]{res / col, res % col};
    }
    
    public void reset() {
        size = row * col;
        map = new HashMap<>();
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(n_rows, n_cols);
 * int[] param_1 = obj.flip();
 * obj.reset();
 */


// no need to clear the map when resetting 
class Solution {
    
    Map<Integer, Integer> map;
    int size, row, col;
    Random random;
    public Solution(int n_rows, int n_cols) {
        row = n_rows;
        col = n_cols;
        size = row * col;
        map = new HashMap<>();
        random = new Random();
    }
    
    public int[] flip() {
        int index = random.nextInt(size);
        int res = map.getOrDefault(index, index);
        if (index != size-1) {
            map.put(index, map.getOrDefault(size-1, size-1));
            map.put(size-1, res);
        }
        size--;
        return new int[]{res / col, res % col};
    }
    
    public void reset() {
        size = row * col;
        // map = new HashMap<>();
    }
}


// a binary search approach 
class Solution {
    
    List<Integer> black;
    int row, col, size;
    Random random;
    public Solution(int n_rows, int n_cols) {
        black = new LinkedList<>();
        row = n_rows;
        col = n_cols;
        size = row * col;
        random = new Random();
    }
    
    public int[] flip() {
        int index = random.nextInt(size--);
        int left = 0, right = black.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (black.get(mid) - mid >= index + 1)
                right = mid;
            else
                left = mid + 1;
        }
        int res = left + index;
        int insert = Collections.binarySearch(black, res);
        insert = - insert - 1;
        black.add(insert, res);
        return new int[]{res / col, res % col};
    }
    
    public void reset() {
        black = new LinkedList<>();
        size = row * col;
    }
}

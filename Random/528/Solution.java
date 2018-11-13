class Solution {
    
    Random random;
    int[] w;
    public Solution(int[] w) {
        random = new Random();
        this.w = w;
        for (int i = 1; i < w.length; i++)
            w[i] += w[i-1];
    }
    
    public int pickIndex() {
        int point = random.nextInt(w[w.length-1]) + 1;
        int left = 0, right = w.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (w[mid] >= point)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */

class Solution {

    Random random;
    int[] prefix;
    int[][] rects;
    public Solution(int[][] rects) {
        random = new Random();
        this.rects = rects;
        prefix = new int[rects.length];
        prefix[0] = (rects[0][2] - rects[0][0] + 1) * (rects[0][3] - rects[0][1] + 1);
        for (int i = 1; i < rects.length; i++)
            prefix[i] = prefix[i-1] + (rects[i][2] - rects[i][0] + 1) * 
                                        (rects[i][3] - rects[i][1] + 1);
        
    }
    
    public int[] pick() {
        int point = random.nextInt(prefix[prefix.length-1]) + 1;
        int left = 0, right = prefix.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (point <= prefix[mid])
                right = mid;
            else
                left = mid + 1;
        }
        point -= left == 0 ? 0 : prefix[left-1];
        point--;
        int deltaRow = point % (rects[left][2] - rects[left][0] + 1);
        int deltaCol = point / (rects[left][2] - rects[left][0] + 1);
        return new int[]{rects[left][0] + deltaRow, rects[left][1] + deltaCol};
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */

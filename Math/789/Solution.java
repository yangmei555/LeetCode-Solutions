class Solution {
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int dist = helper(0, 0, target[0], target[1]);
        for (int i = 0; i < ghosts.length; i++) {
            if (helper(ghosts[i][0], ghosts[i][1], target[0], target[1]) <= dist)
                return false;
        }
        return true;
    }
    
    public int helper(int x1, int y1, int x2, int y2) {
        return (x1>x2 ? x1-x2 : x2-x1) + (y1>y2 ? y1-y2 : y2-y1);
    }
}

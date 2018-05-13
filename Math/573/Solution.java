class Solution {
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int total = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < nuts.length; i++) {
            total += 2 * (getDist(tree[0], nuts[i][0]) + getDist(tree[1], nuts[i][1]));
            min = helper(nuts, squirrel, tree, min, i);
        }
        return total + min;
    }
    
    public int getDist(int a, int b) {
        return a > b ? a - b : b - a;
    }
    
    public int helper(int[][] nuts, int[] squirrel, int[] tree, int min, int i) {
        int dist1 = getDist(nuts[i][0], squirrel[0]);
        int dist2 = getDist(nuts[i][1], squirrel[1]);
        int dist3 = getDist(nuts[i][0], tree[0]);
        int dist4 = getDist(nuts[i][1], tree[1]);
        if (dist1+dist2-dist3-dist4 < min)
            return dist1+dist2-dist3-dist4;
        else
            return min;
    }
}

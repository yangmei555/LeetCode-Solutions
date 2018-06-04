class Solution {
    public int pathSum(int[] nums) {
        int[][] path = new int[5][8];
        int level = 1;
        for (int n : nums) {
            int hundred = n / 100, ten = n / 10 % 10, unit = n % 10;
            level = level > hundred ? level : hundred;
            path[hundred][ten-1] = path[hundred-1][(ten-1)/2] + unit;
        }
        int res = 0;
        for (int i = 1; i <= level; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == level) {
                    res += path[i][j];
                } else {
                    if (j > 3)
                        break;
                    if (path[i+1][j*2] == 0 && path[i+1][j*2+1] == 0) 
                        res += path[i][j];
                }
            }   
        }
        return res;
    }
}

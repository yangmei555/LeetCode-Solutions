class Solution {
    public String[] findRelativeRanks(int[] nums) {
        int[][] ranks = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            ranks[i][0] = i;
            ranks[i][1] = nums[i];
        }
        Arrays.sort(ranks, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i2[1] - i1[1];
            }
        });
        String[] res = new String[nums.length];
        String[] medals = new String[]{"Gold Medal", "Silver Medal", "Bronze Medal"};
        for (int i = 0; i < ranks.length; i++) {
            if (i < 3)
                res[ranks[i][0]] = medals[i];
            else
                res[ranks[i][0]] = i + 1 + "";
        }
        return res;
    }
}


class Solution {
    public String[] findRelativeRanks(int[] nums) {
        // just store the original index is enough 
        // no need to store additional information 
        Integer[] ranks = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) 
            ranks[i] = i;
        Arrays.sort(ranks, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return nums[i2] - nums[i1];
            }
        });
        String[] res = new String[nums.length];
        String[] medals = new String[]{"Gold Medal", "Silver Medal", "Bronze Medal"};
        for (int i = 0; i < ranks.length; i++) {
            if (i < 3)
                res[ranks[i]] = medals[i];
            else
                res[ranks[i]] = i + 1 + "";
        }
        return res;
    }
}

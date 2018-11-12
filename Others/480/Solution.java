class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        int[][] pairs = new int[2][];
        TreeSet<int[]> set = new TreeSet<>(new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                if (i1[0] == i2[0])
                    return i1[1] - i2[1];
                else
                    // cannot directly i1[0] - i2[0] here, 
                    // to avoid integer overflow (eg. Integer.MIN_VALUE - Integer.MAX_VALUE) 
                    return i1[0] < i2[0] ? -1 : 1;
            }
        });
        for (int i = 0; i < k; i++) 
            add(pairs, set, nums, i);
        double[] res = new double[nums.length-k+1];
        res[0] = pairs[1] == null ? pairs[0][0] : (.0 + pairs[0][0] + pairs[1][0]) / 2;
        for (int i = k; i < nums.length; i++) {
            add(pairs, set, nums, i);
            remove(pairs, set, nums, i-k);
            res[i-k+1] = pairs[1] == null ? pairs[0][0] : (.0 + pairs[0][0] + pairs[1][0]) / 2;
        }
        return res;
    }
    
    public void add(int[][] pairs, TreeSet<int[]> set, int[] nums, int i) {
        set.add(new int[]{nums[i], i});
        if (pairs[0] == null) {
            pairs[0] = new int[]{nums[i], i};
        } else if (pairs[1] == null) {
            if (nums[i] < pairs[0][0]) {
                pairs[1] = pairs[0];
                pairs[0] = set.lower(pairs[0]);
            } else {
                pairs[1] = set.higher(pairs[0]);
            }
        } else {
            if (nums[i] < pairs[0][0]) {

            } else if (nums[i] < pairs[1][0]) {
                pairs[0] = new int[]{nums[i], i};
            } else {
                pairs[0] = pairs[1];
            }
            pairs[1] = null;
        }
    }
    
    public void remove(int[][] pairs, TreeSet<int[]> set, int[] nums, int i) {
        if (pairs[1] == null) {
            if (nums[i] <= pairs[0][0]) {
                pairs[1] = set.higher(pairs[0]);
            } else {
                pairs[1] = pairs[0];
                pairs[0] = set.lower(pairs[0]);
            }
        } else {
            if (nums[i] <= pairs[1][0]) {
                pairs[0] = pairs[1];
                pairs[1] = null;
            } else {
                pairs[1] = null;
            }
        }
        set.remove(new int[]{nums[i], i});
        if (!set.contains(pairs[0]))
            pairs[0] = set.floor(pairs[0]);
    }
}

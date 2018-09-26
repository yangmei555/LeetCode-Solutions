class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        Integer[] indices = new Integer[profit.length];
        for (int i = 0; i < profit.length; i++)
            indices[i] = i;
        Arrays.sort(indices, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return profit[i1] - profit[i2];
            }
        });
        Arrays.sort(worker);
        int i = worker.length-1, j = indices.length-1;
        int res = 0;
        while (i >= 0 && j >= 0) {
            while (j >= 0 && difficulty[indices[j]] > worker[i])
                j--;
            if (j >= 0)
                res += profit[indices[j]];
            i--;
        }
        return res;
    }
}

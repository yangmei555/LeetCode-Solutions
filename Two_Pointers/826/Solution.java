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


// another approach which is symmetric to the above one 
// sort the difficulty ascendingly 
class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        Integer[] indices = new Integer[profit.length];
        for (int i = 0; i < profit.length; i++)
            indices[i] = i;
        Arrays.sort(indices, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return difficulty[i1] - difficulty[i2];
            }
        });
        Arrays.sort(worker);
        int i = 0, j = 0, max = 0, res = 0;
        while (i < worker.length) {
            while (j < indices.length && difficulty[indices[j]] <= worker[i])
                max = Math.max(max, profit[indices[j++]]);
            res += max;
            i++;
        }
        return res;
    }
}

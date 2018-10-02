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


// somewhat like counting sort and dp mixture 
class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int max = 0;
        for (int d : difficulty)
            max = Math.max(max, d);
        int[] record = new int[max + 1];
        // notice that there may be duplicate difficutly values 
        for (int i = 0; i < difficulty.length; i++)
            record[difficulty[i]] = Math.max(profit[i], record[difficulty[i]]);
        for (int i = 1; i < record.length; i++)
            record[i] = Math.max(record[i], record[i-1]);
        int res = 0;
        for (int w : worker) {
            if (w > max)
                res += record[max];
            else
                res += record[w];
        }
        return res;
    }
}

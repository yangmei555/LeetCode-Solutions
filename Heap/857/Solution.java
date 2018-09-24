class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        Integer[] indices = new Integer[quality.length];
        for (int i = 0; i < indices.length; i++)
            indices[i] = i;
        Arrays.sort(indices, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return wage[i1] * quality[i2] - wage[i2] * quality[i1];
            }
        });
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        });
        double res = Double.MAX_VALUE;
        double sum = 0;
        for (int i : indices) {
            sum += quality[i];
            queue.offer(quality[i]);
            if (queue.size() > K)
                sum -= queue.poll();
            if (queue.size() == K)
                res = Math.min(res, sum * wage[i] / quality[i]);
        } 
        return res;
    }
}

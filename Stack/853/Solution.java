class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        Integer[] indices = new Integer[position.length];
        for (int i = 0; i < indices.length; i++) 
            indices[i] = i;
        Arrays.sort(indices, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return position[i2] - position[i1];
            }
        });
        double[] times = new double[position.length];
        for (int i = 0; i < times.length; i++) 
            times[i] = (target + .0 - position[i]) / speed[i];
        int res = 0, index = 0;
        while (index < indices.length) {
            res++;
            int temp = index++;
            while (index < indices.length && times[indices[index]] <= times[indices[temp]])
                index++;
        }
        return res;
    }
}

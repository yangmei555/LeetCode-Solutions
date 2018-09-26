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


// traverse by tree map 
class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        TreeMap<Integer, Double> map = new TreeMap<>();
        for (int i = 0; i < position.length; i++) 
            map.put(position[i], (target - position[i] + .0) / speed[i]);
        int res = 0;
        double max = 0;
        for (int k : map.descendingKeySet()) {
            if (map.get(k) > max) {
                max = map.get(k);
                res++;
            }
        }
        return res;
    }
}


// counting sort in O(n + target)  
class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        double[] times = new double[target];
        for (int i = 0; i < position.length; i++) 
            times[position[i]] = (target - position[i] + .0) / speed[i];
        int res = 0;
        double max = 0;
        for (int i = times.length-1; i >= 0; i--) {
            if (times[i] > max) {
                max = times[i];
                res++;
            }
        }
        return res;
    }
}

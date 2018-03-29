class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int max = 0, min = 1;
        int res = 0;
        for (List<Integer> array : arrays) {
            if (max < min) {
                max = array.get(array.size() - 1);
                min = array.get(0);
            } else {
                int m = array.get(array.size() - 1);
                int n = array.get(0);
                int k1 = Math.abs(m - max);
                int k2 = Math.abs(m - min);
                int k3 = Math.abs(n - max);
                int k4 = Math.abs(n - min);
                res = (res > k1 ? res : k1);
                res = (res > k2 ? res : k2);
                res = (res > k3 ? res : k3);
                res = (res > k4 ? res : k4);
                max = max > m ? max : m;
                min = min < n ? min : n;
            }
        }
        return res;
    }
}

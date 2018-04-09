class Solution {
    public int numberOfBoomerangs(int[][] points) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = points.length;
        int len = 0, res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                len = (points[i][0] - points[j][0]) * (points[i][0] - points[j][0]) + 
                    (points[i][1] - points[j][1]) * (points[i][1] - points[j][1]);
                map.put(len, map.getOrDefault(len, 0) + 1);
            }
            for (int k : map.values()) {
                res += k * (k - 1);
            }
            map.clear();
        }
        return res;
    }
}


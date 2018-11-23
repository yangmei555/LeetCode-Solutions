class Solution {
    public int minAreaRect(int[][] points) {
        Map<Integer, Set<Integer>> vertical = new HashMap<>();
        for (int[] p : points) {
            vertical.putIfAbsent(p[0], new HashSet<>());
            vertical.get(p[0]).add(p[1]);
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                if (points[i][0] != points[j][0] && points[i][1] != points[j][1]) {
                    if (vertical.get(points[i][0]).contains(points[j][1]) && 
                        vertical.get(points[j][0]).contains(points[i][1])) {
                        res = Math.min(res, Math.abs(points[i][0] - points[j][0]) * 
                                            Math.abs(points[i][1] - points[j][1]));
                    }
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}

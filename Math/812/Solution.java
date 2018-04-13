class Solution {
    public double largestTriangleArea(int[][] points) {
        int len = points.length, x1 = 0, x2 = 0, y1 = 0, y2 = 0;
        double area = 0, max = 0;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; k < len; k++) {
                    x1 = points[j][0] - points[i][0];
                    x2 = points[k][0] - points[i][0];
                    y1 = points[j][1] - points[i][1];
                    y2 = points[k][1] - points[i][1];
                    area = (x1 * y2 - x2 * y1 + .0) / 2;
                    area = area > 0 ? area : -area;
                    if (area > max)
                        max = area;
                }
            }
        }
        return max;
    }
}

class Solution {
    double r, xc, yc;
    public Solution(double radius, double x_center, double y_center) {
        r = radius;
        xc = x_center;
        yc = y_center;
    }
    
    public double[] randPoint() {
        double rad = Math.random() * Math.PI * 2;
        // r^2 is uniformly distributed over [0, 1], since pi*r^2 is uniformly distributed 
        // over [0, pi] 
        double len = Math.sqrt(Math.random()) * r;
        return new double[]{xc + len * Math.cos(rad), yc + len * Math.sin(rad)};
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(radius, x_center, y_center);
 * double[] param_1 = obj.randPoint();
 */


class Solution {
    double r, xc, yc;
    public Solution(double radius, double x_center, double y_center) {
        r = radius;
        xc = x_center;
        yc = y_center;
    }
    
    public double[] randPoint() {
        double a = -1 + Math.random() * 2, b = -1 + Math.random() * 2;
        while (a * a + b * b > 1) {
            a = -1 + Math.random() * 2;
            b = -1 + Math.random() * 2;
        }
        return new double[]{xc + r * a, yc + r * b};
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(radius, x_center, y_center);
 * double[] param_1 = obj.randPoint();
 */

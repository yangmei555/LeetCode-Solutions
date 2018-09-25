class Solution {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        int a = tx, b = ty;
        while (a > sx && b > sy) {
            if (a > b)
                a = a - b;
            else
                b = b - a;
        }
        if (a < sx || b < sy)
            return false;
        if (a == sx)
            return (b - sy) % a == 0;
        else
            return (a - sx) % b == 0;
    }
}

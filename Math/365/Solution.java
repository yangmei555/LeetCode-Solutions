class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
        if (z > x + y)
            return false;
        if (x > y) {
            int temp = x;
            x = y;
            y = temp;
        }
        int orx = x, ory = y;
        if (z == x || z == y || z == x + y)
            return true;
        if (x == 0 || y == 0)
            return false;
        while (y != 0) {
            while (y >= x) {
                if (z == y - x)
                    return true;
                y -= x;
            }
            if (y == 0)
                break;
            if (z == y + ory)
                return true;
            y = ory - (orx - y);
            if (z == y)
                return true;
            x = orx;
        }
        return false;
    }
}


class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
        if (z > x + y)
            return false;
        if (z == x || z == y || z == x + y)
            return true;
        if (x == 0 || y == 0)
            return false;
        while (x % y != 0) {
            int temp = x % y;
            x = y;
            y = temp;
        }
        return z % y == 0;
    }
}

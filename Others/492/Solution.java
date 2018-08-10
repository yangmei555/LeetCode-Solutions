class Solution {
    public int[] constructRectangle(int area) {
        int W = 0;
        for (W = (int)Math.sqrt(area); W >= 1; W--) {
            if (area % W == 0)
                break;
        }
        return new int[]{area / W, W};
    }
}


class Solution {
    public int[] constructRectangle(int area) {
        int W = 1;
        for (int i = 1; i * i <= area; i++) {
            if (area % i == 0)
                W = i;
        }
        return new int[]{area / W, W};
    }
}

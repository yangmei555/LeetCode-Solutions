class Solution {
    public int minEatingSpeed(int[] piles, int H) {
        int left = 1, right = 1_000_000_000;
        // for (int p : piles)
        //     right = right > p ? right : p;
        while (left < right) {
            int mid = (left + right) / 2;
            int h = 0;
            for (int p : piles)
                h += (p - 1) / mid + 1;
            if (h <= H)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
}

class Solution {
    public int candy(int[] ratings) {
        int res = 1, cons = 1, cur = 1, up = 0, down = 0;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i-1] > ratings[i]) {
                down++;
            } else if (ratings[i-1] < ratings[i]) {
                if (down != 0) {
                    int large = up > down ? up : down, small = up > down ? down : up;
                    large++;
                    res += large*(large+1)/2 + small*(small+1)/2 - 1;
                    up = 0;
                    down = 0;
                }
                up++;
            } else if (ratings[i-1] == ratings[i]) {
                if (up != 0 || down != 0) {
                    int large = up > down ? up : down, small = up > down ? down : up;
                    large++;
                    res += large*(large+1)/2 + small*(small+1)/2 - 1;
                    up = 0;
                    down = 0;
                }
                res++;
            }
        }
        int large = up > down ? up : down, small = up > down ? down : up;
        large++;
        res += large*(large+1)/2 + small*(small+1)/2 - 1;
        return res;
    }
}

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


class Solution {
    public int candy(int[] ratings) {
        int[] record = new int[ratings.length];
        record[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i-1] < ratings[i])
                record[i] = record[i-1] + 1;
            else
                record[i] = 1;
        }
        for (int i = ratings.length-2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1])
                record[i] = Math.max(record[i], record[i+1] + 1);
        }
        int res = 0;
        for (int r : record)
            res += r;
        return res;
    }
}


// same idea with the first solution but more concise 
class Solution {
    public int candy(int[] ratings) {
        int res = 1, up = 0, down = 0, index = 1;
        while (index < ratings.length) {
            up = 1;
            while (index < ratings.length && ratings[index-1] < ratings[index]) {
                index++;
                up++;
            }
            down = 1;
            while (index < ratings.length && ratings[index-1] > ratings[index]) {
                index++;
                down++;
            }
            int max = up > down ? up : down, min = up > down ? down-1 : up-1;
            res += max*(max+1)/2 + min*(min+1)/2 - 1;
            if (index < ratings.length && ratings[index-1] == ratings[index]) {
                index++;
                res++;
            }
        }
        return res;
    }
}


// same idea with the first solution but more concise 
class Solution {
    public int candy(int[] ratings) {
        int res = 1, peak = 1, up = 1, down = 0;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i-1] < ratings[i]) {
                up++;
                
                // peak = i-2 >= 0 && ratings[i-2] < ratings[i-1] ? peak + 1 : 2;
                peak = up;

                res += up;
                down = 0;
            } else if (ratings[i-1] == ratings[i]) {
                up = 1;
                down = 0;
                peak = 1;
                res++;
            } else {
                down++;
                res += down < peak ? down : down + 1;
                up = 1;
            }
        }
        return res;
    }
}

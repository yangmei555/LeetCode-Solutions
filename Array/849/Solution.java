class Solution {
    public int maxDistToClosest(int[] seats) {
        int pos = -1, res = 0;
        for (int i = 0; i <= seats.length; i++) {
            if (i == seats.length || seats[i] == 1) {
                if (i == seats.length || pos == -1) 
                    res = res > i-1-pos ? res : i-1-pos;
                else 
                    res = res > (i-pos)/2 ? res : (i-pos)/2;
                pos = i;
            }
        }
        return res;
    }
}

class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int res = 0, endpoint = 0;
        for (int t : timeSeries) {
            if (endpoint <= t) {
                res += duration;
            } else {
                res += t + duration - endpoint;
            }
            endpoint = t + duration;
        }
        return res;
    }
}


class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int res = 0;
        int start = 0, end = 0;
        for (int t : timeSeries) {
            start = Math.max(start, t);
            end = t + duration;
            res += end - start;
            start = end;
        }
        return res;
    }
}

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0, min = Integer.MAX_VALUE, res = -1;
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
            if (sum < min) {
                min = sum;
                res = i;
            }
        }
        return sum < 0 ? -1 : (res + 1) % gas.length;
    }
}


class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int diff = 0, total = 0, res = 0;
        for (int i = 0; i < gas.length; i++) {
            diff += gas[i] - cost[i];
            if (diff < 0) {
                total += diff;
                diff = 0;
                res = i + 1;
            }
        }
        if (diff > 0)
            total += diff;
        return total < 0 ? -1 : res;
    }
}

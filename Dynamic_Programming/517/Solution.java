class Solution {
    public int findMinMoves(int[] machines) {
        int sum = 0;
        for (int m : machines)
            sum += m;
        if (sum % machines.length != 0)
            return -1;
        int target = sum / machines.length, res = 0, diff = 0;
        for (int m : machines) {
            diff += m - target;
            res = Math.max(Math.max(res, Math.abs(diff)), m - target);
        }
        return res;
    }
}


class Solution {
    public int findMinMoves(int[] machines) {
        int sum = 0;
        for (int m : machines)
            sum += m;
        if (sum % machines.length != 0)
            return -1;
        int target = sum / machines.length, res = 0, addup = 0;
        for (int i = 0; i < machines.length; i++) {
            int left = addup - target * i;
            int right = sum - machines[i] - addup - target * (machines.length - i - 1);
            if (left > 0 && right > 0)
                res = Math.max(res, Math.max(left, right));
            else if (left < 0 && right < 0)
                res = Math.max(res, -left - right);
            else
                res = Math.max(res, Math.max(Math.abs(left), Math.abs(right)));
            addup += machines[i];
        }
        return res;
    }
}

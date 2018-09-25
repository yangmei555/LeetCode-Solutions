class Solution {
    public int reachNumber(int target) {
        target = target > 0 ? target : -target;
        long left = 0, right = target;
        while (left < right) {
            long mid = (left+right)/2;
            if (mid*(mid+1)/2 <= target && (mid+1)*(mid+2)/2 > target) {
                left = mid;
                break;
            }
            else if (mid*(mid+1)/2 < target)
                left = mid+1;
            else
                right = mid;
        }
        long d1 = target - left*(left+1)/2, d2 = (left+1)*(left+2)/2 - target;
        if (d1 == 0)
            return (int)left;
        else if (d2 % 2 == 0)
            return (int)left+1;
        else if (left % 2 == 1)
            return (int)left+2;
        else
            return (int)left+3;
    }
}


class Solution {
    public int reachNumber(int target) {
        target = target > 0 ? target : -target;
        long n = (long)((-1+Math.sqrt(1+(long)8*target))/2);
        if (n*(n+1)/2 == target)
            return (int)n;
        n++;
        if ((n*(n+1)/2-target)%2 == 0)
            return (int)n;
        else if (n % 2 == 0)
            return (int)n+1;
        else
            return (int)n+2;
    }
}


class Solution {
    public int reachNumber(int target) {
        target = Math.abs(target);
        int res = 0, step = 0, len = 0;
        while (len < target || (len - target) % 2 != 0) {
            step++;
            len += step;
            res++;
        }
        return res;
    }
}

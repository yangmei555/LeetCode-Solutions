class Solution {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        if (A.length == 0)
            return 0;
        int left = 0, right = 0, res = 0, remove = 0;
        while (right < A.length) {
            if (L <= A[right] && A[right] <= R) {
                res += right - left + 1;
                remove = 0;
            } else if (A[right] > R) {
                left = right + 1;
                remove = 0;
            } else {
                res += right - left - remove;
                remove++;
            }
            right++;
        }
        return res;
    }
}


class Solution {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        if (A.length == 0)
            return 0;
        int left = 0, right = 0, res = 0, added = 0;
        while (right < A.length) {
            if (L <= A[right] && A[right] <= R) {
                added = right - left + 1;
                res += added;
            } else if (A[right] > R) {
                left = right + 1;
                added = 0;
            } else {
                res += added;
            }
            right++;
        }
        return res;
    }
}

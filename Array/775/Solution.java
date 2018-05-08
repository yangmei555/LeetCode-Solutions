class Solution {
    public boolean isIdealPermutation(int[] A) {
        if (A.length < 3)
            return true;
        int max1 = A[0], max2 = Integer.MIN_VALUE;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i-1]) {
                if (A[i] < max1)
                    return false;
                max2 = max1;
                max1 = A[i];
            } else if (A[i] < A[i-1]) {
                if (A[i] < max2)
                    return false;
                max2 = A[i];
            }
        }
        return true;
    }
}


class Solution {
    public boolean isIdealPermutation(int[] A) {
        if (A.length < 3)
            return true;
        int max = Integer.MIN_VALUE;
        for (int i = 2; i < A.length; i++) {
            max = max > A[i-2] ? max : A[i-2];
            if (A[i] < max)
                return false;
        }
        return true;
    }
}


class Solution {
    public boolean isIdealPermutation(int[] A) {
        if (A.length < 3)
            return true;
        for (int i = 0; i < A.length; i++)
            if (A[i]-i < -1 || A[i]-i > 1)
                return false;
        return true;
    }
}

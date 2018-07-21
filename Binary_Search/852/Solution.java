class Solution {
    public int peakIndexInMountainArray(int[] A) {
        for (int i = 0; i < A.length-1; i++) {
            if (A[i] > A[i+1])
                return i;
        }
        return -1;
    }
}


class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int left = 0, right = A.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (A[mid] > A[mid-1] && A[mid] > A[mid+1])
                return mid;
            else if (A[mid-1] > A[mid] && A[mid] > A[mid+1])
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
}


class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int left = 0, right = A.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (A[mid] > A[mid+1])
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
}

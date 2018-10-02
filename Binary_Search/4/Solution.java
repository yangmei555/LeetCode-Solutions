class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int m = nums1.length, n = nums2.length;
        int left = 0, right = m, index = -1;
        while (left <= right) {
            int i = (left + right) / 2;
            int j = (n + m) / 2 - i;
            if ((i == 0 || j == n || nums1[i-1] <= nums2[j]) && 
                (j == 0 || i == m || nums2[j-1] <= nums1[i])) {
                index = i;
                break;
            } else if (i > 0 && j < n && nums1[i-1] > nums2[j]) {
                right = i - 1;
            } else {
                left = i + 1;
            }
        }
        int i = index, j = (n + m) / 2 - index;
        int cand1 = Math.max(i == 0 ? Integer.MIN_VALUE : nums1[i-1], 
                                j == 0 ? Integer.MIN_VALUE : nums2[j-1]);
        int cand2 = Math.min(i == m ? Integer.MAX_VALUE : nums1[i], 
                                j == n ? Integer.MAX_VALUE : nums2[j]);
        return (n + m) % 2 == 0 ? (cand1 + cand2 + .0) / 2 : cand2;
    }
}


// another style of writing binary search 
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int m = nums1.length, n = nums2.length;
        int left = 0, right = m, index = -1;
        while (left < right) {
            int i = (left + right) / 2;
            int j = (n + m) / 2 - i;
            if (j == 0 || i == m || nums2[j-1] <= nums1[i]) {
                right = i;
            } else {
                left = i + 1;
            }
        }
        int i = left, j = (n + m) / 2 - left;
        int cand1 = Math.max(i == 0 ? Integer.MIN_VALUE : nums1[i-1], 
                                j == 0 ? Integer.MIN_VALUE : nums2[j-1]);
        int cand2 = Math.min(i == m ? Integer.MAX_VALUE : nums1[i], 
                                j == n ? Integer.MAX_VALUE : nums2[j]);
        return (n + m) % 2 == 0 ? (cand1 + cand2 + .0) / 2 : cand2;
    }
}

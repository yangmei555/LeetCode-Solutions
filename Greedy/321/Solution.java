class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] res = null;
        for (int i = Math.max(0, k-nums2.length); i <= Math.min(k, nums1.length); i++) {
            int[] from1 = helper1(nums1, i), from2 = helper1(nums2, k-i);
            int[] merge = helper2(from1, from2);    
            // System.out.println(Arrays.toString(from1) + "  " + Arrays.toString(from2));
            // System.out.println(Arrays.toString(merge));
            if (res == null) {
                res = merge;
            } else {
                // for (int j = 0; j < res.length; j++) {
                //     if (res[j] < merge[j]) {
                //         res = merge;
                //         break;
                //     } else if (res[j] > merge[j]) {
                //         break;
                //     }
                // }
                if (nextDiff(res, merge, 0, 0) < 0)
                    res = merge;
            }
        }
        return res;
    }
    
    public int[] helper1(int[] nums, int len) {
        int[] res = new int[len];
        int index = 0, i = 0;
        while (i < nums.length) {
            while (index > 0 && res[index-1] < nums[i] && len - index <= nums.length - 1 - i)
                index--;
            if (index < res.length)
                res[index++] = nums[i];
            i++;
        }
        return res;
    }
    
    public int[] helper2(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length + nums2.length];
        int index1 = 0, index2 = 0, index = 0;
        while (index1 < nums1.length || index2 < nums2.length) {
            if (index2 == nums2.length || index1 != nums1.length && 
                (nums1[index1] > nums2[index2] || 
                    nums1[index1] == nums2[index2] && nextDiff(nums1, nums2, index1, index2) > 0)) {

                res[index++] = nums1[index1++];
            } else {
                res[index++] = nums2[index2++];
            }
        }
        return res;
    }
    
    public int nextDiff(int[] nums1, int[] nums2, int index1, int index2) {
        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] != nums2[index2])
                return nums1[index1] - nums2[index2];
            index1++;
            index2++;
        }
        return index1 == nums1.length ? -1 : 1;
    }
}

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0)
            return new int[0];
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums1)
            set.add(i);
        int[] res = new int[nums1.length < nums2.length ? nums1.length : nums2.length];
        int index = 0;
        for (int i : nums2) {
            if (set.contains(i)) {
                set.remove(i);
                res[index] = i;
                index++;
            }
        }
        return Arrays.copyOf(res, index);
    }
}

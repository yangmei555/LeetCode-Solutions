class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0)
            return new int[0];
        List<Integer> list = new ArrayList<>(nums1.length < nums2.length ? nums1.length : nums2.length);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1)
            map.put(i, map.getOrDefault(i, 0) + 1);
        for (int i : nums2) {
            int n = map.getOrDefault(i, 0);
            if (n != 0) {
                list.add(i);
                map.put(i, n-1);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++)
            res[i] = list.get(i);
        return res;
    }
}


class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0)
            return new int[0];
        int[] res = new int[nums1.length < nums2.length ? nums1.length : nums2.length];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0, index = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                res[index++] = nums1[i];
                i++;
                j++;
            }  else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return Arrays.copyOf(res, index);
    }
}

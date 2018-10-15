class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeMap<Long, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            Long key1 = map.floorKey(0L + nums[i] + t);
            Long key2 = map.ceilingKey(0L + nums[i] - t);
            if (key1 != null && key2 != null && key2 <= key1)
                return true;
            map.put(0L + nums[i], i);
            if (i >= k && map.get(0L + nums[i-k]) == i-k)
                map.remove(0L + nums[i-k]);
        }
        return false;
    }
}

class Solution {
    public int findMaxLength(int[] nums) {
        int ones = 0, zeros = 0, res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1)
                ones++;
            zeros = i + 1 - ones;
            int index = map.getOrDefault(ones - zeros, i);
            res = res > i - index ? res : i - index;
            map.putIfAbsent(ones - zeros, i);
        }
        return res;
    }
}


class Solution {
    public int findMaxLength(int[] nums) {
        int ones = 0, zeros = 0, res = 0;
        int len = nums.length;
        Integer[] map = new Integer[2 * len + 1];
        map[0 + len] = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1)
                ones++;
            zeros = i + 1 - ones;
            if (map[ones - zeros + len] != null)
                res = res > i - map[ones - zeros + len] ? res : i - map[ones - zeros + len];
            else
                map[ones - zeros + len] = i;
        }
        return res;
    }
}

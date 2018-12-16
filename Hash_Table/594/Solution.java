class Solution {
    public int findLHS(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums)
            map.put(i, map.getOrDefault(i, 0) + 1);
        int res = 0;
        for (int i : map.keySet()) {
            if (map.containsKey(i + 1)) {
                res = res > map.get(i) + map.get(i+1) ? res : map.get(i) + map.get(i+1);
            }
        }
        return res;
    }
}


class Solution {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
            if (map.containsKey(n-1))
                res = Math.max(res, map.get(n-1) + map.get(n));
            if (map.containsKey(n+1))
                res = Math.max(res, map.get(n) + map.get(n+1));
        }
        return res;
    }
}


class Solution {
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int count = 1, consec = 1, n1 = nums[0], n2 = nums[0], res = 0;
        for (int i = 1; i <= nums.length; i++) {
            if (i != nums.length && nums[i] == nums[i-1])
                consec++;
            else {
                if (n1 - n2 == 1) {
                    res = res < count + consec ? count + consec : res;
                }
                if (i != nums.length) {
                    n2 = n1;
                    n1 = nums[i];
                    count = consec;
                    consec = 1;
                }
            }
        }
        return res;
    }
}

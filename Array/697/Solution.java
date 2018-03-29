class Solution {
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, int[]> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int[] info = hashMap.get(nums[i]);
            if (info == null) {
                info = new int[3];
                info[0] = 0;
                info[1] = i;
                hashMap.put(nums[i], info);
            }
            info[0]++;
            info[2] = i;
        }
        int max = 0, res = 0;
        for (Integer integer : hashMap.keySet()) {
            int[] info = hashMap.get(integer);
            if (info[0] > max) {
                max = info[0];
                res = info[2]-info[1]+1;
            } else if (info[0] == max) {
                res = (info[2]-info[1]+1 < res ? info[2]-info[1]+1 : res);
            }
        }
        return res;
    }
}

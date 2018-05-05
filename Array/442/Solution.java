class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i+1) {
                if (nums[i] == nums[nums[i]-1]) {
                    res.add(nums[i]);
                    break;
                }
                int temp = nums[i];
                nums[i] = nums[temp-1];
                nums[temp-1] = temp;
            }
        }
        return new ArrayList<>(res);
    }
}


class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = nums[i] > 0 ? nums[i]-1 : -nums[i]-1;
            if (nums[index] < 0) {
                res.add(index+1);
            } else {
                nums[index] = -nums[index];
            }
        }
        return res;
    }
}

class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>(nums.length);
        if (nums.length == 0)
            return res;
        int temp;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i+1 && nums[i] != nums[nums[i]-1]) {
                temp = nums[i];
                nums[i] = nums[temp-1];
                nums[temp-1] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i+1)
                res.add(i+1);
        }
        return res;
    }
}


class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>(nums.length);
        if (nums.length == 0)
            return res;
        for (int i = 0; i < nums.length; i++) {
            int index = nums[i] > 0 ? nums[i]-1 : -nums[i]-1;
            if (nums[index] > 0)
                nums[index] = -nums[index];
        }
        for (int i = 0; i < nums.length; i++)
            if (nums[i] > 0)
                res.add(i+1);
        return res;
    }
}

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(nums, 0, res, temp);
        return res;
    }
    
    public void helper(int[] nums, int index, List<List<Integer>> res, List<Integer> temp) {
        if (index == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        helper(nums, nums.length, res, temp);
        for (int i = index; i < nums.length; i++) {
            if (i != index && nums[i] == nums[i-1]) 
                continue;
            temp.add(nums[i]);
            helper(nums, i+1, res, temp);
            temp.remove(temp.size()-1);
        }
    }
}


class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(nums, 0, res, temp);
        return res;
    }
    
    public void helper(int[] nums, int index, List<List<Integer>> res, List<Integer> temp) {
        res.add(new ArrayList<>(temp));
        for (int i = index; i < nums.length; i++) {
            if (i != index && nums[i] == nums[i-1]) 
                continue;
            temp.add(nums[i]);
            helper(nums, i+1, res, temp);
            temp.remove(temp.size()-1);
        }
    }
}


class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(nums, 0, res, temp);
        return res;
    }
    
    public void helper(int[] nums, int index, List<List<Integer>> res, List<Integer> temp) {
        if (index == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        temp.add(nums[index]);
        helper(nums, index+1, res, temp);
        temp.remove(temp.size()-1);
        while (index < nums.length-1 && nums[index] == nums[index+1])
            index++;
        helper(nums, index+1, res, temp);
    }
}

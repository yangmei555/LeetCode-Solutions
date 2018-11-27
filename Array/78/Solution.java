class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> temp = new LinkedList<>();
        if (nums == null || nums.length == 0)
            return res;
        helper(nums, 0, res, temp);
        return res;
    }
    
    public void helper(int[] nums, int index, List<List<Integer>> res, List<Integer> temp) {
        if (index == nums.length) {
            res.add(temp);
            return;
        }
        // List<Integer> temp1 = new LinkedList<>(temp);
        helper(nums, index + 1, res, temp);
        List<Integer> temp2 = new LinkedList<>(temp);
        temp2.add(nums[index]);
        helper(nums, index + 1, res, temp2);
    }
}

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        res.add(temp);
        for (int i = 0; i < nums.length; i++) {
            int n = res.size();
            for (int j = 0; j < n; j++) {
                List<Integer> list = new ArrayList<>(res.get(j));
                list.add(nums[i]);
                res.add(list);
            }
        }
        return res;
    }
}


class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> temp = new LinkedList<>();
        helper(nums, 0, res, temp);
        return res;
    }
    
    public void helper(int[] nums, int index, List<List<Integer>> res, List<Integer> temp) {
        if (index == nums.length) {
            res.add(new LinkedList<>(temp));
        } else {
            helper(nums, index+1, res, temp);
            temp.add(nums[index]);
            helper(nums, index+1, res, temp);
            temp.remove(temp.size()-1);
        }
    }
}

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        helper(res, nums, 0);
        return res;
    }
    
    public void helper(List<List<Integer>> res, int[] nums, int start) {
        if (start == nums.length-1) {
            res.add(helper2(nums));
        } else {
            int index = start;
            while (index < nums.length) {
                int temp = nums[index];
                for (int i = index-1; i >= start; i--)
                    nums[i+1] = nums[i];
                nums[start] = temp;
                helper(res, nums, start+1);
                temp = nums[start];
                for (int i = start; i < index; i++)
                    nums[i] = nums[i+1];
                nums[index] = temp;
                index++;
                while (index < nums.length && nums[index-1] == nums[index])
                    index++;
            }
        }
    }
    
    public List<Integer> helper2(int[] nums) {
        List<Integer> list = new ArrayList<>(nums.length);
        for (int n : nums)
            list.add(n);
        return list;
    }
}


class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        helper(res, nums, 0);
        return res;
    }
    
    public void helper(List<List<Integer>> res, int[] nums, int start) {
        if (start == nums.length-1) {
            res.add(helper2(nums));
        } else {
            for (int i = start; i < nums.length; i++) {
                int j = start;
                while (j < i) {
                    if (nums[j] == nums[i])
                        break;
                    j++;
                }
                if (j == i) {
                    int temp = nums[i];
                    nums[i] = nums[start];
                    nums[start] = temp;
                    helper(res, nums, start+1);
                    temp = nums[i];
                    nums[i] = nums[start];
                    nums[start] = temp;
                }
            }
        }
    }
    
    public List<Integer> helper2(int[] nums) {
        List<Integer> list = new ArrayList<>(nums.length);
        for (int n : nums)
            list.add(n);
        return list;
    }
}


class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> temp = new ArrayList<>(nums.length);
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        helper(res, temp, nums, used);
        return res;
    }
    
    public void helper(List<List<Integer>> res, List<Integer> temp, int[] nums, boolean[] used) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i] || (i > 0 && nums[i-1] == nums[i] && !used[i-1]))
                    continue;
                used[i] = true;
                temp.add(nums[i]);
                helper(res, temp, nums, used);
                used[i] = false;
                temp.remove(temp.size()-1);
            }
        }
    }
}


class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        helper(res, nums, 0);
        return res;
    }
    
    public void helper(List<List<Integer>> res, int[] nums, int start) {
        if (start == nums.length-1) {
            res.add(helper2(nums));
        } else {
            for (int i = start; i < nums.length; i++) {
                if (i != start && nums[start] == nums[i])
                    continue;
                int temp = nums[i];
                nums[i] = nums[start];
                nums[start] = temp;
                // equals to pass by value not by reference 
                helper(res, nums.clone(), start+1);
            }
        }
    }
    
    public List<Integer> helper2(int[] nums) {
        List<Integer> list = new ArrayList<>(nums.length);
        for (int n : nums)
            list.add(n);
        return list;
    }
}


// come up by myself , I think this is more time and space efficient than the above solutions 
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        // seems even no need to sort 
        // Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        helper(nums, 0, res);
        return res;
    }
    
    public void helper(int[] nums, int index, List<List<Integer>> res) {
        if (index == nums.length) {
            List<Integer> temp = new LinkedList<>();
            for (int n : nums)
                temp.add(n);
            res.add(temp);
        } else {
            int stop = nums[index];
            for (int i = index; i >= 0; i--) {
                if (i != index && nums[i] == stop) 
                    break;
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                helper(nums, index+1, res);
                temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
            }
        }
    }
}


// using a hashset, most straight forward 
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        helper(nums, 0, res);
        return res;
    }
    
    public void helper(int[] nums, int index, List<List<Integer>> res) {
        if (index == nums.length-1) {
            List<Integer> list = new LinkedList<>();
            for (int n : nums)
                list.add(n);
            res.add(list);
        } else {
            Set<Integer> visited = new HashSet<>();
            for (int i = index; i < nums.length; i++) {
                if (visited.add(nums[i])) {
                    swap(nums, i, index);
                    helper(nums, index+1, res);
                    swap(nums, i, index);
                }
            }
        }
    }
    
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

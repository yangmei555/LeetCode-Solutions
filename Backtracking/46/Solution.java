class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> temp = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        helper(res, temp, nums, used);
        return res;
    }
    
    public void helper(List<List<Integer>> res, List<Integer> temp, int[] nums, boolean[] used) {
        if (temp.size() == nums.length) {
            res.add(new LinkedList<>(temp));
        } else {
            for (int i = 0; i < used.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    temp.add(nums[i]);
                    helper(res, temp, nums, used);
                    temp.remove(temp.size()-1);
                    used[i] = false;
                }
            }
        }
    }
}


class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> temp = new ArrayList<>(nums.length);
        List<List<Integer>> res = new LinkedList<>();
        helper(res, temp, nums, 0);
        return res;
    }
    
    public void helper(List<List<Integer>> res, List<Integer> temp, int[] nums, int start) {
        if (start == nums.length) {
            res.add(new ArrayList<>(temp));
        } else {
            for (int i = start; i < nums.length; i++) {
                temp.add(nums[i]);
                int x = nums[i];
                nums[i] = nums[start];
                helper(res, temp, nums, start+1);
                nums[i] = x;
                temp.remove(temp.size()-1);
            }
        }
    }
}


class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        helper(res, nums, 0);
        return res;
    }
    
    public void helper(List<List<Integer>> res, int[] nums, int start) {
        if (start == nums.length-1) {
            res.add(helper2(nums));
        } else {
            for (int i = start; i < nums.length; i++) {
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
    
    public List<Integer> helper2(int[] nums) {
        List<Integer> res = new ArrayList<>(nums.length);
        for (int n : nums)
            res.add(n);
        return res;
    }
}


class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        res.add(Arrays.asList(nums[0]));
        for (int i = 1; i < nums.length; i++) {
            List<List<Integer>> newres = new LinkedList<>();
            for (List<Integer> list : res) {
                for (int j = 0; j <= i; j++) {
                    List<Integer> list2 = new LinkedList<>(list);
                    list2.add(j, nums[i]);
                    newres.add(list2);
                }
            }
            res = newres;
        }
        return res;
    }
}


class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
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

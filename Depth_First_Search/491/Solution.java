class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>>[] list = new List[nums.length];
        for (int i = 0; i < nums.length; i++) {
            list[i] = new LinkedList<>();
            List<Integer> li = new LinkedList<>();
            li.add(nums[i]);
            list[i].add(li);
            for (int j = 0; j < i; j++) {
                if (nums[j] <= nums[i]) {
                    for (List<Integer> loop : list[j]) {
                        List<Integer> temp = new LinkedList<>(loop);
                        temp.add(nums[i]);
                        list[i].add(temp);
                    }
                }
            }
        }
        Set<List<Integer>> set = new HashSet<>();
        for (List<List<Integer>> loop : list) {
            for (List<Integer> temp : loop) {
                if (temp.size() > 1)
                    set.add(temp);
            }
        }
        return new LinkedList<>(set);
    }
}


class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>>[] list = new List[nums.length];
        Integer[] map = new Integer[201];
        for (int i = 0; i < nums.length; i++) {
            list[i] = new LinkedList<>();
            if (map[nums[i]+100] == null) {
                List<Integer> temp = new LinkedList<>();
                temp.add(nums[i]);
                list[i].add(temp);
            }
            int j = map[nums[i]+100] == null ? 0 : map[nums[i]+100];
            for (; j < i; j++) {
                if (nums[j] <= nums[i]) {
                    for (List<Integer> loop : list[j]) {
                        List<Integer> temp = new LinkedList<>(loop);
                        temp.add(nums[i]);
                        list[i].add(temp);
                    }
                }
            }
            map[nums[i]+100] = i;
        }
        List<List<Integer>> res = new LinkedList<>();
        for (List<List<Integer>> loop : list) {
            for (List<Integer> temp : loop) {
                if (temp.size() > 1)
                    res.add(temp);
            }
        }
        return res;
    }
}


class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> temp = new ArrayList<>();
        helper(nums, set, temp, 0);
        return new LinkedList<>(set);
    }
    
    public void helper(int[] nums, Set<List<Integer>> set, List<Integer> temp, int index) {
        if (index == nums.length)
            return;
        helper(nums, set, temp, index+1);
        if (temp.size() == 0 || temp.get(temp.size()-1) <= nums[index]) {
            temp.add(nums[index]);
            if (temp.size() != 1)
                set.add(new ArrayList<>(temp));
            helper(nums, set, temp, index+1);
            temp.remove(temp.size()-1);
        }
    }
}


class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> temp = new ArrayList<>(); 
        helper(nums, res, temp, 0);
        return res;
    }
    
    public void helper(int[] nums, List<List<Integer>> res, List<Integer> temp, int index) {
        if (index == nums.length)
            return;
        boolean[] maps = new boolean[201];
        for (int i = index; i < nums.length; i++) {
            if (maps[nums[i]+100])
                continue;
            maps[nums[i]+100] = true;
            if (temp.size() == 0 || temp.get(temp.size()-1) <= nums[i]) {
                temp.add(nums[i]);
                if (temp.size() != 1)
                    res.add(new ArrayList<>(temp));
                helper(nums, res, temp, i+1);
                temp.remove(temp.size()-1);
            }
        }
    }
}


class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> temp = new ArrayList<>();
        helper(nums, res, temp, 0);
        return res;
    }
    
    public void helper(int[] nums, List<List<Integer>> res, List<Integer> temp, int index) {
        if (index == nums.length) {
            if (temp.size() > 1)
                res.add(new ArrayList<>(temp));
            return;
        }
        if (temp.size() == 0 || temp.get(temp.size()-1) != nums[index])
            helper(nums, res, temp, index+1);
        if (temp.size() == 0 || temp.get(temp.size()-1) <= nums[index]) {
            temp.add(nums[index]);
            helper(nums, res, temp, index+1);
            temp.remove(temp.size()-1);
        }
    }
}

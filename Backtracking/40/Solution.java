class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(candidates, target, 0, res, temp);
        return res;
    }
    
    public void helper(int[] can, int target, int index, List<List<Integer>> res, 
                                                                        List<Integer> temp) {
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (index == can.length) {
            return;
        }
        while (index < can.length) {
            if (target >= can[index]) {
                temp.add(can[index]);
                helper(can, target-can[index], index+1, res, temp);
                temp.remove(temp.size()-1);
            }
            while (index >= 0 && index < can.length - 1 && can[index] == can[index+1])
                index++;
            index++;
        }
    }
}


class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> temp = new LinkedList<>();
        helper(candidates, 0, target, res, temp);
        return res;
    }
    
    public void helper(int[] candidates, int index, int target, List<List<Integer>> res, 
                                                                            List<Integer> temp) {
        if (target == 0) {
            res.add(new LinkedList<>(temp));
        } else if (target > 0 && index != candidates.length) {
            temp.add(candidates[index]);
            helper(candidates, index+1, target - candidates[index], res, temp);
            temp.remove(temp.size()-1);
            index++;
            while (index < candidates.length && candidates[index-1] == candidates[index])
                index++;
            helper(candidates, index, target, res, temp);
        }
    }
}

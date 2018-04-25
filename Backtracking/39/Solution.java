class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        if (candidates == null || candidates.length == 0)
            return res;
        helper(candidates, target, 0, res, temp);
        return res;
    }
    
    public void helper(int[] can, int target, int index, List<List<Integer>> res, List<Integer> temp) {
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (index == can.length)
            return;
        helper(can, target, index + 1, res, temp);
        if (target >= can[index]) {
            temp.add(can[index]);
            helper(can, target-can[index], index, res, temp);
            temp.remove(temp.size()-1);
        }
    }
}


class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        if (candidates == null || candidates.length == 0)
            return res;
        helper(candidates, target, 0, res, temp);
        return res;
    }
    
    public void helper(int[] can, int target, int index, List<List<Integer>> res, List<Integer> temp) {
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (index == can.length)
            return;
        for (int i = index; i < can.length; i++) {
            if (target >= can[i]) {
                temp.add(can[i]);
                helper(can, target-can[i], i, res, temp);
                temp.remove(temp.size()-1);
            }
        }
    }
}


class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<List<Integer>>> tarlist = new ArrayList<>(target + 1);
        for (int t = 0; t <= target; t++) {
            List<List<Integer>> list = new ArrayList<>();
            if (t == 0) {
                List<Integer> empty = new ArrayList<>();
                list.add(empty);
                tarlist.add(list);
                continue;
            }
            for (int c : candidates) {
                if (t >= c && tarlist.get(t-c).size() != 0) {
                    for (List<Integer> temp : tarlist.get(t-c)) {
                        if (temp.size() == 0 || c >= temp.get(temp.size()-1)) {
                            List<Integer> temp1 = new ArrayList<>(temp);
                            temp1.add(c);
                            list.add(temp1);
                        }
                    }
                }
            }
            tarlist.add(list);
        }
        return tarlist.get(target);
    }
}

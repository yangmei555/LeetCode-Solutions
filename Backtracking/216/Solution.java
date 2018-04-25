class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        if (k > 9)
            return res;
        helper(k, n, 1, res, temp);
        return res;
    }
    
    public void helper(int k, int n, int index, List<List<Integer>> res, List<Integer> temp) {
        if (k == 0 && n == 0) {
            res.add(new ArrayList<>(temp));
            return;
        } else if (index == 10) {
            return;
        }
        helper(k, n, index+1, res, temp);
        if (n >= index && k != 0) {
            temp.add(index);
            helper(k-1, n-index, index+1, res, temp);
            temp.remove(temp.size()-1);
        }
    }
}

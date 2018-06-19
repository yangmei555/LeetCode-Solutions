class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> temp = new LinkedList<>();
        helper(res, temp, 2, n);
        return res;
    }
    
    public void helper(List<List<Integer>> res, List<Integer> temp, int start, int n) {
        for (int i = start; i * i <= n; i++) {
            if (n % i == 0) {
                temp.add(i);
                helper(res, temp, i, n/i);
                temp.remove(temp.size()-1);
            }
        }
        if (temp.size() != 0) {
            temp.add(n);
            res.add(new LinkedList<>(temp));
            temp.remove(temp.size()-1);
        }
    }
}

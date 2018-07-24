class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> cur = res.size() == 0 ? new ArrayList<>(1) : 
                                                new ArrayList<>(res.get(res.size()-1));
            cur.add(1);
            for (int j = cur.size()-2; j >= 1; j--)
                cur.set(j, cur.get(j-1) + cur.get(j));
            res.add(cur);
        }
        return res;
    }
}

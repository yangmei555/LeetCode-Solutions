class Solution {
    public List<Integer> grayCode(int n) {
        if (n == 0) {
            List<Integer> res = new ArrayList<>();
            res.add(0);
            return res;
        }
        List<Integer> res = grayCode(n-1);
        int size = res.size(), addup = 1 << (n-1);
        for (int i = size-1; i >= 0; i--)
            res.add(res.get(i) + addup);
        return res;
    }
}


class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>(1 << n);
        res.add(0);
        for (int i = 1; i <= n; i++) {
            int addup = 1 << (i-1), size = res.size();
            for (int j = size-1; j >= 0; j--)
                res.add(res.get(j) + addup);
        }
        return res;
    }
}


class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>(1 << n);
        for (int i = 0; i < (1 << n); i++) 
            res.add(i ^ (i >> 1));
        return res;
    }
}

class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new LinkedList<>();
        for (int i = left; i <= right; i++) {
            if (helper(i) != -1)
                list.add(i);
        }
        return list;
    }
    
    public int helper(int n) {
        int t = n, digit = 0;
        while (t != 0) {
            if ((digit = t % 10) == 0 || n % digit != 0)
                return -1;
            t /= 10;
        }
        return n;
    }
}

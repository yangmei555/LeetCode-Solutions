class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        char[] ch = input.toCharArray();
        List<Integer>[][] memo = new List[ch.length][ch.length];
        return helper(ch, 0, ch.length-1, memo);
    }
    
    public List<Integer> helper(char[] ch, int left, int right, List<Integer>[][] memo) {
        if (memo[left][right] != null)
            return memo[left][right];
        List<Integer> res = new LinkedList<>();
        Integer num = 0;
        for (int i = left; i <= right; i++) {
            if (ch[i] == '+' || ch[i] == '-' || ch[i] == '*') {
                num = null;
                List<Integer> llist = helper(ch, left, i-1, memo), 
                                rlist = helper(ch, i+1, right, memo);
                for (int m : llist) 
                    for (int n : rlist) 
                        res.add(ch[i] == '+' ? m + n : ch[i] == '-' ? m - n : m * n);
            } else if (num != null) {
                num = num * 10 + ch[i] - '0';
            }
        }
        if (num != null)
            res.add(num);
        memo[left][right] = res;
        return res;
    }
}

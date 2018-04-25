class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0)
            return res;
        StringBuilder sb = new StringBuilder();
        helper(n, 0, 0, res, sb);
        return res;
    }
    
    public void helper(int n, int left, int right, List<String> res, StringBuilder sb) {
        if (left < right || left > n)
            return;
        if (sb.length() == 2 * n && left == right) {
            res.add(sb.toString());
            return;
        }
        if (left < n) {
            sb.append('(');
            helper(n, left+1, right, res, sb);
            sb.deleteCharAt(sb.length()-1);
        }
        if (left > right) {
            sb.append(')');
            helper(n, left, right+1, res, sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}

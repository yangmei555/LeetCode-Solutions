class Solution {
    public List<String> addOperators(String num, int target) {
        char[] ch = num.toCharArray();
        StringBuilder sb = new StringBuilder();
        List<String> res = new LinkedList<>();
        helper(ch, 0, sb, res, target);
        return res;
    }
    
    public void helper(char[] ch, int index, StringBuilder sb, List<String> res, int target) {
        if (index == ch.length) {
            char[] expre = sb.toString().toCharArray();
            long[] nums = new long[expre.length];
            char[] op = new char[expre.length];
            int p1 = 0, p2 = 0, i = 0;
            while (i < expre.length) {
                if (expre[i] >= '0' && expre[i] <= '9') {
                    long num = 0;
                    while (i < expre.length && expre[i] >= '0' && expre[i] <= '9')
                        num = num * 10 + (expre[i++] - '0');
                    nums[p1++] = num;
                } else {
                    if (expre[i] != '*') {
                        while (p2 != 0) {
                            if (op[p2-1] == '*') 
                                nums[p1-2] *= nums[--p1];
                            else if (op[p2-1] == '+')
                                nums[p1-2] += nums[--p1];
                            else
                                nums[p1-2] -= nums[--p1];
                            p2--;
                        }
                    }
                    op[p2++] = expre[i];
                    i++;
                }
            }
            while (p2 != 0) {
                if (op[p2-1] == '+')
                    nums[p1-2] += nums[--p1];
                else if (op[p2-1] == '-')
                    nums[p1-2] -= nums[--p1];
                else
                    nums[p1-2] *= nums[--p1];
                p2--;
            }
            if (p1 == 1 && nums[0] == target) 
                res.add(sb.toString());
        } else {
            sb.append(ch[index]);
            if (ch[index] != '0' || index == ch.length-1 || 
                sb.length() != 1 && sb.charAt(sb.length()-2) >= '0' && 
                sb.charAt(sb.length()-2) <= '9')
                helper(ch, index+1, sb, res, target);
            if (index != ch.length-1) {
                sb.append('+');
                helper(ch, index+1, sb, res, target);
                sb.deleteCharAt(sb.length()-1);
                sb.append('-');
                helper(ch, index+1, sb, res, target);
                sb.deleteCharAt(sb.length()-1);
                sb.append('*');
                helper(ch, index+1, sb, res, target);
                sb.deleteCharAt(sb.length()-1);
            }
            sb.deleteCharAt(sb.length()-1);
        }
    }
}

class Solution {
    public List<Integer> splitIntoFibonacci(String S) {
        char[] ch = S.toCharArray();
        List<Integer> res = new ArrayList<>();
        int n1 = 0;
        for (int i = 0; i < 10 && i < (ch.length+2)/3; i++) {
            // if (n1 * 10 / 10 != n1 || n1 * 10 + ch[i] - '0' < 0)
            //     break;    // detect overflow
            n1 = n1 * 10 + ch[i] - '0';
            int n2 = 0;
            for (int j = i+1; j <= i+10 && j < ch.length*2/3; j++) {
                // if (n2 * 10 / 10 != n2 || n2 * 10 + ch[j] - '0' < 0)
                //     break;    // detect overflow
                n2 = n2 * 10 + ch[j] - '0';
                res.add(n1);
                res.add(n2);
                if (helper(ch, n1, n2, ch[j+1] - '0', j+2, res))
                    return res;
                res.clear();
                if (n2 == 0)
                    break;
            }
            if (n1 == 0)
                break;
        }
        return res;
    }
    
    public boolean helper(char[] ch, int n1, int n2, int n3, int index, List<Integer> res) {
        if (n3 == 0 && (n1 != 0 || n2 != 0))
            return false;
        if (n1 + n2 == n3) {
            res.add(n3);
            n1 = n2;
            n2 = n3;
            n3 = 0;
            if (index == ch.length)
                return true;
        }
        if (n3 * 10 > n1 + n2 || index == ch.length)
            return false;
        return helper(ch, n1, n2, n3 * 10 + ch[index] - '0', index + 1, res);
    }
}


class Solution {
    public List<Integer> splitIntoFibonacci(String S) {
        char[] ch = S.toCharArray();
        List<Integer> res = new ArrayList<>();
        int n1 = 0;
        for (int i = 0; i < 10 && i < (ch.length+2)/3; i++) {
            // if (n1 * 10 / 10 != n1 || n1 * 10 + ch[i] - '0' < 0)
            //     break;    // detect overflow
            if (n1 == 0 && i != 0)
                break;
            n1 = n1 * 10 + ch[i] - '0';
            int n2 = 0;
            for (int j = i+1; j <= i+10 && j < ch.length*2/3; j++) {
                // if (n2 * 10 / 10 != n2 || n2 * 10 + ch[j] - '0' < 0)
                //     break;    // detect overflow
                if (n2 == 0 && j != i+1)
                    break;
                n2 = n2 * 10 + ch[j] - '0';
                res.add(n1);
                res.add(n2);
                if (helper(ch, n1, n2, ch[j+1]-'0', j+2, res))
                    return res;
                res.clear();
            }
        }
        return res;
    }
    
    public boolean helper(char[] ch, int n1, int n2, int n3, int index, List<Integer> res) {
        if (index == ch.length) {
            if (n1 + n2 == n3) {
                res.add(n3);
                return true;
            }
            return false;
        }
        if (n1 + n2 == n3) {
            res.add(n3);
            return helper(ch, n2, n3, ch[index]-'0', index+1, res);
        } else if (n3 != 0 && n3 * 10 <= n1 + n2) {
            return helper(ch, n1, n2, n3*10+ch[index]-'0', index+1, res);
        } else {
            return false;
        }
    }
}


class Solution {
    public List<Integer> splitIntoFibonacci(String S) {
        char[] ch = S.toCharArray();
        List<Integer> res = new ArrayList<>();
        int n1 = 0;
        for (int i = 0; i < 10 && i < (ch.length+2)/3; i++) {
            // if (n1 * 10 / 10 != n1 || n1 * 10 + ch[i] - '0' < 0)
            //     break;    // detect overflow
            if (n1 == 0 && i != 0)
                break;
            n1 = n1 * 10 + ch[i] - '0';
            int n2 = 0;
            for (int j = i+1; j <= i+10 && j < ch.length*2/3; j++) {
                // if (n2 * 10 / 10 != n2 || n2 * 10 + ch[j] - '0' < 0)
                //     break;    // detect overflow
                if (n2 == 0 && j != i+1)
                    break;
                n2 = n2 * 10 + ch[j] - '0';
                res.add(n1);
                res.add(n2);
                for (int k = j+2, num1 = n1, num2 = n2, num3 = ch[j+1]-'0'; k <= ch.length; k++) {
                    if (num3 == 0 && (num1 != 0 || num2 != 0))
                        break;
                    if (num3 == num1 + num2) {
                        res.add(num3);
                        num1 = num2;
                        num2 = num3;
                        num3 = 0;
                        if (k == ch.length) 
                            return res;
                    } else if (num3 > num1 + num2) {
                        break;
                    }
                    if (k != ch.length)
                        num3 = num3 * 10 + ch[k] - '0';
                }
                res.clear();
            }
        }
        return res;
    }
}

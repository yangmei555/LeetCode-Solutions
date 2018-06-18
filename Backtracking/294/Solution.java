class Solution {
    public boolean canWin(String s) {
        char[] ch = s.toCharArray();
        return helper(ch, 1);
    }
    
    public boolean helper(char[] ch, int player) {
        for (int i = 1; i < ch.length; i++) {
            if (ch[i-1] == '+' && ch[i] == '+') {
                ch[i-1] = '-';
                ch[i] = '-';
                if (helper(ch, player == 1 ? 2 : 1)) {
                    ch[i-1] = '+';
                    ch[i] = '+';
                    if (player == 1) 
                        return true;
                } else {
                    ch[i-1] = '+';
                    ch[i] = '+';
                    if (player == 2) 
                        return false;
                }
            }
        }
        return player == 1 ? false : true;
    }
}


class Solution {
    public boolean canWin(String s) {
        char[] ch = s.toCharArray();
        Map<Integer, Boolean> memo = new HashMap<>();
        return helper(ch, 1, memo);
    }
    
    public boolean helper(char[] ch, int player, Map<Integer, Boolean> memo) {
        if (memo.containsKey(Arrays.hashCode(ch)))
            return memo.get(Arrays.hashCode(ch));
        for (int i = 1; i < ch.length; i++) {
            if (ch[i-1] == '+' && ch[i] == '+') {
                ch[i-1] = '-';
                ch[i] = '-';
                if (helper(ch, player == 1 ? 2 : 1, memo)) {
                    ch[i-1] = '+';
                    ch[i] = '+';
                    if (player == 1) {
                        memo.put(Arrays.hashCode(ch), true);
                        return true;
                    }
                } else {
                    ch[i-1] = '+';
                    ch[i] = '+';
                    if (player == 2) {
                        memo.put(Arrays.hashCode(ch), false);
                        return false;
                    }
                }
            }
        }
        boolean res = player == 1 ? false : true;
        if (res == false)
            memo.put(Arrays.hashCode(ch), false);
        return res;
    }
}


class Solution {
    public boolean canWin(String s) {
        Map<Integer, Boolean> memo = new HashMap<>();
        return helper(s.toCharArray(), memo);
    }
    
    public boolean helper(char[] ch, Map<Integer, Boolean> memo) {
        int hash = Arrays.hashCode(ch);
        if (memo.containsKey(hash))
            return memo.get(hash);
        for (int i = 1; i < ch.length; i++) {
            if (ch[i-1] == '+' && ch[i] == '+') {
                ch[i-1] = '-';
                ch[i] = '-';
                if (!helper(ch, memo)) {
                    ch[i-1] = '+';
                    ch[i] = '+';
                    memo.put(hash, true);
                    return true;
                }
                ch[i-1] = '+';
                ch[i] = '+';
            }
        }
        memo.put(hash, false);
        return false;
    }
}

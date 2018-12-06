class Solution {
    public List<List<String>> partition(String s) {
        char[] ch = s.toCharArray();
        List<List<String>> res = new LinkedList<>();
        List<String> temp = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        helper(ch, 0, res, temp, sb, 0);
        return res;
    }
    
    public void helper(char[] ch, int index, List<List<String>> res, List<String> temp, 
                        StringBuilder sb, int unmatched) {
        if (index == ch.length) {
            if (unmatched == 0 && sb.length() != 0 || unmatched == 1 && sb.length() == 1) {
                temp.add(sb.toString());
                res.add(new LinkedList<>(temp));
                temp.remove(temp.size()-1);
            }
        } else {
            if (unmatched == 0 && sb.length() != 0 || unmatched == 1 && sb.length() == 1) {
                StringBuilder str = new StringBuilder(sb);
                temp.add(sb.toString());
                sb.setLength(0);
                sb.append(ch[index]);
                helper(ch, index+1, res, temp, sb, 1);
                temp.remove(temp.size()-1);
                sb.setLength(0);
                sb.append(str.toString());
            }
            if (unmatched > 0 && ch[index] == sb.charAt(unmatched-1)) {
                sb.append(ch[index]);
                helper(ch, index+1, res, temp, sb, unmatched-1);
                sb.deleteCharAt(sb.length()-1);
            }
            if (sb.length() == unmatched && unmatched >= 2 && ch[index] == sb.charAt(unmatched-2)) {
                sb.append(ch[index]);
                helper(ch, index+1, res, temp, sb, unmatched-2);
                sb.deleteCharAt(sb.length()-1);
            }
            if (ch.length-1-index >= sb.length() && sb.length() == unmatched) {
                sb.append(ch[index]);
                helper(ch, index+1, res, temp, sb, sb.length());
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
}


class Solution {
    public List<List<String>> partition(String s) {
        char[] ch = s.toCharArray();
        List<List<String>> res = new LinkedList<>();
        List<String> temp = new LinkedList<>();
        helper(s, ch, 0, res, temp);
        return res;
    }
    
    public void helper(String str, char[] ch, int index, List<List<String>> res, 
                        List<String> temp) {
        if (index == ch.length) {
            res.add(new LinkedList<>(temp));
        } else {
            for (int i = index; i < ch.length; i++) {
                if (judge(ch, index, i)) {
                    temp.add(str.substring(index, i+1));
                    helper(str, ch, i+1, res, temp);
                    temp.remove(temp.size()-1);
                }
            }
        }
    }
    
    public boolean judge(char[] ch, int i, int j) {
        while (i < j) {
            if (ch[i] != ch[j])
                return false;
            i++;
            j--;
        }
        return true;
    }
}


class Solution {
    public List<List<String>> partition(String s) {
        char[] ch = s.toCharArray();
        List<List<String>> res = new LinkedList<>();
        List<String> temp = new LinkedList<>();
        boolean[][] decide = new boolean[ch.length][ch.length];
        setboolean(ch, decide);
        helper(s, ch, 0, res, temp, decide);
        return res;
    }
    
    public void helper(String str, char[] ch, int index, List<List<String>> res, 
                        List<String> temp, boolean[][] decide) {
        if (index == ch.length) {
            res.add(new LinkedList<>(temp));
        } else {
            for (int i = index; i < ch.length; i++) {
                if (decide[index][i]) {
                    temp.add(str.substring(index, i+1));
                    helper(str, ch, i+1, res, temp, decide);
                    temp.remove(temp.size()-1);
                }
            }
        }
    }
    
    public void setboolean(char[] ch, boolean[][] decide) {
        for (int len = 1; len <= ch.length; len++) {
            for (int i = 0; i+len-1 < ch.length; i++) {
                int j = i + len - 1;
                if (j - i == 0) {
                    decide[i][j] = true;
                } else if (j - i == 1) {
                    decide[i][j] = ch[i] == ch[j];
                } else {
                    decide[i][j] = ch[i] == ch[j] && decide[i+1][j-1];
                }
            }
        }
    }
}


class Solution {
    public List<List<String>> partition(String s) {
        char[] ch = s.toCharArray();
        List<List<String>> res = new LinkedList<>();
        List<String> temp = new LinkedList<>();
        boolean[][] decide = new boolean[ch.length][ch.length];
        setboolean(ch, decide);
        helper(s, ch, 0, res, temp, decide);
        return res;
    }
    
    public void helper(String str, char[] ch, int index, List<List<String>> res, 
                        List<String> temp, boolean[][] decide) {
        if (index == ch.length) {
            res.add(new LinkedList<>(temp));
        } else {
            for (int i = index; i < ch.length; i++) {
                if (decide[index][i]) {
                    temp.add(str.substring(index, i+1));
                    helper(str, ch, i+1, res, temp, decide);
                    temp.remove(temp.size()-1);
                }
            }
        }
    }
    
    public void setboolean(char[] ch, boolean[][] decide) {
        for (int j = 0; j < ch.length; j++) {
            for (int i = 0; i <= j; i++) {
                decide[i][j] = ch[i] == ch[j] && (j-i <= 1 || decide[i+1][j-1]);
            }
        }
    }
}


// actually, we need to do a memoization here, even if it is slower than not doing memoization. 
// because there will be lots of duplicate visits to each single index 
class Solution {
    public List<List<String>> partition(String s) {
        char[] ch = s.toCharArray();
        boolean[][] decide = new boolean[ch.length][ch.length];
        setboolean(ch, decide);
        return helper(s, ch, 0, decide, new List[ch.length]);
    }
    
    public List<List<String>> helper(String str, char[] ch, int index, boolean[][] decide, 
                                                                List<List<String>>[] memo) {
        if (index == ch.length) {
            List<List<String>> res = new LinkedList<>();
            res.add(new LinkedList<>());
            return res;
        } else {
            if (memo[index] != null)
                return memo[index];
            List<List<String>> res = new LinkedList<>();
            for (int i = index; i < ch.length; i++) {
                if (decide[index][i]) {
                    String sub = str.substring(index, i+1);
                    for (List<String> list : helper(str, ch, i+1, decide, memo)) {
                        List<String> temp = new LinkedList<>(list);
                        temp.add(0, sub);
                        res.add(temp);
                    }
                }
            }
            memo[index] = res;
            return res;
        }
    }
    
    public void setboolean(char[] ch, boolean[][] decide) {
        for (int j = 0; j < ch.length; j++) {
            for (int i = 0; i <= j; i++) {
                decide[i][j] = ch[i] == ch[j] && (j-i <= 1 || decide[i+1][j-1]);
            }
        }
    }
}

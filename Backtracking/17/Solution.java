class Solution {
    public List<String> letterCombinations(String digits) {
        String[] board = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) 
            return res;
        char[] temp = new char[digits.length()];
        char[] ch = digits.toCharArray();
        helper(ch, 0, board, res, temp);
        return res;
    }
    
    public void helper(char[] ch, int index, String[] board, List<String> res, char[] temp) {
        if (index == ch.length) {
            res.add(new String(temp));
            return;
        }
        char[] arr = board[ch[index] - '2'].toCharArray();
        for (char c : arr) {
            temp[index] = c;
            helper(ch, index+1, board, res, temp);
        }
    }
}


class Solution {
    public List<String> letterCombinations(String digits) {
        String[] board = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) 
            return res;
        char[] temp = new char[digits.length()];
        char[] ch = digits.toCharArray();
        res.add("");
        for (int i = 0; i < ch.length; i++) {
            List<String> newres = new ArrayList<>();
            for (char c : board[ch[i]-'2'].toCharArray()) {
                for (String s : res) {
                    newres.add(s + c);
                }
            }
            res = newres;
        }
        return res;
    }
}

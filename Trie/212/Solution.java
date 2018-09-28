// brain less method. notice that there may be duplicates in words array 
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> set = new HashSet<>();
        for (String string : words) {
            char[] ch = string.toCharArray();
            boolean flag = false;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (helper(board, i, j, ch, 0)) {
                        set.add(string);
                        flag = true;
                        break;
                    }
                }
                if (flag)
                    break;
            }
        }
        return new LinkedList<>(set);
    }
    
    public boolean helper(char[][] board, int row, int col, char[] ch, int index) {
        if (index == ch.length)
            return true;
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || 
            board[row][col] != ch[index])
            return false;
        board[row][col] = ' ';
        boolean flag = false;
        if (helper(board, row-1, col, ch, index+1) || helper(board, row+1, col, ch, index+1) || 
            helper(board, row, col-1, ch, index+1) || helper(board, row, col+1, ch, index+1))
            flag = true;
        board[row][col] = ch[index];
        return flag;
    }
}

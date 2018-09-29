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


// use a trie to store the words 
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        Node root = new Node();
        for (String w : words) {
            Node cur = root;
            for (char c : w.toCharArray()) {
                if (cur.nodes[c-'a'] == null)
                    cur.nodes[c-'a'] = new Node();
                cur = cur.nodes[c-'a'];
            }
            cur.end = true;
        }
        List<String> res = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                helper(board, i, j, root, res, sb);
            }
        }
        return res;
    }
    
    // see whether board[row][col] is a child of cur 
    public void helper(char[][] board, int row, int col, Node cur, List<String> res, 
                                                                    StringBuilder sb) {
        if (cur.end && !cur.found) {
            res.add(sb.toString());
            cur.found = true;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || 
            board[row][col] == ' ')
            return;
        int index = board[row][col] - 'a';
        if (cur.nodes[index] != null) {
            sb.append(board[row][col]);
            board[row][col] = ' ';
            helper(board, row-1, col, cur.nodes[index], res, sb);
            helper(board, row+1, col, cur.nodes[index], res, sb);
            helper(board, row, col-1, cur.nodes[index], res, sb);
            helper(board, row, col+1, cur.nodes[index], res, sb);
            sb.deleteCharAt(sb.length()-1);
            board[row][col] = (char)('a' + index);
        }
    }
    
    class Node {
        Node[] nodes = new Node[26];
        boolean end, found;
    }
}


// another style of implementing the same idea with the above solution 
class Solution {
    int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public List<String> findWords(char[][] board, String[] words) {
        Node root = new Node();
        for (String w : words) {
            Node cur = root;
            for (char c : w.toCharArray()) {
                if (cur.nodes[c-'a'] == null)
                    cur.nodes[c-'a'] = new Node();
                cur = cur.nodes[c-'a'];
            }
            cur.end = true;
        }
        List<String> res = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (root.nodes[board[i][j]-'a'] != null) {
                    int index = board[i][j] - 'a';
                    sb.append(board[i][j]);
                    board[i][j] = ' ';
                    helper(board, i, j, root.nodes[index], res, sb);
                    board[i][j] = (char)('a' + index);
                    sb.deleteCharAt(sb.length()-1);
                }
            }
        }
        return res;
    }
    
    // alreay know board[row][col] is represented by cur, 
    // see whether the neighbours of board[row][col] is children of cur 
    public void helper(char[][] board, int row, int col, Node cur, List<String> res, 
                                                                    StringBuilder sb) {
        if (cur.end && !cur.found) {
            res.add(sb.toString());
            cur.found = true;
        }
        for (int[] d : dir) {
            int x = row + d[0], y = col + d[1];
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && 
                board[x][y] != ' ' && cur.nodes[board[x][y]-'a'] != null) {
                int index = board[x][y] - 'a';
                sb.append(board[x][y]);
                board[x][y] = ' ';
                helper(board, x, y, cur.nodes[index], res, sb);
                board[x][y] = (char)('a' + index);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
    
    class Node {
        Node[] nodes = new Node[26];
        boolean end, found;
    }
}

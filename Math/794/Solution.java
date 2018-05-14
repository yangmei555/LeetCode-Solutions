class Solution {
    public boolean validTicTacToe(String[] board) {
        char[][] ch = new char[3][3];
        for (int i = 0; i < board.length; i++)
            ch[i] = board[i].toCharArray();
        int o = 0, x = 0, win = 0;
        for (int i = 0; i < ch.length; i++) {
            for (int j = 0; j < ch[0].length; j++) {
                if (ch[i][j] == 'X')
                    x++;
                else if (ch[i][j] == 'O')
                    o++;
                if (ch[i][j] != ' ') {
                    if (i == 1 && j == 1) {
                        if (ch[i][j] == ch[i-1][j] && ch[i][j] == ch[i+1][j]) {
                            if (win == 0)
                                win = ch[i][j] == 'X' ? 1 : -1;
                            else if (win != (ch[i][j] == 'X' ? 1 : -1))
                                return false;
                        }
                        if (ch[i][j] == ch[i][j-1] && ch[i][j] == ch[i][j+1]) {
                            if (win == 0)
                                win = ch[i][j] == 'X' ? 1 : -1;
                            else if (win != (ch[i][j] == 'X' ? 1 : -1))
                                return false;
                        }
                        if (ch[i][j] == ch[i-1][j-1] && ch[i][j] == ch[i+1][j+1]) {
                            if (win == 0)
                                win = ch[i][j] == 'X' ? 1 : -1;
                            else if (win != (ch[i][j] == 'X' ? 1 : -1))
                                return false;
                        }
                        if (ch[i][j] == ch[i-1][j+1] && ch[i][j] == ch[i+1][j-1]) {
                            if (win == 0)
                                win = ch[i][j] == 'X' ? 1 : -1;
                            else if (win != (ch[i][j] == 'X' ? 1 : -1))
                                return false;
                        }
                    } else if (i == 1) {
                        if (ch[i][j] == ch[i-1][j] && ch[i][j] == ch[i+1][j]) {
                            if (win == 0)
                                win = ch[i][j] == 'X' ? 1 : -1;
                            else if (win != (ch[i][j] == 'X' ? 1 : -1))
                                return false;
                        }
                    } else if (j == 1) {
                        if (ch[i][j] == ch[i][j-1] && ch[i][j] == ch[i][j+1]) {
                            if (win == 0)
                                win = ch[i][j] == 'X' ? 1 : -1;
                            else if (win != (ch[i][j] == 'X' ? 1 : -1))
                                return false;
                        }
                    }
                } 
            }
        }
        if (win == 0) {
            return x >= o && x-o <= 1;
        } else {
            return (win == 1 && x-o == 1) || (win == -1 && x == o);
        }
    }
}

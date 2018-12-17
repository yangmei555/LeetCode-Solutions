class Solution {
    public String pushDominoes(String dominoes) {
        char[] ch = dominoes.toCharArray();
        char pre = 'L';
        int index = 0, pos = -1;
        while (index < ch.length) {
            while (index < ch.length && ch[index] == '.')
                index++;
            char cur = index < ch.length ? ch[index] : 'R';
            if (pre == cur) {
                for (int i = pos+1; i < index; i++)
                    ch[i] = cur;
            } else if (pre == 'R' && cur == 'L') {
                for (int left = pos+1, right = index-1; left < right; left++, right--) {
                    ch[left] = 'R';
                    ch[right] = 'L';
                }
            }
            pre = cur;
            pos = index;
            index++;
        }
        return String.valueOf(ch);
    }
}


class Solution {
    public String pushDominoes(String dominoes) {
        char[] ch = dominoes.toCharArray();
        int[] dist = new int[ch.length];
        int x = Integer.MAX_VALUE;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == 'R') {
                x = 1;
            } else if (ch[i] == 'L') {
                x = Integer.MAX_VALUE;
            } else {
                dist[i] = x;
                if (x != Integer.MAX_VALUE)
                    x++;
            }
        }
        x = Integer.MAX_VALUE;
        for (int i = ch.length-1; i >= 0; i--) {
            if (ch[i] == 'L') {
                x = 1;
            } else if (ch[i] == 'R') {
                x = Integer.MAX_VALUE;
            } else {
                if (dist[i] < x)
                    ch[i] = 'R';
                else if (dist[i] > x)
                    ch[i] = 'L';
                if (x != Integer.MAX_VALUE)
                    x++;
            }
        }
        return String.valueOf(ch);
    }
}


// a verbose solution 
class Solution {
    public String pushDominoes(String dominoes) {
        char[] ch = dominoes.toCharArray();
        int index = -1;
        while (index < ch.length) {
            int start = index++;
            while (index < ch.length && ch[index] == '.')
                index++;
            if (start == -1) {
                if (index < ch.length && ch[index] == 'L') {
                    for (int i = 0; i < index; i++)
                        ch[i] = 'L';
                }
            } else if (index == ch.length) {
                if (ch[start] == 'R') {
                    for (int i = start + 1; i < ch.length; i++)
                        ch[i] = 'R';
                }
            } else {
                if (ch[start] == 'L' && ch[index] == 'L') {
                    for (int i = start + 1; i < index; i++)
                        ch[i] = 'L';
                } else if (ch[start] == 'R' && ch[index] == 'R') {
                    for (int i = start + 1; i < index; i++)
                        ch[i] = 'R';
                } else if (ch[start] == 'R' && ch[index] == 'L') {
                    for (int i = start + 1, j = index - 1; i < j; i++, j--) {
                        ch[i] = 'R';
                        ch[j] = 'L';
                    }
                }
            }
        }
        return new String(ch);
    }
}

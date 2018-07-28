class Solution {
    public String predictPartyVictory(String senate) {
        char[] ch = senate.toCharArray();
        Queue<Integer> queue1 = new LinkedList<>(), queue2 = new LinkedList<>();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == 'R')
                queue1.offer(i);
            else
                queue2.offer(i);
        }
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            int q1 = queue1.poll(), q2 = queue2.poll();
            if (q1 < q2)
                queue1.offer(q1 + ch.length);
            else
                queue2.offer(q2 + ch.length);
        }
        return queue1.isEmpty() ? "Dire" : "Radiant";
    }
}


class Solution {
    public String predictPartyVictory(String senate) {
        char[] ch = senate.toCharArray();
        int r = 0, d = 0;
        for (int c : ch) {
            if (c == 'R')
                r++;
            else
                d++;
        }
        int index = 0;
        while (r != 0 && d != 0) {
            if (ch[index] != 'B') {
                char c = ch[index] == 'R' ? 'D' : 'R';
                int pos = index;
                while (ch[pos] != c) 
                    pos = (pos + 1) % ch.length;
                ch[pos] = 'B';
                if (c == 'R')
                    r--;
                else
                    d--;
            }
            index = (index + 1) % ch.length;
        }
        return r != 0 ? "Radiant" : "Dire";
    }
}


public class Solution {
    public String predictPartyVictory(String senate) {
        char[] ch = senate.toCharArray();
        int len = 0, rminusd = 0;
        while (ch.length != len) {
            len = ch.length;
            StringBuilder sb = new StringBuilder();
            for (char c : ch) {
                if (c == 'R' && rminusd >= 0)
                    sb.append('R');
                if (c == 'D' && rminusd <= 0)
                    sb.append('D');
                rminusd = c == 'R' ? rminusd + 1 : rminusd - 1;
            }
            ch = sb.toString().toCharArray();
        }
        return ch[0] == 'R' ? "Radiant" : "Dire";
    }
}


class Solution {
    public String predictPartyVictory(String senate) {
        char[] ch = senate.toCharArray();
        Queue<Character> queue = new LinkedList<>();
        int d = 0, dban = 0, r = 0, rban = 0;
        for (char c : ch) {
            if (c == 'R')
                r++;
            else
                d++;
            queue.offer(c);
        }
        while (d != 0 && r != 0) {
            char c = queue.poll();
            if (c == 'R') {
                if (rban != 0) {
                    rban--;
                    r--;
                } else {
                    dban++;
                    queue.offer('R');
                }
            } else {
                if (dban != 0) {
                    dban--;
                    d--;
                } else {
                    rban++;
                    queue.offer('D');
                }
            }
        }
        return d == 0 ? "Radiant" : "Dire";
    }
}

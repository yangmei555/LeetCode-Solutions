class Solution {
    public int binaryGap(int N) {
        int res = 0, pre = -1;
        for (int i = 0; (1 << i) < N; i++) {
            if ((N & (1 << i)) != 0) {
                if (pre == -1) {
                    pre = i;
                } else {
                    res = res > i - pre ? res : i - pre;
                    pre = i;
                }
            }
        }
        return res;
    }
}


class Solution {
    public int binaryGap(int N) {
        int res = 0, pre = -1;
        for (int i = N, count = 0; i != 0; i >>= 1, count++) {
            if ((i & 1) != 0) {
                if (pre == -1) {
                    pre = count;
                } else {
                    res = res > count - pre ? res : count - pre;
                    pre = count;
                }
            }
        }
        return res;
    }
}


class Solution {
    public int binaryGap(int N) {
        int res = 0, pre = 32;
        char[] ch = Integer.toString(N, 2).toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '1') {
                res = res > i - pre ? res : i - pre;
                pre = i;
            }
        }
        return res;
    }
}

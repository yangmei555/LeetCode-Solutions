class Solution {
    public int[] shortestToChar(String S, char C) {
        char[] ch = S.toCharArray();
        int[] res = new int[ch.length];
        int dist = Integer.MAX_VALUE;
        for (int i = 0; i < res.length; i++) {
            if (C == ch[i])
                dist = 0;
            res[i] = dist;
            if (dist != Integer.MAX_VALUE)
                dist++;
        }
        dist = Integer.MAX_VALUE;
        for (int i = res.length-1; i >= 0; i--) {
            if (C == ch[i])
                dist = 0;
            res[i] = res[i] < dist ? res[i] : dist;
            if (dist != Integer.MAX_VALUE)
                dist++;
        }
        return res;
    }
}

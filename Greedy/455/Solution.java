class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = g.length-1, j = s.length-1, res = 0;
        while (i >= 0 && j >= 0) {
            if (g[i] <= s[j]) {
                j--;
                res++;
            }
            i--;
        }
        return res;
    }
}


class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0, res = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                i++;
                res++;
            }
            j++;
        }
        return res;
    }
}

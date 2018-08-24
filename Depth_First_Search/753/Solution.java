class Solution {
    public String crackSafe(int n, int k) {
        int count = 1;
        for (int i = 1; i < n; i++)
            count *= k;
        boolean[][] map = new boolean[count][k];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; i++)
            sb.append('0');
        helper(sb, map, 0, 0, count * k);
        return sb.toString();
    }
    
    public boolean helper(StringBuilder sb, boolean[][] map, int prefix, int index, int remain) {
        if (remain == 0)
            return true;
        int scale = map.length, k = map[0].length;
        int nextpre = index < sb.length() ? prefix * k - scale * (sb.charAt(index) - '0') : -1;
        for (int i = 0; i < k; i++) {
            if (!map[prefix][i]) {
                sb.append(i);
                map[prefix][i] = true;
                if (helper(sb, map, nextpre == -1 ? 0 : nextpre + i, index + 1, remain - 1))
                    return true;
                sb.deleteCharAt(sb.length()-1);
                map[prefix][i] = false;
            }
        }
        return false;
    }
}

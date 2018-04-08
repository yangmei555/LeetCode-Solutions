class Solution {
    public int repeatedStringMatch(String A, String B) {
        if (B == null || B.length() == 0)
            return 1;
        if (A == null || A.length() == 0)
            return -1;
        StringBuilder sb = new StringBuilder();
        int res = 0;
        while (true) {
            sb.append(A);
            res++;
            if (sb.length() >= B.length())
                break;
        }
        if (sb.toString().contains(B))
            return res;
        if (sb.append(A).toString().contains(B))
            return res + 1;
        
        return -1;
    }
}

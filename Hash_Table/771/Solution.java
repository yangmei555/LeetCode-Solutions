class Solution {
    public int numJewelsInStones(String J, String S) {
        int res = 0;
        char[] ch = S.toCharArray();
        for (int i = 0; i < ch.length; i++)
            if (J.contains(ch[i] + ""))
                res++;
        return res;
    }
}

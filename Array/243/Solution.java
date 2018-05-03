class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int n1 = -1, n2 = -1, res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1))
                n1 = i;
            if (words[i].equals(word2))
                n2 = i;
            if (n1 != -1 && n2 != -1)
                res = Math.min(res, Math.abs(n1-n2));
        }
        return res;
    }
}

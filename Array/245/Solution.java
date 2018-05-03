class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int index = -1, res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1) || words[i].equals(word2)) {
                if (index != -1 && (!words[index].equals(words[i]) || (word1.equals(word2)))) {
                    res = res < i - index ? res : i - index;
                }
                if (res == 1)
                    return 1;
                index = i;
            }
        }
        return res;
    }
}

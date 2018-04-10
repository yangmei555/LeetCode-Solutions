class Solution {
    public String[] findWords(String[] words) {
        int[] index = {2, 3, 3, 2, 1, 2, 2, 2, 1, 2, 2, 2, 3, 3, 1, 1, 1, 1, 2, 1, 1, 3, 1, 3, 1, 3};
        String[] res = new String[words.length];
        int inindex = 0;
        for (String s : words) {
            int in = -1;
            for (char c : s.toLowerCase().toCharArray()) {
                if (in == -1)
                    in = index[c - 'a'];
                else if (index[c - 'a'] != in) {
                    in = -1;
                    break;
                }
            }
            if (in != -1) {
                res[inindex] = s;
                inindex++;
            }
        }
        return Arrays.copyOf(res, inindex);
    }
}

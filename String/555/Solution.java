class Solution {
    public String splitLoopedString(String[] strs) {
        char max = 'a';
        StringBuilder front = new StringBuilder(), back = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            if (new StringBuilder(strs[i]).reverse().toString().compareTo(strs[i]) > 0)
                strs[i] = new StringBuilder(strs[i]).reverse().toString();
            for (char c : strs[i].toCharArray())
                max = (char)(Math.max(max, c));
            back.append(strs[i]);
        }
        String res = "";
        for (int i = 0; i < strs.length; i++) {
            back.delete(0, strs[i].length());
            for (int j = 0; j < strs[i].length(); j++) {
                if (max == strs[i].charAt(j)) {
                    String reverse = new StringBuilder(strs[i]).reverse().toString();
                    String cand1 = new StringBuilder(strs[i].substring(j)).append(back)
                                .append(front).append(strs[i].substring(0, j)).toString();
                    String cand2 = new StringBuilder(reverse.substring(reverse.length() - j - 1))
                                .append(back).append(front)
                                .append(reverse.substring(0, reverse.length() - j - 1)).toString();
                    if (res.compareTo(cand1) < 0)
                        res = cand1;
                    if (res.compareTo(cand2) < 0)
                        res = cand2;
                }
            }
            front.append(strs[i]);
        }
        return res;
    }
}

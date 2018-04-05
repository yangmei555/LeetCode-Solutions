class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> list = new LinkedList<>();
        if (s == null || s.length() == 0)
            return list;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i+1) == '+')
                list.add(s.substring(0, i) + "--" + (i == s.length()-2 ? "" : s.substring(i+2)));
        }
        return list;
    }
}

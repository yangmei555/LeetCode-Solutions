class Solution {
    public List<String> letterCasePermutation(String S) {
        StringBuilder sb = new StringBuilder(S);
        List<String> list = new LinkedList<>();
        helper(list, sb, 0);
        return list;
    }
    public void helper(List<String> list, StringBuilder sb, int index) {
        if (index == sb.length()) {
            list.add(sb.toString());
            return;
        }
        helper(list, sb, index + 1);
        if (Character.isUpperCase(sb.charAt(index))) {
            sb.setCharAt(index, Character.toLowerCase(sb.charAt(index)));
            helper(list, sb, index + 1);
        } else if (Character.isLowerCase(sb.charAt(index))) {
            sb.setCharAt(index, Character.toUpperCase(sb.charAt(index)));
            helper(list, sb, index + 1);
        }
    }
}

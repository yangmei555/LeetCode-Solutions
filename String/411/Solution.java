class Solution {
    public String minAbbreviation(String target, String[] dictionary) {
        Set<Integer> set = new HashSet<>();
        char[] ch = target.toCharArray();
        for (String str : dictionary) {
            if (str.length() == ch.length) {
                int index = 0, record = 0;
                for (char c : str.toCharArray()) {
                    if (ch[index] == c) 
                        record |= (1 << index);
                    index++;
                }
                set.add(record);
            }
        }
        if (set.isEmpty())
            return ch.length + "";
        for (int i = 1; i <= ch.length-2; i++) {
            List<String> list = new LinkedList<>();
            helper(ch, 0, 0, 0, i, list, set);
            if (!list.isEmpty()) {
                String res = "";
                for (String str : list) {
                    if (res.equals("") || res.length() > str.length())
                        res = str;
                }
                return res;
            }
        }
        return target;
    }
    
    public void helper(char[] ch, int index, int mark, int count, int total, 
                                                    List<String> list, Set<Integer> set) {
        if (count == total) {
            boolean flag = true;
            for (int s : set) {
                if ((s | mark) == s) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                StringBuilder sb = new StringBuilder();
                int pre = -1;
                for (int i = 0; i <= ch.length; i++) {
                    if (i == ch.length || ((1 << i) & mark) != 0) {
                        if (i - pre - 1 != 0)
                            sb.append(i-pre-1);
                        if (i != ch.length)
                            sb.append(ch[i]);
                        pre = i;
                    }
                }
                list.add(sb.toString());
            }
        } else if (index != ch.length) {
            helper(ch, index+1, mark | (1 << index), count+1, total, list, set);
            if (ch.length - index - 1 >= total - count)
                helper(ch, index+1, mark, count, total, list, set);
        }
    }
}

class Solution {
    public List<String> generateAbbreviations(String word) {
        char[] ch = word.toCharArray();
        boolean[] abbr = new boolean[ch.length];
        List<String> res = new LinkedList<>();
        helper(ch, abbr, 0, res);
        return res;
    }
    
    public void helper(char[] ch, boolean[] abbr, int index, List<String> res) {
        if (index == ch.length) {
            StringBuilder sb = new StringBuilder();
            int cons = 0;
            for (int i = 0; i < abbr.length; i++) {
                if (abbr[i]) {
                    cons++;
                } else {
                    if (cons != 0)
                        sb.append(cons);
                    sb.append(ch[i]);
                    cons = 0;
                }
            }
            if (cons != 0)
                sb.append(cons);
            res.add(sb.toString());
        } else {
            helper(ch, abbr, index+1, res);
            abbr[index] = true;
            helper(ch, abbr, index+1, res);
            abbr[index] = false;
        }
    }
}


class Solution {
    public List<String> generateAbbreviations(String word) {
        char[] ch = word.toCharArray();
        if (ch.length == 0)
            return Arrays.asList("");
        return helper(ch, 0);
    }
    
    public List<String> helper(char[] ch, int index) {
        if (index == ch.length-1) {
            List<String> res = new ArrayList<>(2);
            res.add(ch[index] + "");
            res.add(1 + "");
            return res;
        } else {
            List<String> res = new ArrayList<>(), next = helper(ch, index+1);
            int size = next.size();
            for (int i = 0; i < size; i++)
                res.add(ch[index] + next.get(i));
            for (int i = 0; i < size/2; i++)
                res.add('1' + next.get(i));
            for (int i = size/2; i < size; i++) {
                int num = 0, j = 0;
                String str = next.get(i);
                while (j < str.length() && str.charAt(j) >= '0' && str.charAt(j) <= '9') {
                    num = num * 10 + str.charAt(j) - '0';
                    j++;
                }
                res.add(num + 1 + str.substring(j, str.length()));
            }   
            return res;
        }
    }
}


class Solution {
    public List<String> generateAbbreviations(String word) {
        char[] ch = word.toCharArray();
        boolean[] abbr = new boolean[ch.length];
        List<String> res = new LinkedList<>();
        for (int i = 0; i < (1 << ch.length); i++)
            res.add(helper(ch, i));
        return res;
    }
    
    public String helper(char[] ch, int mask) {
        StringBuilder sb = new StringBuilder();
        int cons = 0;
        for (int i = 0; i < ch.length; i++) {
            if ((mask & 1) == 0) {
                cons++;
            } else {
                if (cons != 0)
                    sb.append(cons);
                sb.append(ch[i]);
                cons = 0;
            }
            mask >>= 1;
        }
        if (cons != 0)
            sb.append(cons);
        return sb.toString();
    }
}


class Solution {
    public List<String> generateAbbreviations(String word) {
        char[] ch = word.toCharArray();
        StringBuilder sb = new StringBuilder();
        List<String> res = new LinkedList<>();
        helper(ch, 0, 0, sb, res);
        return res;
    }
    
    public void helper(char[] ch, int abbrs, int index, StringBuilder sb, List<String> res) {
        int len = sb.length();
        if (index == ch.length) {
            if (abbrs != 0)
                sb.append(abbrs);
            res.add(sb.toString());
            sb.setLength(len);
        } else {
            helper(ch, abbrs+1, index+1, sb, res);
            if (abbrs != 0)
                sb.append(abbrs);
            sb.append(ch[index]);
            helper(ch, 0, index+1, sb, res);
            sb.setLength(len);
        }
    }
}

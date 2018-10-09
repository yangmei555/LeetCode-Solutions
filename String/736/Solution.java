class Solution {
    int index = 0;
    public int evaluate(String expression) {
        char[] ch = expression.toCharArray();
        return helper(ch, new HashMap<String, Integer>());
    }
    
    public int helper(char[] ch, Map<String, Integer> map) {
        if (ch[index] != '(') {
            StringBuilder sb = new StringBuilder();
            while (index < ch.length && ch[index] != ' ' && ch[index] != ')')
                sb.append(ch[index++]);
            if (sb.charAt(0) >= '0' && sb.charAt(0) <= '9' || sb.charAt(0) == '-')
                return Integer.valueOf(sb.toString());
            else
                return map.get(sb.toString());
        } else {
            index++;
            StringBuilder sb = new StringBuilder();
            while (ch[index] != ' ')
                sb.append(ch[index++]);
            index++;
            if (!sb.toString().equals("let")) {
                int v1 = helper(ch, new HashMap<String, Integer>(map));
                index++;
                int v2 = helper(ch, new HashMap<String, Integer>(map));
                index++;
                if (sb.toString().equals("add"))
                    return v1 + v2;
                else
                    return v1 * v2;
            } else {
                int res = -1;
                while (true) {
                    if (ch[index] == '(') {
                        res = helper(ch, new HashMap<String, Integer>(map));
                        index++;
                        break;
                    }
                    sb.setLength(0);
                    while (ch[index] != ' ' && ch[index] != ')')
                        sb.append(ch[index++]);
                    if (ch[index] == ')') {
                        index++;
                        if (sb.charAt(0) >= '0' && sb.charAt(0) <= '9' || sb.charAt(0) == '-')
                            res = Integer.valueOf(sb.toString());
                        else
                            res = map.get(sb.toString());
                        break;
                    } else {
                        index++;
                    }    
                    map.put(sb.toString(), helper(ch, new HashMap<String, Integer>(map)));
                    index++;
                }
                return res;
            }
        }
    }
}

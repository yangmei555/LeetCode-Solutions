class Solution {
    public List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {
        System.out.println(expression.length());
        Map<String, Integer> eval = new HashMap<>();
        for (int i = 0; i < evalvars.length; i++)
            eval.put(evalvars[i], evalints[i]);
        char[] ch = expression.toCharArray();
        Map<String, Integer> map = helper(ch, eval);
        Map<String, Integer> formalMap = new HashMap<>();
        for (String str : map.keySet()) {
            if (!str.equals("1")) {
                String[] strs = str.split("\\*");
                Arrays.sort(strs);
                StringBuilder sb = new StringBuilder();
                for (String s : strs)
                    sb.append('*').append(s);
                String lexicoKey = sb.substring(1);
                formalMap.put(lexicoKey, formalMap.getOrDefault(lexicoKey, 0) + map.get(str));
                if (formalMap.get(lexicoKey) == 0)
                    formalMap.remove(lexicoKey);
            }
        }
        List<String> keyList = new LinkedList<>();
        for (String str : formalMap.keySet())
            keyList.add(str);
        Collections.sort(keyList, new Comparator<String>() {
            public int compare(String str1, String str2) {
                String[] s1 = str1.split("\\*");
                String[] s2 = str2.split("\\*");
                if (s1.length != s2.length) {
                    return s2.length - s1.length;
                } else {
                    return str1.compareTo(str2);
                }
            }
        });
        List<String> res = new LinkedList<>();
        for (String str : keyList) 
            res.add(formalMap.get(str) + "*" + str);
        if (map.containsKey("1") && map.get("1") != 0) 
            res.add(map.get("1") + "");
        return res;
    }
    int index = 0;
    public Map<String, Integer> helper(char[] ch, Map<String, Integer> eval) {
        Map<String, Integer>[] stack = new Map[ch.length];
        int stackIndex = 0;
        char op = '+';
        StringBuilder sb = new StringBuilder();
        boolean isNum = false;
        Map<String, Integer> term = null;
        while (index <= ch.length) {
            if (index != ch.length && ch[index] >= '0' && ch[index] <= '9') {
                isNum = true;
                sb.append(ch[index]);
            } else if (index != ch.length && ch[index] >= 'a' && ch[index] <= 'z') {
                isNum = false;
                sb.append(ch[index]);
            } else if (index == ch.length || ch[index] != ' ' && ch[index] != '(') {
                if (term == null) {
                    term = new HashMap<>();
                    if (isNum) {
                        int value = Integer.valueOf(sb.toString());
                        term.put("1", value);
                    } else {
                        if (eval.containsKey(sb.toString())) {
                            int value = eval.get(sb.toString());
                            term.put("1", value);
                        } else {
                            term.put(sb.toString(), 1);
                        }
                    }
                }
                if (op != '*') {
                    if (op == '-') {
                        for (String str : term.keySet())
                            term.put(str, -1 * term.get(str));
                    }
                    stack[stackIndex++] = term;
                } else {
                    stack[stackIndex-1] = mul(stack[stackIndex-1], term);
                }
                if (index == ch.length || ch[index] == ')')
                    break;
                op = ch[index];
                term = null;
                sb.setLength(0);
            } else if (ch[index] == '(') {
                index++;
                term = helper(ch, eval);
            } 
            index++;
        }
        Map<String, Integer> res = new HashMap<>();
        while (stackIndex != 0) {
            stackIndex--;
            Map<String, Integer> addTerm = stack[stackIndex];
            for (String str : addTerm.keySet()) {
                res.put(str, res.getOrDefault(str, 0) + addTerm.get(str));
                if (res.get(str) == 0)
                    res.remove(str);
            }
        }
        return res;
    }
    
    public Map<String, Integer> mul(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> res = new HashMap<>();
        for (String str1 : map1.keySet()) {
            int v1 = map1.get(str1);
            for (String str2 : map2.keySet()) {
                int v2 = map2.get(str2);
                String key;
                if (str1.equals("1")) {
                    key = str2;
                } else if (str2.equals("1")) {
                    key = str1;
                } else {
                    key = str1 + "*" + str2;
                }
                res.put(key, res.getOrDefault(key, 0) + v1 * v2);
                if (res.get(key) == 0)
                    res.remove(key);
            }
        }
        return res;
    }
}

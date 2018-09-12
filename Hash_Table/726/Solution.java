class Solution {
    int index;
    public String countOfAtoms(String formula) {
        char[] ch = formula.toCharArray();
        index = 0;
        Map<String, Integer> map = helper(ch);
        StringBuilder sb = new StringBuilder();
        for (String str : map.keySet()) {
            sb.append(str);
            if (map.get(str) != 1)
                sb.append(map.get(str));
        }
        return sb.toString();
    }
    
    public Map<String, Integer> helper(char[] ch) {
        Map<String, Integer> map = new TreeMap<>();
        while (index < ch.length) {
            if (ch[index] >= 'A' && ch[index] <= 'Z') {
                int temp = index++;
                while (index < ch.length && ch[index] >= 'a' && ch[index] <= 'z')
                    index++;
                String element = new String(ch, temp, index - temp);
                int count = 0;
                while (index < ch.length && ch[index] >= '0' && ch[index] <= '9') 
                    count = count * 10 + ch[index++] - '0';
                count = count == 0 ? 1 : count;
                map.put(element, map.getOrDefault(element, 0) + count);
            } else if (ch[index] == '(') {
                index++;
                Map<String, Integer> ret = helper(ch);
                int mul = 0;
                while (index < ch.length && ch[index] >= '0' && ch[index] <= '9')
                    mul = mul * 10 + ch[index++] - '0';
                mul = mul == 0 ? 1 : mul;
                for (String str : ret.keySet())
                    map.put(str, map.getOrDefault(str, 0) + ret.get(str) * mul);
            } else if (ch[index] == ')') { 
                index++;
                return map;
            }
        }
        return map;
    }
}

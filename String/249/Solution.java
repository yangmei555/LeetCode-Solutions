class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            char[] ch = str.toCharArray();
            int diff = ch[0] - 'a';
            for (int i = 0; i < ch.length; i++) 
                ch[i] = (char)('a' + (ch[i]-'a'-diff+26)%26);
            String s = new String(ch);
            map.putIfAbsent(s, new ArrayList<>());
            map.get(s).add(str);
        }
        List<List<String>> res = new ArrayList<>();
        for (String str : map.keySet())
            res.add(map.get(str));
        return res;
    }
}


class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        Map<Integer, List<String>> map = new HashMap<>();
        for (String str : strings) {
            char[] ch = str.toCharArray();
            int diff = ch[0] - 'a';
            for (int i = 0; i < ch.length; i++) 
                ch[i] = (char)('a' + (ch[i]-'a'-diff+26)%26);
            map.putIfAbsent(Arrays.hashCode(ch), new ArrayList<>());
            map.get(Arrays.hashCode(ch)).add(str);
        }
        List<List<String>> res = new ArrayList<>();
        for (Integer str : map.keySet())
            res.add(map.get(str));
        return res;
    }
}

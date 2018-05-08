class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        int res = 0;
        for (String w : words) {
            char[] ch = w.toCharArray();
            int i = 0, index = 0;
            while (i < ch.length) {
                index = S.indexOf(ch[i], index);
                if (index == -1)
                    break;
                index++;
                i++;
            }
            if (index != -1)
                res++;
        }
        return res;
    }
}


class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        Map<Character, List<Integer>> map = new HashMap<>();
        char[] s = S.toCharArray();
        for (int i = 0; i < s.length; i++) {
            map.putIfAbsent(s[i], new ArrayList<>());
            map.get(s[i]).add(i);
        }
        int res = 0, temp = 0;
        for (String w : words) {
            char[] ch = w.toCharArray();
            int index = 0, i = 0;
            for (; i < ch.length; i++) {
                if (!map.containsKey(ch[i]))
                    break;
                List<Integer> list = map.get(ch[i]);
                temp = index;
                for (int l : list)
                    if (l >= index) {
                        index = l + 1;
                        break;
                    }
                if (temp == index)
                    break;
            }
            if (i == ch.length)
                res++;
        }
        return res;
    }
}

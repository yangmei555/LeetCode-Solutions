class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        int res = 0;
        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            if (map.containsKey(w)) {
                res += map.get(w);
                continue;
            }
            char[] ch = w.toCharArray();
            int i = 0, index = 0;
            while (i < ch.length) {
                index = S.indexOf(ch[i], index);
                if (index == -1)
                    break;
                index++;
                i++;
            }
            if (index != -1) {
                res++;
                map.put(w, 1);
            } else {
                map.put(w, 0);
            }
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


class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        List<Wrapper>[] list = new List[26];
        for (int i = 0; i < list.length; i++)
            list[i] = new ArrayList<>();
        for (String w : words) {
            list[w.charAt(0)-'a'].add(new Wrapper(w, 0));
        }
        int res = 0;
        for (char c : S.toCharArray()) {
            List<Wrapper> l = list[c-'a'];
            int size = l.size();
            for (int i = 0; i < size; i++) {
                Wrapper w = l.get(0);
                if (w.index != w.ch.length-1) {
                    w.index++;
                    list[w.ch[w.index]-'a'].add(w);
                } else {
                    res++;
                }
                l.remove(0);
            }
        }
        return res;
    }
    
    class Wrapper {
        char[] ch;
        int index;
        public Wrapper(String s, int index) {
            this.ch = s.toCharArray();
            this.index = index;
        }
    }
}

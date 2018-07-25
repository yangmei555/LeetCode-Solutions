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
        List<Integer>[] map = new List[26];
        char[] s = S.toCharArray();
        for (int i = 0; i < s.length; i++) {
            if (map[s[i]-'a'] == null)
                map[s[i]-'a'] = new ArrayList<>();
            map[s[i]-'a'].add(i);
        }
        int res = 0, temp = 0;
        for (String w : words) {
            char[] ch = w.toCharArray();
            int index = 0, i = 0;
            for (; i < ch.length; i++) {
                if (map[ch[i]-'a'] == null)
                    break;
                List<Integer> list = map[ch[i]-'a'];
                int pos = Collections.binarySearch(list, index);
                pos = pos >= 0 ? pos : - pos - 1;
                if (pos == list.size())
                    break;
                else
                    index = list.get(pos) + 1;
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
            list[i] = new LinkedList<>();
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


class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        List<Node>[] map = new List[26];
        for (int i = 0; i < words.length; i++) {
            if (map[words[i].charAt(0)-'a'] == null)
                map[words[i].charAt(0)-'a'] = new ArrayList<>();
            map[words[i].charAt(0)-'a'].add(new Node(i, 0));
        }
        int res = 0;
        for (int i = 0; i < S.length(); i++) {
            if (map[S.charAt(i)-'a'] == null)
                continue;
            List<Node> list = new ArrayList<>(map[S.charAt(i)-'a']);
            map[S.charAt(i)-'a'] = null;
            for (Node n : list) {
                n.index++;
                if (n.index == words[n.i].length())
                    res++;
                else {
                    if (map[words[n.i].charAt(n.index)-'a'] == null)
                        map[words[n.i].charAt(n.index)-'a'] = new ArrayList<>();
                    map[words[n.i].charAt(n.index)-'a'].add(n);
                }
            }
        }
        return res;
    }
    
    class Node {
        int i, index;
        public Node(int i, int index) {
            this.i = i;
            this.index = index;
        }
    }
}

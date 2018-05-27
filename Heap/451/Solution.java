class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) 
            map.put(c, map.getOrDefault(c, 0) + 1);
        List<Character>[] index = new List[s.length() + 1];
        for (char c : map.keySet()) {
            int freq = map.get(c);
            if (index[freq] == null)
                index[freq] = new LinkedList<>();
            index[freq].add(c);
        }
        StringBuilder res = new StringBuilder();
        for (int i = index.length-1; i >= 0; i--) {
            if (index[i] != null) {
                for (char c : index[i])
                    for (int j = 0; j < i; j++)
                        res.append(c);
            }
        }
        return res.toString();
    }
}


class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) 
            map.put(c, map.getOrDefault(c, 0) + 1);
        PriorityQueue<Elem> queue = new PriorityQueue<>(new Comparator<Elem>() {
            public int compare(Elem e1, Elem e2) {
                return e2.freq - e1.freq;
            }
        });
        for (char c : map.keySet()) 
            queue.offer(new Elem(c, map.get(c)));
        StringBuilder res = new StringBuilder();
        while (!queue.isEmpty()) {
            Elem e = queue.poll();
            for (int i = 0; i < e.freq; i++)
                res.append(e.c);
        }
        return res.toString();
    }
    
    class Elem {
        char c;
        int freq;
        public Elem(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }
    }
}


class Solution {
    public String frequencySort(String s) {
        int[] map = new int[128];
        for (char c : s.toCharArray()) 
            map[c]++;
        List<Character>[] index = new List[s.length() + 1];
        for (int i = 0; i < map.length; i++) {
            if (map[i] != 0) {
                if (index[map[i]] == null)
                    index[map[i]] = new LinkedList<>();
                index[map[i]].add((char)i);
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = index.length-1; i >= 0; i--) {
            if (index[i] != null) {
                for (char c : index[i])
                    for (int j = 0; j < i; j++)
                        res.append(c);
            }
        }
        return res.toString();
    }
}


class Solution {
    public String frequencySort(String s) {
        int[] map = new int[128];
        for (char c : s.toCharArray()) 
            map[c]++;
        StringBuilder res = new StringBuilder();
        int index = 0;
        while (index < s.length()) {
            int cand = 0;
            for (int i = 1; i < map.length; i++)
                if (map[i] > map[cand])
                    cand = i;
            for (int i = 0; i < map[cand]; i++)
                res.append((char)cand);
            index += map[cand];
            map[cand] = 0;
        }
        return res.toString();
    }
}

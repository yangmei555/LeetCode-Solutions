class WordDistance {
    HashMap<String, List<Integer>> map;
    int len;
    public WordDistance(String[] words) {
        map = new HashMap<>();
        len = words.length;
        for (int i = 0; i < words.length; i++) {
            List<Integer> list;
            if (map.containsKey(words[i])) {
                list = map.get(words[i]);
            } else {
                list = new ArrayList<>();
                map.put(words[i], list);
            }
            list.add(i);
        }
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> list1 = map.get(word1), list2 = map.get(word2);
        int res = Integer.MAX_VALUE;
        List<Integer> list = new ArrayList<>(list1);
        list.addAll(list2);
        Collections.sort(list);
        for (int i = 1; i < list.size(); i++) {
            if (((list1.contains(list.get(i)) && list2.contains(list.get(i-1))) || 
                 (list2.contains(list.get(i)) && list1.contains(list.get(i-1)))) && 
                    list.get(i)-list.get(i-1) < res)
                res = list.get(i) - list.get(i-1);
            if (res == 1)
                return 1;
        }
        return res;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */


class WordDistance {
    HashMap<String, List<Integer>> map;
    public WordDistance(String[] words) {
        map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            List<Integer> list;
            if (map.containsKey(words[i])) {
                list = map.get(words[i]);
            } else {
                list = new ArrayList<>();
                map.put(words[i], list);
            }
            list.add(i);
        }
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> list1 = map.get(word1), list2 = map.get(word2);
        int res = Integer.MAX_VALUE;
        int i = 0, j = 0, num1, num2;
        while (i < list1.size() && j < list2.size()) {
            num1 = list1.get(i);
            num2 = list2.get(j);
            if (num1 < num2) {
                res = res < num2 - num1 ? res : num2 - num1;
                i++; 
            } else {
                res = res < num1 - num2 ? res : num1 - num2;
                j++;
            }
            if (res == 1)
                return 1;
        }
        return res;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */

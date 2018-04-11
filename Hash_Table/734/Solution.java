class Solution {
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length)
            return false;
        HashMap<String, Set<String>> map = new HashMap<>();
        for (String[] str : pairs) {
            Set<String> set;
            if (map.containsKey(str[0]))
                set = map.get(str[0]);
            else {
                set = new HashSet<>();
                map.put(str[0], set);
            }
            set.add(str[1]);
            if (map.containsKey(str[1]))
                set = map.get(str[1]);
            else {
                set = new HashSet<>();
                map.put(str[1], set);
            }
            set.add(str[0]);
        }
        for (int i = 0; i < words1.length; i++) {
            if (!words1[i].equals(words2[i])) {
                if (map.get(words1[i]) == null || map.get(words1[i]).contains(words2[i]) == false)
                    return false;
            }
        }
        return true;
    }
}


class Solution {
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length)
            return false;
        Set<String> set = new HashSet<>();
        for (String[] str : pairs)
            set.add(str[0] + " " + str[1]);
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i]) == false) {
                if (set.contains(words1[i] + " " + words2[i]) == false && 
                    set.contains(words2[i] + " " + words1[i]) == false)
                    return false;
            }
        }
        return true;
    }
}

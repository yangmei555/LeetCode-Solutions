class Solution {
    public String rearrangeString(String s, int k) {
        char[] ch = s.toCharArray();
        int[] map = new int[26];
        int max = 0;
        Set<Character> set = new HashSet<>();
        for (char c : ch) {
            map[c-'a']++;
            if (map[c-'a'] > max) {
                max = map[c-'a'];
                set.clear();
                set.add(c);
            } else if (map[c-'a'] == max) {
                set.add(c);
            }
        }
        int availableSlots = (max - 1) * (k - set.size());
        int totalNeed = ch.length - max * set.size();
        if (availableSlots > totalNeed)
            return "";
        StringBuilder temp = new StringBuilder();
        for (char c : set)
            temp.append(c);
        StringBuilder[] sb = new StringBuilder[max];
        for (int i = 0; i < sb.length; i++) 
            sb[i] = new StringBuilder(temp.toString());
        int index = 0;
        for (int i = 0; i < map.length; i++) {
            char c = (char)('a' + i);
            if (set.contains(c))
                continue;
            while (map[i]-- > 0) {
                sb[index].append(c);
                index = (index + 1) % (sb.length-1);
            }
        }
        for (int i = 1; i < sb.length; i++) 
            sb[0].append(sb[i]);
        return sb[0].toString();
    }
}

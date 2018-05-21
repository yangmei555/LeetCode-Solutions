class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> map = new HashMap<>();
        String[] strings = paragraph.split(" ");
        Set<String> set = new HashSet<>();
        for (String s : banned)
            set.add(s);
        int max = 0;
        String res = "";
        for (String s : strings) {
            s = s.toLowerCase();
            if (s.length() != 0 && !(s.charAt(s.length()-1) >= 'a' && s.charAt(s.length()-1) <= 'z'))
                s = s.substring(0, s.length()-1);
            if (s.length() == 0 || set.contains(s))
                continue;
            int fre = map.getOrDefault(s, 0);
            map.put(s, fre + 1);
            if (fre + 1 > max) {
                max = fre + 1;
                res = s;
            }
        }
        return res;
    }
}


class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (String s : banned)
            set.add(s);
        int max = 0;
        String res = "";
        StringBuilder sb = new StringBuilder();
        for (char c : paragraph.toCharArray()) {
            c = Character.toLowerCase(c);
            if (c >= 'a' && c <= 'z') {
                sb.append(c);
            } else {
                if (sb.length() != 0 && !set.contains(sb.toString())) {
                    int fre = map.getOrDefault(sb.toString(), 0);
                    map.put(sb.toString(), fre + 1);
                    if (fre + 1 > max) {
                        max = fre + 1;
                        res = sb.toString();
                    }
                }
                sb.setLength(0);
            }
        }
        if (sb.length() != 0 && !set.contains(sb.toString())) {
            int fre = map.getOrDefault(sb.toString(), 0);
            map.put(sb.toString(), fre + 1);
            if (fre + 1 > max) {
                max = fre + 1;
                res = sb.toString();
            }
        }
        return res;
    }
}

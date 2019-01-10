class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new LinkedList<>();
        if (words.length == 0)
            return res;
        Map<String, Integer> map = new HashMap<>();
        for (String str : words)
            map.put(str, map.getOrDefault(str, 0) + 1);
        int length = s.length(), len = words[0].length();
        for (int i = 0; i + len * words.length <= length; i++) {
            Map<String, Integer> temp = new HashMap<>(map);
            for (int j = i; j < i + len * words.length; j += len) {
                String str = s.substring(j, j + len);
                if (!temp.containsKey(str))
                    break;
                int v = temp.get(str);
                if (v == 1)
                    temp.remove(str);
                else
                    temp.put(str, v - 1);
            }
            if (temp.isEmpty())
                res.add(i);
        }
        return res;
    }
}


class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new LinkedList<>();
        if (words.length == 0)
            return res;
        Map<String, Integer> map = new HashMap<>();
        for (String str : words)
            map.put(str, map.getOrDefault(str, 0) + 1);
        int len = words[0].length();
        for (int i = 0; i < len; i++) {
            int left = i, right = i, count = 0;
            Map<String, Integer> temp = new HashMap<>();
            while (right + len <= s.length()) {
                String str = s.substring(right, right + len);
                temp.put(str, temp.getOrDefault(str, 0) + 1);
                while (temp.getOrDefault(str, 0) > map.getOrDefault(str, 0)) {
                    String _str = s.substring(left, left + len);
                    temp.put(_str, temp.get(_str) - 1);
                    left += len;
                }
                right += len;
                if (right - left == len * words.length)
                    res.add(left);
            }
        }
        return res;
    }
}

class Solution {
    public String findLongestWord(String s, List<String> d) {
        String res = "";
        char[] ch = s.toCharArray();
        for (String str : d) {
            if (str.length() >= res.length() && str.length() <= ch.length) {
                if (helper(ch, str.toCharArray())) {
                    if (str.length() > res.length() || str.compareTo(res) < 0)
                        res = str;
                }
            }
        }
        return res;
    }
    
    public boolean helper(char[] ch1, char[] ch2) {
        int index = 0;
        for (char c : ch1) {
            if (c == ch2[index]) {
                index++;
                if (index == ch2.length)
                    return true;
            }
        }
        return false;
    }
}


class Solution {
    public String findLongestWord(String s, List<String> d) {
        char[] ch = s.toCharArray();
        d.sort(new Comparator<String>() {
            public int compare(String s1, String s2) {
                if (s1.length() == s2.length()) {
                    return s1.compareTo(s2);
                } else {
                    return s2.length() - s1.length();
                }
            } 
        });
        for (String str : d) {
            if (str.length() <= ch.length && helper(ch, str.toCharArray())) 
                return str;
        }
        return "";
    }
    
    public boolean helper(char[] ch1, char[] ch2) {
        int index = 0;
        for (char c : ch1) {
            if (c == ch2[index]) {
                index++;
                if (index == ch2.length)
                    return true;
            }
        }
        return false;
    }
}


class Solution {
    public String findLongestWord(String s, List<String> d) {
        char[] ch = s.toCharArray();
        int[][] map = new int[ch.length+1][26];
        for (int i = ch.length-1; i >= 0; i--) {
            for (int j = 0; j < 26; j++)
                map[i][j] = map[i+1][j];
            map[i][ch[i]-'a'] = i+1;
        }
        String res = "";
        for (String str : d) {
            if (str.length() <= ch.length && str.length() >= res.length()) {
                char[] temp = str.toCharArray();
                int index = 0;
                for (int i = 0; i < temp.length; i++) {
                    index = map[index][temp[i]-'a'];
                    if (index == 0)
                        break;
                }
                if (index != 0 && (str.length() > res.length() || str.compareTo(res) < 0))
                    res = str;
            }
        }
        return res;
    }
}


// using the waitFor list, time complexity O(s.length + sum(string.length) where string is in d) 
class Solution {
    public String findLongestWord(String s, List<String> d) {
        List<int[]>[] waitFor = new List[26];
        for (int i = 0; i < waitFor.length; i++)
            waitFor[i] = new LinkedList<>();
        char[][] ch = new char[d.size()][];
        for (int i = 0; i < ch.length; i++)
            ch[i] = d.get(i).toCharArray();
        for (int i = 0; i < ch.length; i++) 
            waitFor[ch[i][0]-'a'].add(new int[]{i, 0});
        String str = "";
        for (char c : s.toCharArray()) {
            List<int[]> temp = new LinkedList<>(waitFor[c-'a']);
            waitFor[c-'a'] = new LinkedList<>();
            for (int[] pair : temp) {
                pair[1]++;
                if (pair[1] == ch[pair[0]].length) {
                    if (pair[1] > str.length() || 
                        pair[1] == str.length() && str.compareTo(d.get(pair[0])) > 0)
                        str = d.get(pair[0]);
                } else {
                    waitFor[ch[pair[0]][pair[1]]-'a'].add(pair);
                }
            }
        }
        return str;
    }
}

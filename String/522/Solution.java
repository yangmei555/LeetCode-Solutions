class Solution {
    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, new Comparator<String>() {
            public int compare(String s1, String s2) {
                if (s1.length() == s2.length()) 
                    return s1.compareTo(s2);
                else
                    return s2.length() - s1.length();
            }    
        });
        if (!strs[0].equals(strs[1]))
            return strs[0].length();
        int i = 1;
        while (i < strs.length) {
            while (i != strs.length && strs[i].equals(strs[i-1])) {
                strs[i-1] = null;
                i++;
            }
            if (i < strs.length-1 && strs[i].equals(strs[i+1])) {
                i++;
                continue;
            } else if (i != strs.length) {
                int j = i-1;
                for (; j >= 0; j--)
                    if (strs[j] != null && isSub(strs[i], strs[j]))
                        break;
                if (j == -1)
                    return strs[i].length();
            }
            i++;
        }
        return -1;
    }
    
    public boolean isSub(String s1, String s2) {
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int index = 0;
        for (int i = 0; i < ch2.length; i++)
            if (ch1[index] == ch2[i]) {
                index++;
                if (index == ch1.length)
                    return true;
            }
        return false;
    }
}


class Solution {
    public int findLUSlength(String[] strs) {
        int res = -1;
        for (int i = 0; i < strs.length; i++) {
            int j = 0;
            for (; j < strs.length; j++) 
                if (i != j && (strs[i].equals(strs[j]) || 
                        (strs[i].length()<strs[j].length()&&isSub(strs[i], strs[j]))))
                    break;
            if (j == strs.length)
                res = res > strs[i].length() ? res : strs[i].length();
        }
        return res;
    }
    
    public boolean isSub(String s1, String s2) {
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int index = 0;
        for (int i = 0; i < ch2.length; i++)
            if (ch1[index] == ch2[i]) {
                index++;
                if (index == ch1.length)
                    return true;
            }
        return false;
    }
}

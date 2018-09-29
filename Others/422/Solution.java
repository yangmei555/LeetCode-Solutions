class Solution {
    public boolean validWordSquare(List<String> words) {
        if (words.size() == 0)
            return true;
        if (words.get(0).length() != words.size())
            return false;
        for (int i = 0; i < words.size(); i++) {
            char[] ch = words.get(i).toCharArray();
            int count = 0;
            for (String str : words) {
                if (count >= ch.length) {
                    if (str.length() >= i + 1)
                        return false;
                    else
                        break;
                }
                if (str.length() < i + 1 || str.charAt(i) != ch[count++]) 
                    return false;
            }
            if (count != ch.length)
                return false;
        }
        return true;
    }
}


class Solution {
    public boolean validWordSquare(List<String> words) {
        if (words.size() == 0)
            return true;
        if (words.get(0).length() != words.size())
            return false;
        char[][] ch = new char[words.size()][];
        for (int i = 0; i < words.size(); i++) {
            ch[i] = words.get(i).toCharArray();
            if (i != 0 && ch[i-1].length < ch[i].length)
                return false;
        }
        for (int i = 0; i < ch.length; i++) {
            for (int j = 0; j < ch.length; j++) {
                if (j == ch[i].length) {
                    if (ch[j].length < i + 1)
                        break;
                    else
                        return false;
                }
                if (ch[j].length < i + 1 || ch[i][j] != ch[j][i])
                    return false;
            }
        }
        return true;
    }
}


class Solution {
    public boolean validWordSquare(List<String> words) {
        int size = words.size();
        if (words.get(0).length() != size)
            return false;
        for (int i = 1; i < size; i++) {
            if (words.get(i).length() > size)
                return false;
            for (int j = 0; j < i; j++) {
                if (j < words.get(i).length()) {
                    if (words.get(j).length() <= i || 
                        words.get(j).charAt(i) != words.get(i).charAt(j))
                        return false;
                } else {
                    if (words.get(j).length() > i)
                        return false;
                }
            }
        }
        return true;
    }
}

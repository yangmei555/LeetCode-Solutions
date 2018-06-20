class Solution {
    public boolean isValidSerialization(String preorder) {
        char[] ch = preorder.toCharArray();
        if (ch[ch.length-1] != '#')
            return false;
        boolean[] stack = new boolean[ch.length];
        int index = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == ',') {
                stack[index++] = false;
            } else if (ch[i] == '#') {
                stack[index++] = true;
                while (index >= 3 && stack[index-2] == true) {
                    if (stack[index-3])
                        return false;
                    stack[index-3] = true;
                    index -= 2;
                }
                i++;
            }
        }
        return index == 1;
    }
}


class Solution {
    public boolean isValidSerialization(String preorder) {
        char[] ch = preorder.toCharArray();
        int count = 1;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '#') {
                count--;
                if (count == 0)
                    return i == ch.length-1;
            } else if (ch[i] == ',' && ch[i-1] != '#') {
                count++;
            }
        }
        return false;
    }
}


class Solution {
    public boolean isValidSerialization(String preorder) {
        char[] ch = preorder.toCharArray();
        return helper(ch, 0) == ch.length-1;
    }
    
    public int helper(char[] ch, int start) {
        if (start >= ch.length)
            return start;
        if (ch[start] == '#') 
            return start;
        while (start < ch.length && ch[start] != ',')
            start++;
        int leftend = helper(ch, start+1);
        return helper(ch, leftend+2);
    }
}

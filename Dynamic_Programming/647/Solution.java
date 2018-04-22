class Solution {
    public int countSubstrings(String s) {
        boolean[] check = new boolean[s.length()];
        char[] ch = s.toCharArray();
        int res = 0;
        boolean temp1, temp2;
        for (int i = 0; i < s.length(); i++) {
            temp1 = check[i];
            for (int j = i; j >= 0; j--) {
                temp2 = check[j];
                if (i == j) {
                    check[j] = true;
                } else {
                    check[j] = ch[i] == ch[j] ? temp1 || (i-j==1) : false;
                }
                temp1 = temp2;
                if (check[j])
                    res++;
            }
        }
        return res;
    }
}


class Solution {
    public int countSubstrings(String s) {
        boolean[] check = new boolean[s.length()];
        char[] ch = s.toCharArray();
        int res = 0;
        boolean temp1, temp2;
        for (int i = s.length() - 1; i >= 0; i--) {
            temp1 = check[i];
            for (int j = i; j < s.length(); j++) {
                temp2 = check[j];
                if (i == j) {
                    check[j] = true;
                } else {
                    check[j] = ch[i] == ch[j] ? temp1 || (j-i==1) : false;
                }
                temp1 = temp2;
                if (check[j])
                    res++;
            }
        }
        return res;
    }
}


class Solution {
    public int countSubstrings(String s) {
        char[] ch = s.toCharArray();
        int res = 0, left = 0, right = 0;
        for (int i = 0; i < ch.length; i++) {
            res++;
            left = i-1;
            right = i+1;
            while (left >= 0 && right < ch.length && ch[left--] == ch[right++])
                res++;
            left = i;
            right = i+1;
            while (left >= 0 && right < ch.length && ch[left--] == ch[right++])
                res++;
        }
        return res;
    }
}

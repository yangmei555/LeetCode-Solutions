class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        char[] ch = Integer.toString(x).toCharArray();
        int i = 0, j = ch.length-1;
        while (i < j) {
            if (ch[i] != ch[j])
                return false;
            i++;
            j--;
        }
        return true;
    }
}


class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0))
            return false;
        int rev = 0;
        while (x > rev) {
            rev = 10 * rev + x % 10;
            x /= 10;
        }
        return x == rev || rev/10 == x;
    }
}

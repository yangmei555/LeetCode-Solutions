// brainless brute force 
class Solution {
    public int maximumSwap(int num) {
        char[] ch = (num + "").toCharArray();
        int res = num;
        for (int i = 0; i < ch.length; i++) {
            for (int j = i+1; j < ch.length; j++) {
                char temp = ch[i];
                ch[i] = ch[j];
                ch[j] = temp;
                res = Math.max(res, Integer.valueOf(new String(ch)));
                temp = ch[i];
                ch[i] = ch[j];
                ch[j] = temp;
            }
        }
        return res;
    }
}


class Solution {
    public int maximumSwap(int num) {
        int[] digit = new int[9];
        int n = num, index = 0;
        while (n != 0) {
            digit[index] = n % 10;
            n /= 10;
            index++;
        }
        int[] temp = Arrays.copyOf(digit, index);
        Arrays.sort(temp);
        for (int i = index-1; i >= 0; i--) {
            if (digit[i] == temp[i])
                continue;
            int j = 0;
            for (; j < i; j++)
                if (digit[j] == temp[i])
                    break;
            digit[j] = digit[i];
            digit[i] = temp[i];
            break;
        }
        int res = 0;
        for (int i = index-1; i >= 0; i--)
            res = 10 * res + digit[i];
        return res;
    }
}


class Solution {
    public int maximumSwap(int num) {
        char[] ch = Integer.toString(num).toCharArray();
        for (int i = 0; i < ch.length-1; i++) {
            if (ch[i] < ch[i+1]) {
                int max = i+1;
                for (int j = i+1; j < ch.length; j++) 
                    if (ch[j] >= ch[max])
                        max = j;
                int k = i;
                while (k >= 0 && ch[max] > ch[k])
                    k--;
                k++;
                char temp = ch[max];
                ch[max] = ch[k];
                ch[k] = temp;
                return Integer.valueOf(new String(ch));
            }
        }
        return num;
    }
}


class Solution {
    public int maximumSwap(int num) {
        char[] ch = Integer.toString(num).toCharArray();
        int[] digits = new int[10];
        for (int i = 0; i < ch.length; i++) {
            digits[ch[i]-'0'] = i;
        }
        for (int i = 0; i < ch.length; i++) {
            for (int j = 9; j > ch[i]-'0'; j--) {
                if (digits[j] > i) {
                    char temp = ch[i];
                    ch[i] = ch[digits[j]];
                    ch[digits[j]] = temp;
                    return Integer.valueOf(new String(ch));
                }
            }
        }
        return num;
    }
}

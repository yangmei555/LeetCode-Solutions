public class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] str = s.split(" ");
        for (int i = str.length-1; i >= 0; i--) {
            if (str[i].length() != 0)
                sb.append(str[i] + " ");
        }
        if (sb.length() == 0)
            return "";
        else
            return sb.substring(0, sb.length()-1).toString();
    }
}


public class Solution {
    public String reverseWords(String s) {
        char[] ch = s.toCharArray();
        char temp = 0;
        int i = 0, j = ch.length-1;
        while (i < j) {
            temp = ch[i];
            ch[i] = ch[j];
            ch[j] = temp;
            i++;
            j--;
        }
        int run = 0, start = 0;
        i = 0;
        while (i < ch.length) {
            while (i < ch.length && ch[i] == ' ') 
                i++;
            start = run;
            while (i < ch.length && ch[i] != ' ') 
                ch[run++] = ch[i++];
            for (int m = start, k = run-1; m < k; m++, k--) {
                temp = ch[m];
                ch[m] = ch[k];
                ch[k] = temp;
            }
            if (run != start && run != ch.length)
                ch[run++] = ' ';
        }
        if (run == 0)
            return "";
        return ch[run-1] == ' ' ? new String(ch).substring(0, run-1) : 
                                    new String(ch).substring(0, run);
    }
}


public class Solution {
    public String reverseWords(String s) {
        char[] ch = s.toCharArray();
        char temp = 0;
        int i = 0, j = ch.length-1;
        while (i < j) {
            temp = ch[i];
            ch[i++] = ch[j];
            ch[j--] = temp;
        }
        int run = 0, start = 0;
        for (i = 0; i <= ch.length; i++) {
            if (i == ch.length || ch[i] == ' ') {
                if (i != 0 && ch[i-1] != ' ') {
                    for (int k = start, m = i-1; k < m; k++, m--) {
                        temp = ch[k];
                        ch[k] = ch[m];
                        ch[m] = temp;
                    }
                    for (int k = start; k < i; k++)
                        ch[run++] = ch[k];
                    if (run != ch.length)
                        ch[run++] = ' ';
                }
                start = i + 1;
            }
        }
        if (run == 0)
            return "";
        return ch[run-1] == ' ' ? new String(ch).substring(0, run-1) : 
                                    new String(ch).substring(0, run);
    }
}


void reverseWords(char *s) {
    int len = strlen(s);
    int i = 0, j = len - 1;
    char temp = 0;
    while (i < j) {
        temp = s[i];
        s[i] = s[j];
        s[j] = temp;
        i++;
        j--;
    }
    i = 0;
    while (i < len && s[i] == ' ')
        i++;
    if (i == len) {
        s[0] = '\0';
        return;
    }
    int start = i, run = 0;
    while (i < len) {
        while (i < len && s[i] != ' ')
            i++;
        for (int j = start, k = i-1; j < k; j++, k--) {
            temp = s[j];
            s[j] = s[k];
            s[k] = temp;
        }
        for (int j = start; j < i; j++, run++)
            s[run] = s[j];
        s[run] = ' ';
        run++;
        while (i < len && s[i] == ' ')
            i++;
        start = i;
    }
    s[run-1] = '\0';
}

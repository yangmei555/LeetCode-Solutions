class Solution {
    public String toGoatLatin(String S) {
        String[] strs = S.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            char[] ch = strs[i].toCharArray();
            if (ch[0] == 'a' || ch[0] == 'e' || ch[0] == 'i' || ch[0] == 'o' || ch[0] == 'u' || 
                ch[0] == 'A' || ch[0] == 'E' || ch[0] == 'I' || ch[0] == 'O' || ch[0] == 'U') {
                sb.append(ch).append("ma");
            } else {
                sb.append(strs[i].substring(1)).append(ch[0]).append("ma");
            }
            for (int j = 0; j <= i; j++)
                sb.append('a');
            sb.append(' ');
        }
        return sb.substring(0, sb.length()-1);
    }
}


class Solution {
    public String toGoatLatin(String S) {
        StringBuilder sb = new StringBuilder();
        char[] ch = S.toCharArray();
        int start = 0, index = 0, count = 0;
        while (index < ch.length) {
            start = index++;
            while (index < ch.length && ch[index] != ' ')
                index++;
            if (ch[start] == 'a' || ch[start] == 'e' || ch[start] == 'i' || 
                ch[start] == 'o' || ch[start] == 'u' || ch[start] == 'A' || 
                ch[start] == 'E' || ch[start] == 'I' || ch[start] == 'O' || ch[start] == 'U') {
                for (int i = start; i < index; i++)
                    sb.append(ch[i]);
                sb.append("ma");
            } else {
                for (int i = start+1; i < index; i++)
                    sb.append(ch[i]);
                sb.append(ch[start]).append("ma");
            }
            count++;
            for (int i = 0; i < count; i++)
                sb.append('a');
            if (index != ch.length)
                sb.append(' ');
            index++;
        }
        return sb.toString();
    }
}

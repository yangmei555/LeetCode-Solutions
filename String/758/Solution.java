class Solution {
    public String boldWords(String[] words, String S) {
        if (S == null || S.length() == 0)
            return new String();
        int[] info = new int[S.length()];
        int start = 0, index = 0;
        for (String word : words) {
            start = 0;
            while (true) {
                index = S.substring(start).indexOf(word);
                if (index == -1)
                    break;
                index += start;
                for (int i = 0; i < word.length(); i++)
                    info[index + i] = 1;
                start = index + 1;
            }
        }
        StringBuilder sb = new StringBuilder(S);
        int offset = 0;
        for (int i = 0; i < info.length; i++) {
            if ((i != 0 && info[i] == 1 && info[i-1] == 0) || (i == 0 && info[i] == 1)) {
                sb.insert(i + offset, "<b>");
                offset += 3;
            }
            if ((i != info.length - 1 && info[i] == 1 && info[i+1] == 0) || 
                (i == info.length - 1 && info[i] == 1)) {
                sb.insert(i + 1 + offset, "</b>");
                offset += 4;
            }
        }
        return sb.toString();
    }
}


class Solution {
    public String boldWords(String[] words, String S) {
        if (S == null || S.length() == 0)
            return new String();
        int[] info = new int[S.length() + 1];
        char[] ch = S.toCharArray();
        for (String word : words) {
            int start = 0, index = 0;
            while (true) {
                index = S.indexOf(word, start);
                if (index == -1)
                    break;
                info[index]++;
                info[index + word.length()]--;
                start = index + 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        int sum = 0, pre = 0;
        for (int i = 0; i < info.length; i++) {
            sum += info[i];
            if (pre == 0 && sum > 0)
                sb.append("<b>");
            if (pre > 0 && sum == 0)
                sb.append("</b>");
            if (i < S.length())
                sb.append(ch[i]);
            pre = sum;
        }
        return sb.toString();
    }
}

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new LinkedList<>();
        int index = 0;
        while (index < words.length) {
            int len = -1, temp = index;
            while (index < words.length && len+1+words[index].length() <= maxWidth) 
                len += 1 + words[index++].length();
            StringBuilder sb = new StringBuilder();
            if (index == words.length || index-temp == 1) {
                for (int i = temp; i < index; i++)
                    sb.append(words[i]).append(' ');
                while (sb.length() < maxWidth)
                    sb.append(' ');
                sb.setLength(maxWidth);
                res.add(sb.toString());
            } else {
                int count = index - temp;
                int blanks = maxWidth - len + count - 1;
                int num = blanks / (count-1), remain = blanks % (count-1);
                for (int i = temp; i < index; i++) {
                    sb.append(words[i]);
                    if (i != index-1) {
                        for (int j = 0; j < num; j++)
                            sb.append(' ');
                        if (i-temp < remain)
                            sb.append(' ');
                    }
                }
                res.add(sb.toString());
            }
        }
        return res;
    }
}


// a more concise way to implement the above solution 
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new LinkedList<>();
        int index = 0;
        while (index < words.length) {
            int len = -1, temp = index;
            while (index < words.length && len+1+words[index].length() <= maxWidth) 
                len += 1 + words[index++].length();
            StringBuilder sb = new StringBuilder(words[temp]);
            for (int i = temp+1; i < index; i++) {
                for (int j = 0; j < (index == words.length ? 1 : 
                                        (maxWidth-len+index-temp-1)/(index-temp-1)); j++)
                    sb.append(' ');
                if (index != words.length && i-temp <= (maxWidth-len+index-temp-1)%(index-temp-1))
                    sb.append(' ');
                sb.append(words[i]);
            }
            while (sb.length() < maxWidth)
                sb.append(' ');
            res.add(sb.toString());
        }
        return res;
    }
}

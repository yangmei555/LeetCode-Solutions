class Solution {
    public String removeKdigits(String num, int k) {
        StringBuilder sb = new StringBuilder(num);
        int index = 0;
        while (index + 1 < sb.length() && k > 0) {
            if (sb.charAt(index) > sb.charAt(index + 1)) {
                sb.deleteCharAt(index);
                if (index != 0)
                    index--;
                k--;
            } else {
                index++;
            }
        }
        while (k-- > 0) 
            sb.setLength(sb.length()-1);
        index = 0;
        while (index < sb.length() && sb.charAt(index) == '0')
            index++;
        return index == sb.length() ? "0" : sb.substring(index);
    }
}


class Solution {
    public String removeKdigits(String num, int k) {
        char[] ch = num.toCharArray();
        int size = 0, index = 0;
        while (index < ch.length && k > 0) {
            while (size != 0 && k > 0 && ch[size-1] > ch[index]) {
                k--;
                size--;
            }
            ch[size++] = ch[index++];
        }
        while (index < ch.length)
            ch[size++] = ch[index++];
        // while (k-- > 0)
        //     size--;
        size -= k;
        index = 0;
        while (index < size && ch[index] == '0')
            index++;
        return index == size ? "0" : String.valueOf(ch, index, size - index);
    }
}


class Solution {
    public String removeKdigits(String num, int k) {
        char[] ch = num.toCharArray();
        int index = 0;
        for (int i = 0; i < ch.length; i++) {
            while (index != 0 && k != 0 && ch[index-1] > ch[i]) {
                index--;
                k--;
            }
            ch[index++] = ch[i];
        }
        index -= k;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < index; i++) {
            if (ch[i] == '0' && sb.length() == 0)
                continue;
            sb.append(ch[i]);
        }
        return sb.length() != 0 ? sb.toString() : "0";
    }
}

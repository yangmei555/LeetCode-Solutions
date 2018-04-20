class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int res = 0;
        int col = 0, index = 0, len = sentence.length;
        int sum = 0, count = 0;
        for (String s : sentence)
            sum += s.length() + 1;
        for (int i = 0; i < rows; i++) {
            col = cols;
            while (col >= sentence[index].length()) {
                if (index == 0) {
                    while (col >= sum-1) {
                        count = (col + 1) / sum;
                        res += count;
                        col -= sum * count;
                    }
                }
                if (col < sentence[index].length())
                    break;
                col -= sentence[index].length() + 1;
                index++;
                if (index == len) {
                    res++;
                    index = 0;
                }
            }
        }
        return res;
    }
}


class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int len = sentence.length, time = 0, index = 0, col = 0, res = 0;
        int[] start = new int[len];
        int[] times = new int[len];
        for (int i = 0; i < len; i++) {
            col = cols;
            index = i;
            time = 0;
            while (col >= sentence[index].length()) {
                col -= sentence[index].length() + 1;
                index++;
                if (index == len) {
                    index = 0;
                    time++;
                }
            }
            start[i] = index;
            times[i] = time;
        }
        index = 0;
        for (int i = 0; i < rows; i++) {
            res += times[index];
            index = start[index];
        }
        return res;
    }
}


class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String str = String.join(" ", sentence);
        str += " ";
        char[] ch = str.toCharArray();
        int index = 0, len = ch.length;
        for (int i = 0; i < rows; i++) {
            index += cols;
            if (ch[index % len] == ' ') {
                index++;
            } else {
                while (index >= 0 && ch[index % len] != ' ')
                    index--;
                index++;
            }
        }
        return index / len;
    }
}

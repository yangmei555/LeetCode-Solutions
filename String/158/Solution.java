/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    StringBuilder sb = new StringBuilder();
    public int read(char[] buf, int n) {
        int index = 0;
        char[] ch = new char[4];
        while (index < n && sb.length() > 0) {
            buf[index++] = sb.charAt(0);
            sb.deleteCharAt(0);
        }
        while (index < n) {
            int get = read4(ch), min = Math.min(get, n-index);
            for (int i = 0; i < min; i++)
                buf[index++] = ch[i];
            if (min != get) {
                for (int i = min; i < get; i++)
                    sb.append(ch[i]);
            }
            if (get != 4)
                break;
        }
        return index;
    }
}

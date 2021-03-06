/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        char[] ch = new char[4];
        int index = 0;
        while (index != n) {
            int get = read4(ch), temp = index;
            for (int i = 0; i < get && index < n; i++)
                buf[index++] = ch[i];
            if (get != 4)
                break;
        }
        return index;
    }
}

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        int temp = 0, res = 0;
        char[] ch = new char[4];
        while (res < n) {
            temp = read4(ch);
            for (int i = 0; i < temp && res < n; i++, res++)
                buf[res] = ch[i];
            if (temp !=  4)
                break;
        }
        return res;
    }
}

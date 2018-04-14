public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res = res | (n & 1);
            n >>>= 1;
        }
        return res;
    }
}


public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        res = (n << 16) | (n >>> 16);
        res = ((res & 0x00ff00ff) << 8) | ((res & 0xff00ff00) >>> 8);
        res = ((res & 0x0f0f0f0f) << 4) | ((res & 0xf0f0f0f0) >>> 4);
        res = ((res & 0x33333333) << 2) | ((res & 0xcccccccc) >>> 2);
        res = ((res & 0x55555555) << 1) | ((res & 0xaaaaaaaa) >>> 1);
        return res;
    }
}

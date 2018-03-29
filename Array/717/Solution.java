class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int len = bits.length;
        if (len == 1)
            return true;
        int ones = 0;
        for (int i = len - 2; i >= 0; i--) {
            if (bits[i] == 0) {
                if (ones % 2 == 0)
                    return true;
                else 
                    return false;
            }
            ones++;
        }
        if (len % 2 == 0)
            return false;
        else 
            return true;
    }
}

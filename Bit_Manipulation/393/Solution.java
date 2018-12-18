class Solution {
    public boolean validUtf8(int[] data) {
        int index = 0;
        while (index < data.length) {
            if ((data[index] >> 7) == 0) {
                index++;                
            } else if ((data[index] >> 5) == 0b110) {
                index++;
                for (int i = 0; i < 1; i++, index++) {
                    if (index == data.length || (data[index] >> 6) != 0b10)
                        return false;
                }
            } else if ((data[index] >> 4) == 0b1110) {
                index++;
                for (int i = 0; i < 2; i++, index++) {
                    if (index == data.length || (data[index] >> 6) != 0b10)
                        return false;
                }
            } else if ((data[index] >> 3) == 0b11110) {
                index++;
                for (int i = 0; i < 3; i++, index++) {
                    if (index == data.length || (data[index] >> 6) != 0b10)
                        return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}

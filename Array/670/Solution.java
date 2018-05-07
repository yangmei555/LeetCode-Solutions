class Solution {
    public int maximumSwap(int num) {
        int[] digit = new int[9];
        int n = num, index = 0;
        while (n != 0) {
            digit[index] = n % 10;
            n /= 10;
            index++;
        }
        int[] temp = Arrays.copyOf(digit, index);
        Arrays.sort(temp);
        for (int i = index-1; i >= 0; i--) {
            if (digit[i] == temp[i])
                continue;
            int j = 0;
            for (; j < i; j++)
                if (digit[j] == temp[i])
                    break;
            digit[j] = digit[i];
            digit[i] = temp[i];
            break;
        }
        int res = 0;
        for (int i = index-1; i >= 0; i--)
            res = 10 * res + digit[i];
        return res;
    }
}

class Solution {
    public int nextGreaterElement(int n) {
        int[] digits = new int[10];
        int num = n, index = 0;
        while (num != 0) {
            digits[index++] = num % 10;
            num /= 10;
        }
        int i = 1;
        while (i < index && digits[i-1] <= digits[i])
            i++;
        if (i == index)
            return -1;
        int j = i-1;
        for (; j >= 0; j--)
            if (digits[j] <= digits[i])
                break;
        j++;
        int temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;
        for (int m = 0, k = i-1; m < k; m++, k--) {
            temp = digits[m];
            digits[m] = digits[k];
            digits[k] = temp;
        }
        int res = 0;
        for (i = index-1; i >= 0; i--) {
            if (res * 10 / 10 != res)
                return -1;
            res = res * 10 + digits[i];
        }
        return res;
    }
}

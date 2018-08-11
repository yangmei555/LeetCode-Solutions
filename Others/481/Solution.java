class Solution {
    public int magicalString(int n) {
        if (n == 0)
            return 0;
        if (n <= 3)
            return 1;
        StringBuilder sb = new StringBuilder();
        sb.append(1).append(2).append(2);
        int res = 1, c = 1, index = 2;
        while (sb.length() < n) {
            if (c == 1)
                res += sb.charAt(index) == '1' ? 1 : 2;
            if (sb.charAt(index) == '1')
                sb.append(c);
            else
                sb.append(c).append(c);
            c = 3 - c;
            index++;
        }
        if (sb.length() > n && c == 2)
            res--;
        return res;
    }
}


class Solution {
    public int magicalString(int n) {
        if (n == 0)
            return 0;
        if (n <= 3)
            return 1;
        int[] arr = new int[n];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 2;
        int res = 1, c = 1, index = 2, i = 3;
        while (i < n) {
            arr[i++] = c;
            if (arr[index++] == 2 && i < n)
                arr[i++] = c;
            c = 3 - c;
        }
        for (int j = 3; j < n; j++) {
            if (arr[j] == 1)
                res++;
        }
        return res;
    }
}

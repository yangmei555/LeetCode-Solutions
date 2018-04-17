class Solution {
    public int nthUglyNumber(int n) {
        if (n <= 6)
            return n;
        int[] num = new int[1691];
        for (int i = 1; i <= 6; i++)
            num[i] = i;
        int two = 4, three = 3, five = 2;
        for (int k = 7; k <= n; k++) {
            if (2 * num[two] == num[k-1])
                two++;
            if (3 * num[three] == num[k-1])
                three++;
            if (5 * num[five] == num[k-1])
                five++;
            if (2 * num[two] <= 3 * num[three] && 2 * num[two] <= 5 * num[five])
                num[k] = 2 * num[two++];
            else if (3 * num[three] <= 5 * num[five])
                num[k] = 3 * num[three++];
            else 
                num[k] = 5 * num[five++];
            // System.out.println(num[k] + " " + two + " " + three + " " + five);
        }
        return num[n];
    }
}



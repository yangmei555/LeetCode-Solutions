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


class Solution {
    public int nthUglyNumber(int n) {
        int[] res = new int[n];
        int index1 = 0, index2 = 0, index3 = 0, min = 0;
        int cand1 = 1, cand2 = 1, cand3 = 1;
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            min = Integer.MAX_VALUE;
            if (cand1 == res[i-1]) 
                cand1 = res[index1++] * 2;
            if (cand2 == res[i-1]) 
                cand2 = res[index2++] * 3;
            if (cand3 == res[i-1])
                cand3 = res[index3++] * 5;
            min = min < cand1 ? min : cand1;
            min = min < cand2 ? min : cand2;
            min = min < cand3 ? min : cand3;
            res[i] = min;
        }
        return res[n-1];
    }
}


class Solution {
    public int nthUglyNumber(int n) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(1);
        int pre = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            ans = queue.poll();
            if (ans == pre) {
                i--;
                continue;
            } else {
                if (ans < Integer.MAX_VALUE / 2)
                    queue.offer(ans * 2);
                if (ans < Integer.MAX_VALUE / 3)
                    queue.offer(ans * 3);
                if (ans < Integer.MAX_VALUE / 5)
                    queue.offer(ans * 5);
                pre = ans;
            }
        }
        return ans;
    }
}


class Solution {
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < n; i++) {
            int min = Math.min(ugly[p2] * 2, Math.min(ugly[p3] * 3, ugly[p5] * 5));
            ugly[i] = min;
            if (min == ugly[p2] * 2)
                p2++;
            if (min == ugly[p3] * 3)
                p3++;
            if (min == ugly[p5] * 5)
                p5++;
        }
        return ugly[n-1];
    }
}

class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n == 1)
            return 1;
        int[] res = new int[n];
        res[0] = 1;
        int[] index = new int[primes.length], temp = new int[primes.length];
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                temp[j] = primes[j] * res[index[j]];
                min = min < temp[j] ? min : temp[j];
            }
            res[i] = min;
            for (int j = 0; j < primes.length; j++) {
                if (min == temp[j])
                    index[j]++;
            }
        }
        return res[n-1];
    }
}


class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n == 1)
            return 1;
        int[] res = new int[n];
        res[0] = 1;
        int[] index = new int[primes.length], temp = new int[primes.length];
        Arrays.fill(temp, 1);
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                if (temp[j] == res[i-1]) {
                    temp[j] = primes[j] * res[index[j]];
                    index[j]++;
                }
                min = min < temp[j] ? min : temp[j];
            }
            res[i] = min;
        }
        return res[n-1];
    }
}


class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(1);
        int ans = 0, pre = 0;
        for (int i = 0; i < n; i++) {
            ans = queue.poll();
            if (ans == pre) {
                i--;
                continue;
            } else {
                for (int j = 0; j < primes.length; j++) {
                    if (ans < Integer.MAX_VALUE / primes[j])
                        queue.offer(ans * primes[j]);
                }
                pre = ans;
            }
        }
        return ans;
    }
}


// theoretically fastest solution, O(n * log(primes.length))  
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] res = new int[n];
        PriorityQueue<P> queue = new PriorityQueue<>(new Comparator<P>() {
            public int compare(P p1, P p2) {
                return p1.value - p2.value;
            }
        });
        for (int i = 0; i < primes.length; i++) 
            queue.offer(new P(primes[i], 1, primes[i]));
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            P top = queue.peek();
            res[i] = top.value;
            while (res[i] == queue.peek().value) {
                P poll = queue.poll();
                poll.value = res[poll.index] * poll.prime;
                poll.index++;
                queue.add(poll);
            }
        }
        return res[n-1];
    }
    
    class P {
        int prime;
        int index;
        int value;
        public P(int p, int i, int v) {
            prime = p;
            index = i;
            value = v;
        }
    }
}


class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        Queue<Integer> queue = new PriorityQueue<>();
        queue.offer(1);
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = queue.poll();
            for (int p : primes) {
                if (res <= Integer.MAX_VALUE / p)
                    queue.offer(res * p);
            }
            while (!queue.isEmpty() && queue.peek() == res)
                queue.poll();
        }
        return res;
    }
}


// has redundant inner loop 
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int[] pointers = new int[primes.length];
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++)
                min = Math.min(min, ugly[pointers[j]] * primes[j]);
            ugly[i] = min;
            for (int j = 0; j < primes.length; j++) {
                if (min == ugly[pointers[j]] * primes[j])
                    pointers[j]++;
            }
        }
        return ugly[n-1];
    }
}


// avoids redundant loop, but has to introduce an additional candidate array 
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int[] pointers = new int[primes.length];
        int[] cands = new int[primes.length];
        Arrays.fill(cands, 1);
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                if (cands[j] == ugly[i-1])
                    cands[j] = ugly[pointers[j]++] * primes[j];
                min = Math.min(min, cands[j]);
            }
            ugly[i] = min;
        }
        return ugly[n-1];
    }
}


// avoids redundant loop and avoids additional candidate array 
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int[] pointers = new int[primes.length];
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < primes.length; j++) {
                if (min > ugly[pointers[j]] * primes[j]) {
                    min = ugly[pointers[j]] * primes[j];
                    minIndex = j;
                } else if (min == ugly[pointers[j]] * primes[j]) {
                    pointers[j]++;
                }
            }
            ugly[i] = min;
            pointers[minIndex]++;
        }
        return ugly[n-1];
    }
}

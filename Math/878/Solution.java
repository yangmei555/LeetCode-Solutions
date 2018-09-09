// use priority queue to locate the remainder 
class Solution {
    public int nthMagicalNumber(int N, int A, int B) {
        int mod = 1000000007;
        int a = A, b = B;
        while (b != 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        int gcd = a;
        A /= gcd;
        B /= gcd;
        if (A == 1 || B == 1)
            return (int)((long)N * gcd % mod);
        
        int size = A + B - 1;
        int turn = (N-1) / size, remainder = (N-1) % size;
        if (remainder == size-1) {
            long res = ((long)turn * A * B + A * B) % mod;
            return (int)((res * gcd) % mod);
        }
        Queue<Long> queue = new PriorityQueue<>();
        for (int i = 1; i <= B; i++)
            queue.offer((long)A * i);
        for (int i = 1; i <= A; i++)
            queue.offer((long)B * i);
        int count = 0;
        while (count++ < remainder)
            queue.poll();
        long res = ((long)turn * A * B + queue.poll()) % mod;
        return (int)((res * gcd) % mod);
    }
}


// use binary search to locate the remainder 
// the number of dividing A or B numbers keeps increasing, so it is suitable to use binay search 
class Solution {
    public int nthMagicalNumber(int N, int A, int B) {
        int mod = 1000000007;
        int a = A, b = B;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        int gcd = a;
        A /= gcd;
        B /= gcd;
        if (A == 1 || B == 1)
            return (int)((long)N * gcd % mod);
        int size = A + B - 1;
        int turn = (N - 1) / size, remainder = (N - 1) % size;
        int left = 1, right = A * B;
        while (left < right) {
            int mid = (left + right) / 2;
            int count = (mid - 1) / A + (mid - 1) / B - 1;
            if (mid % A == 0 || mid % B == 0)
                count++;
            if (count < remainder)
                left = mid + 1;
            else
                right = mid;
        }
        long res = (long)turn * A * B + left;
        return (int)(res * gcd % mod);
    }
}


// proceed step by step to locate the remainder 
class Solution {
    public int nthMagicalNumber(int N, int A, int B) {
        int mod = 1000000007;
        int a = A, b = B;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        int gcd = a;
        A /= gcd;
        B /= gcd;
        if (A == 1 || B == 1)
            return (int)((long)N * gcd % mod);
        int size = A + B - 1;
        int turn = (N - 1) / size, remainder = (N - 1) % size;
        int count = 0, cand1 = A, cand2 = B;
        while (count < remainder) {
            if (cand1 < cand2)
                cand1 += A;
            else
                cand2 += B;
            count++;
        }
        long res = (long)turn * A * B + (cand1 < cand2 ? cand1 : cand2);
        return (int)(res * gcd % mod);
    }
}


// a more concise binary search . notice that the number of magic numbers which are smaller than 
// or equal to num is just num / A + num / B - num / lcm . 
class Solution {
    public int nthMagicalNumber(int N, int A, int B) {
        int mod = 1000000007;
        int a = A, b = B;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        int gcd = a, lcm = A * B / a;
        long left = A < B ? A : B, right = left * N;
        while (left < right) {
            long mid = (left + right) / 2;
            long count = mid / A + mid / B - mid / lcm;
            if (count < N)
                left = mid + 1;
            else
                right = mid;
        }
        return (int)(left % mod);
    }
}


// a method to locate the remainder in O(1) time . cannot fully figure out 
// why should do it like that 
class Solution {
    public int nthMagicalNumber(int N, int A, int B) {
        int mod = 1000000007;
        int a = A, b = B;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        int gcd = a, lcm = A * B / a;
        int size = lcm / A + lcm / B - 1;
        int turn = N / size, remainder = N % size;
        double x = remainder / (1.0 / A + 1.0 / B);
        int num = (int)Math.min(Math.ceil(x / A) * A, Math.ceil(x / B) * B);
        return (int)(((long)lcm * turn + num) % mod);
    }
}

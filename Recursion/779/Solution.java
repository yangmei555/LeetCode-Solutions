class Solution {
    public int kthGrammar(int N, int K) {
        if (N == 1)
            return 0;
        int len = 1 << (N - 1);
        if (K <= len / 2)
            return kthGrammar(N - 1, K);
        else 
            return N % 2 == 0 ? 1 - kthGrammar(N - 1, len + 1 - K) : 
        							kthGrammar(N - 1, len + 1 - K);
    }
}


class Solution {
    public int kthGrammar(int N, int K) {
        if (N == 1)
            return 0;
        int len = 1 << (N - 1);
        if (K <= len / 2)
            return kthGrammar(N - 1, K);
        else 
            return 1 - kthGrammar(N - 1, K - len / 2);
    }
}


class Solution {
    public int kthGrammar(int N, int K) {
        int len = 1 << (N - 1);
        int count = 0;
        while (K != 1) {
            if (K > len / 2) {
                K -= len / 2;
                count++;
            }
            len /= 2;
        }
        return count % 2 == 0 ? 0 : 1;
    }
}


// notice that, as long as K <= (1 << (N-1)), the result is irrelevant with N 
// also, when based on 0 indexed, f(2*K) = f(K), f(2*K+1) = f(K) ^ 1 
class Solution {
    public int kthGrammar(int N, int K) {
        K--;
        int count = 0;
        while (K != 0) {
            K &= (K - 1);
            count++;
        }
        return count & 1;
    }
}


// a verbose version 
class Solution {
    public int kthGrammar(int N, int K) {
        if (N == 1 || K == 1)
            return 0;
        int len = 1 << (N - 1);
        if (K <= len / 2) {
            int count = 0, temp = K - 1;
            while (temp != 0) {
                temp /= 2;
                count++;
            }
            return kthGrammar(count + 1, K);
        } else {
            K = K - len / 2;
            int count = 0, temp = K - 1;
            while (temp != 0) {
                temp /= 2;
                count++;
            }
            return kthGrammar(count + 1, K) ^ 1;
        }
    }
}

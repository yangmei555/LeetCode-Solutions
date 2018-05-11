class Solution {
    public int superPow(int a, int[] b) {
        int res = 1, temp = a % 1337;
        for (int i = b.length-1; i >= 0; i--) {
            res =  (res * helper(temp, b[i], 1337)) % 1337;
            temp = helper(temp, 10, 1337);
        }
        return res;
    }
    
    public int helper(int a, int b, int mod) {
        if (b == 0)
            return 1;
        if (b == 1)
            return a % mod;
        int ret = helper(a, b / 2, mod);
        if (b % 2 == 0) {
            return (ret * ret) % mod;
        } else {
            return (((ret * ret) % mod) * a) % mod; 
        }
    }
}

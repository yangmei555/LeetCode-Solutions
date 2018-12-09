class Solution {
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
}


// the stupid TLE method. just notice that, to make the number of facters to be odd, i must be 
// a square number . 
class Solution {
    public int bulbSwitch(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            int count = 1, num = i;
            for (int f = 2, t = 0; f * f <= num; f++, t = 0) {
                if (num % f == 0) {
                    while (num % f == 0) {
                        t++;
                        num /= f;
                    }
                    count *= (t+1);
                }
            }
            if (num != 1)
                count *= 2;
            if (count % 2 == 1)
                res++;
        }
        return res;
    }
}

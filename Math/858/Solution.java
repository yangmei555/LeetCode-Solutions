class Solution {
    public int mirrorReflection(int p, int q) {
        int a = p, b = q;
        while (a % b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        int lcm = p * q / b;
        int n1 = lcm / p, n2 = lcm / q;

        // if (n2 % 2 == 0) {
        //     if (n1 % 2 == 1)
        //         return 2;
        // } else {
        //     return n1 % 2 == 0 ? 0 : 1;
        // }
        // return -1;


        // more concise way to express the output 
        return 1 - n2 % 2 + n1 % 2;
    }
}

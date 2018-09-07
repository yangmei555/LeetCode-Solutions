// use reverse polish notation to express the expression 
class Solution {
    public boolean judgePoint24(int[] nums) {
        boolean[] used = new boolean[nums.length];
        char[] expre = new char[7];
        return helper(expre, 0, nums, used);
    }
    
    public boolean helper(char[] expre, int index, int[] nums, boolean[] used) {
        if (index == expre.length) {
            // System.out.println(Arrays.toString(expre));
            double[] stack = new double[expre.length];
            int i = 0;
            for (char c : expre) {
                if (c < '0' || c > '9') {
    // if (i < 2) {
    //     System.out.println(Arrays.toString(stack) + " " + i + " " + Arrays.toString(expre));
    //     return false;
    // }
                    double num2 = stack[--i], num1 = stack[--i];
                    stack[i++] = c == '+' ? num1 + num2 : (c == '-' ? num1 - num2 : 
                                                        (c == '*' ? num1 * num2 : num1 / num2));
                } else {
                    stack[i++] = c - '0';
                }
            }
            // floating point number divided by 0 is infinity and will not cause exception 
            return Math.abs(stack[0] - 24) <= 1e-6;
        } else {
            int count = nums.length;
            for (int i = 0; i < nums.length; i++) {
                if (!used[i]) {
                    count--;
                    expre[index] = (char)('0' + nums[i]);
                    used[i] = true;
                    if (helper(expre, index+1, nums, used))
                        return true;
                    used[i] = false;
                }
            }
            if (count - (index - count) > 1) {
                expre[index] = '+';
                if (helper(expre, index+1, nums, used))
                    return true;
                expre[index] = '-';
                if (helper(expre, index+1, nums, used))
                    return true;
                expre[index] = '*';
                if (helper(expre, index+1, nums, used))
                    return true;
                expre[index] = '/';
                if (helper(expre, index+1, nums, used))
                    return true;
            }
            return false;
        }
    }
}


// each time remove 2 numbers and replace them with their result 
// try each possibility only once, search space reduced comparing with the above solution 
class Solution {
    public boolean judgePoint24(int[] nums) {
        double[] input = new double[nums.length];
        for (int i = 0; i < nums.length; i++)
            input[i] = nums[i];
        return helper(input, input.length);
    }
    
    public boolean helper(double[] input, int len) {
        if (len == 1)
            return Math.abs(input[0] - 24) <= 1e-6;
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                double x = input[i], y = input[j];
                input[j] = input[len-1];
                input[i] = x + y;
                if (helper(input, len-1))
                    return true;
                input[i] = x - y;
                if (helper(input, len-1))
                    return true;
                input[i] = y - x;
                if (helper(input, len-1))
                    return true;
                input[i] = x * y;
                if (helper(input, len-1))
                    return true;
                input[i] = x / y;
                if (helper(input, len-1))
                    return true;
                input[i] = y / x;
                if (helper(input, len-1))
                    return true;
                input[i] = x;
                input[j] = y;
            }
        }
        return false;
    }
}

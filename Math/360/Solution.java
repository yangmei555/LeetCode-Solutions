class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] res = new int[nums.length];
        if (nums.length == 0)
            return res;
        if (a == 0) {
            if (b >= 0) {
                for (int i = 0; i < nums.length; i++)
                    res[i] = nums[i] * b + c;
            } else {
                for (int i = 0; i < nums.length; i++)
                    res[i] = nums[nums.length-1-i] * b + c;
            }
        } else {
            double sym = -b / (2.0 * a);
            int min = 0;
            for (int i = 0; i < nums.length; i++)
                if (Math.abs(sym-nums[i]) < Math.abs(sym-nums[min]))
                    min = i;
            int left = min, right = min + 1;
            int index = 0;
            boolean neg = a < 0;
            if (neg) {
                a = -a;
                b = -b;
                c = -c;
            }
            int cand1 = a*nums[left]*nums[left]+b*nums[left]+c;
            int cand2 = right == nums.length ? Integer.MAX_VALUE : 
                                            a*nums[right]*nums[right]+b*nums[right]+c;
            while (index < nums.length) {
                if (cand1 <= cand2) {
                    res[index] = cand1;
                    left--;
                    cand1 = left < 0 ? Integer.MAX_VALUE : 
                                            a*nums[left]*nums[left]+b*nums[left]+c;
                } else {
                    res[index] = cand2;
                    right++;
                    cand2 = right >= nums.length ? Integer.MAX_VALUE : 
                                            a*nums[right]*nums[right]+b*nums[right]+c;    
                }
                index++;
            }
            if (neg) {
                left = 0;
                right = nums.length-1;
                while (left < right) {
                    res[left] = -res[left];
                    res[right] = -res[right];
                    int temp = res[left];
                    res[left] = res[right];
                    res[right] = temp;
                    left++;
                    right--;
                }
                if (left == right)
                    res[left] = -res[left];
            }
        }
        return res;
    }
}


class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] res = new int[nums.length];
        if (nums.length == 0)
            return res;
        int index = a >= 0 ? nums.length-1 : 0;
        int left = 0, right = nums.length-1;
        int cand1 = helper(nums, left, a, b, c);
        int cand2 = helper(nums, right, a, b, c);
        if (a >= 0) {
            while (index >= 0) {
                if (cand1 >= cand2) {
                    res[index--] = cand1;
                    if (index < 0)
                        break;
                    left++;
                    cand1 = helper(nums, left, a, b, c);
                } else {
                    res[index--] = cand2;
                    if (index < 0)
                        break;
                    right--;
                    cand2 = helper(nums, right, a, b, c);
                }
            }
        } else {
            while (index < nums.length) {
                if (cand1 <= cand2) {
                    res[index++] = cand1;
                    if (index == nums.length)
                        break;
                    left++;
                    cand1 = helper(nums, left, a, b, c);
                } else {
                    res[index++] = cand2;
                    if (index == nums.length)
                        break;
                    right--;
                    cand2 = helper(nums, right, a, b, c);
                }
            }
        }
        return res;
    }
    
    public int helper(int[] nums, int x, int a, int b, int c) {
        return a*nums[x]*nums[x] + b*nums[x] + c;
    }
}

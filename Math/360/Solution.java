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


// concise version of the above approach 
class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] res = new int[nums.length];
        if (a == 0) {
            if (b >= 0) {
                for (int i = 0; i < res.length; i++)
                    res[i] = eval(nums[i], a, b, c);
            } else {
                for (int i = 0; i < res.length; i++)
                    res[i] = eval(nums[nums.length-1-i], a, b, c);
            }
        } else {
            double center = -b / (2D * a);
            int left = 0, right = nums.length;
            while (left < right) {
                int mid = (left + right) / 2;
                if (nums[mid] < center)
                    left = mid + 1;
                else
                    right = mid;
            }
            left--;
            for (int j = 0; j < res.length; j++) {
                int i = a > 0 ? j : res.length-1-j;
                if (left < 0) {
                    res[i] = eval(nums[right++], a, b, c);
                } else if (right == nums.length) {
                    res[i] = eval(nums[left--], a, b, c);
                } else {
                    int cand1 = eval(nums[left], a, b, c), cand2 = eval(nums[right], a, b, c);
                    if (a > 0 && cand1 < cand2 || a < 0 && cand1 > cand2) {
                        res[i] = cand1;
                        left--;
                    } else {
                        res[i] = cand2;
                        right++;
                    }
                }
            }
        }
        return res;
    }
    
    public int eval(int n, int a, int b, int c) {
        return a * n * n + b * n + c;
    }
}


// think in another way: consider the two end points first 
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


// concise version of the above approach 
class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] res = new int[nums.length];
        int index = a >= 0 ? res.length-1 : 0;
        int left = 0, right = nums.length-1;
        while (left <= right) {
            int cand1 = eval(nums[left], a, b, c), cand2 = eval(nums[right], a, b, c);
            if (a >= 0) {
                if (cand1 > cand2) {
                    res[index--] = cand1;
                    left++;
                } else {
                    res[index--] = cand2;
                    right--;
                }
            } else {
                if (cand1 < cand2) {
                    res[index++] = cand1;
                    left++;
                } else {
                    res[index++] = cand2;
                    right--;
                }
            }
        }
        return res;
    }
    
    public int eval(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}

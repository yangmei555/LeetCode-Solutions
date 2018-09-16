class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            if (!set.add(n))
                return false;
            int next = 0;
            while (n != 0) {
                next += (n % 10) * (n % 10);
                n /= 10;
            }
            n = next;
        }
        return true;
    }
}


class Solution {
    public boolean isHappy(int n) {
        int slow = n, fast = n;
        do {
            slow = helper(slow);
            fast = helper(helper(fast));
        } while (slow != fast);
        return slow == 1;
    }
    
    public int helper(int n) {
        int res = 0;
        while (n != 0) {
            res += (n % 10) * (n % 10);
            n /= 10;
        }
        return res;
    }
}

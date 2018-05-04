class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast)
                break;
        }
        fast = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }
}


class Solution {
    public int findDuplicate(int[] nums) {
        int res = 0;
        for (int n = 0; n < 31; n++) {
            int base = 1 << n;
            int c1 = 0, c2 = 0;
            for (int i = 0; i < nums.length; i++) {
                if ((nums[i] & base) != 0)
                    c1++;
                if ((i & base) != 0)
                    c2++;
            }
            if (c1 > c2)
                res += base;
        }
        return res;
    }
}


class Solution {
    public int findDuplicate(int[] nums) {
        int left = 1, right = nums.length-1;
        while (left != right) {
            int mid = (left + right) / 2;
            int count = 0;
            for (int n : nums)
                if (n <= mid)
                    count++;
            if (count <= mid)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}

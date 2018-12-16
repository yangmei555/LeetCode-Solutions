class Solution {
    public int numFriendRequests(int[] ages) {
        if (ages.length < 2)
            return 0;
        Arrays.sort(ages);
        int res = 0, dup = 0;
        for (int i = 1; i < ages.length; i++) {
            if (ages[i] <= 14)
                continue;
            if (ages[i] == ages[i-1])
                dup++;
            else
                dup = 0;
            int left = helper(ages, (int)(0.5*ages[i]+7)+1, i-1);
            while (left > 0 && ages[left-1] == ages[left])
                left--;
            res += i - left + dup;
        }
        return res;
    }
    
    public int helper(int[] ages, int target, int index) {
        int left = 0, right = index;
        while (left < right) {
            int mid = (left + right) / 2;
            if (ages[mid] == target)
                return mid;
            else if (ages[mid] < target) 
                left = mid + 1;
            else
                right = mid;
        }
        return ages[left] >= target ? left : left + 1;
    }
}


// similar idea with above but more concise 
class Solution {
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int res = 0, same = 0;
        for (int i = 0; i < ages.length; i++) {
            int index = helper(ages, 0, i, ages[i]/2+7);
            res += i - index;
            if (i != 0 && ages[i-1] == ages[i] && ages[i] > 14)
                same++;
            else
                same = 0;
            res += same;
        }
        return res;
    }
    
    public int helper(int[] ages, int left, int right, int val) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (ages[mid] <= val)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}


class Solution {
    public int numFriendRequests(int[] ages) {
        int[] count = new int[121];
        for (int a : ages)
            count[a]++;
        int res = 0;
        for (int i = 15; i < count.length; i++) {
            int j = (int)(0.5 * i + 7) + 1;
            for (; j < i; j++)
                res += count[j] * count[i];
            res += count[i] * (count[i]-1);
        }
        return res;
    }
}


class Solution {
    public int numFriendRequests(int[] ages) {
        int[] count = new int[121];
        for (int a : ages)
            count[a]++;
        int res = 0;
        for (int i = 15; i < count.length; i++) {
            int j = (int)(0.5 * i + 7);
            res += (count[i-1] - count[j]) * count[i];
            res += count[i] * (count[i]-1);
            count[i] += count[i-1];
        }
        return res;
    }
}

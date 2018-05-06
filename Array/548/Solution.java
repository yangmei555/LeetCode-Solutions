class Solution {
    public boolean splitArray(int[] nums) {
        if (nums.length < 7)
            return false;
        int total = 0;
        for (int n : nums)
            total += n;
        int i = 1, sum1 = 0;
        for (; i < nums.length; i++) {
            sum1 += nums[i-1];
            int j = i+2, sum2 = 0;
            for (; j < nums.length; j++) {
                sum2 += nums[j-1];
                if (sum2 != sum1)
                    continue;
                int k = j + 2, sum3 = 0;
                for (; k < nums.length; k++) {
                    sum3 += nums[k-1];
                    if (sum3 != sum2)
                        continue;
                    if (k < nums.length-1 && total-sum1*3-nums[i]-nums[j]-nums[k] == sum1)
                        return true;
                    if (nums[k] == 0) {
                        while (k+1 < nums.length && nums[k+1] == 0)
                            k++;
                    }
                }
                if (nums[j] == 0) {
                    while (j+1 < nums.length && nums[i+1] == 0)
                        j++;
                }
            }
            if (nums[i] == 0) {
                while (i+1 < nums.length && nums[i+1] == 0)
                    i++;
            }
        }
        return false;
    }
}


class Solution {
    public boolean splitArray(int[] nums) {
        if (nums.length < 7)
            return false;
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] sum = new int[nums.length];
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
            sum[i] = total;
            map.putIfAbsent(total, new LinkedList<>());
            map.get(total).add(i);
        }
        int sum1 = 0;
        for (int i = 1; i < nums.length; i++) {
            sum1 += nums[i-1];
            if (!map.containsKey(sum1+sum1+nums[i]))
                continue;
            for (int j : map.get(sum1+sum1+nums[i])) {
                if (j < i+1 || j > nums.length-5)
                    continue;
                j++;
                if (!map.containsKey(3*sum1+nums[i]+nums[j]))
                    continue;
                for (int k : map.get(3*sum1+nums[i]+nums[j])) {
                    if (k < j+1 || k > nums.length-3)
                        continue;
                    k++;
                    if (4*sum1+nums[i]+nums[j]+nums[k] == total)
                        return true;
                }
            }
            if (nums[i] == 0) {
                while (i+1 < nums.length && nums[i+1] == 0)
                    i++;
            }
        }
        return false;
    }
}

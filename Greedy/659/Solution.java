class Solution {
    public boolean isPossible(int[] nums) {
        int ones = 0, twos = 0, threes = 0;
        int index = 0, pre = 0;
        while (index < nums.length) {
            int count = index++;
            while (index < nums.length && nums[index-1] == nums[index])
                index++;
            count = index - count;
            if (ones + twos == 0) {
                if (pre + 1 != nums[index-1]) 
                    threes = 0;
                else
                    threes = threes < count ? threes : count;
                ones += count - threes;
            } else {
                if (ones + twos > count || pre + 1 != nums[index-1])
                    return false;
                int remain = count - ones - twos;
                threes = threes < remain ? threes : remain;
                remain -= threes;
                threes += twos;
                twos = ones;
                ones = remain;
            }
            pre = nums[index-1];
            // System.out.println(pre + ":  " + ones + " " + twos + " " + threes);
        }
        return ones + twos == 0;
    }
}


class Solution {
    public boolean isPossible(int[] nums) {
        // the number of arrays whose length is 1, 2, or more than 3
        int ones = 0, twos = 0, threes = 0;
        int index = 0, pre = 0;
        while (index < nums.length) {
            int count = index++;
            while (index < nums.length && nums[index-1] == nums[index])
                index++;
            count = index - count;
            if (ones + twos > count || ones + twos != 0 && pre + 1 != nums[index-1])
                return false;
            if (pre + 1 != nums[index-1])
                threes = 0;
            int remain = count - ones - twos;   // how many left after feeding ones and twos
            threes = threes < remain ? threes : remain;
            remain -= threes;
            threes += twos;
            twos = ones;
            ones = remain;
            pre = nums[index-1];
            // System.out.println(pre + ":  " + ones + " " + twos + " " + threes);
        }
        return ones + twos == 0;
    }
}


class Solution {
    public boolean isPossible(int[] nums) {
        int ones = 0, twos = 0, threes = 0, pre = Integer.MIN_VALUE, index = 0;
        while (index++ < nums.length) {
            int count = 1;
            while (index < nums.length && nums[index-1] == nums[index]) {
                count++;
                index++;
            }
            // different process logics than the above 2 solutions 
            if (pre != Integer.MIN_VALUE && pre + 1 != nums[index-1]) {
                if (ones != 0 || twos != 0)
                    return false;
                threes = 0;
                ones = count;
            } else {
                if (ones + twos > count)
                    return false;
                int remain = count - ones - twos;
                threes = threes < remain ? threes : remain;
                remain -= threes;
                threes += twos;
                twos = ones;
                ones = remain;
            }
            pre = nums[index-1];
        }
        return ones == 0 && twos == 0;
    }
}


class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>(), ending = new HashMap<>();
        for (int n : nums)
            count.put(n, count.getOrDefault(n, 0) + 1);
        for (int n : nums) {
            if (count.get(n) == 0) {
                continue;
            } else if (ending.getOrDefault(n-1, 0) != 0) {
                ending.put(n-1, ending.get(n-1) - 1);
                ending.put(n, ending.getOrDefault(n, 0) + 1);
                count.put(n, count.get(n) - 1);
            } else if (count.getOrDefault(n+1, 0) != 0 && count.getOrDefault(n+2, 0) != 0) {
                count.put(n, count.get(n) - 1);
                count.put(n+1, count.get(n+1) - 1);
                count.put(n+2, count.get(n+2) - 1);
                ending.put(n+2, ending.getOrDefault(n+2, 0) + 1);
            } else {
                return false;
            }
        }
        return true;
    }
}

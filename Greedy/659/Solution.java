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

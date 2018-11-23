class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> list = new LinkedList<>();
        if (nums.length == 0)
            return list;
        int count1 = 0, count2 = 0, cand1 = nums[0], cand2 = nums[0];
        for (int n : nums) {
            
            if (count1 == 0) {
                cand1 = n;
            } else if (count2 == 0) {
                cand2 = n;
            }
            if (n == cand1) {
                count1++;
            } else if (n == cand2) {
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        // System.out.println(cand1 + " " + cand2);
        count1 = count2 = 0;
        for (int n : nums) {
            if (n == cand1)
                count1++;
            else if (n == cand2)
                count2++;
        }
        if (count1 > nums.length/3)
            list.add(cand1);
        if (count2 > nums.length/3)
            list.add(cand2);
        return list;
    }
}


class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> list = new LinkedList<>();
        if (nums.length == 0)
            return list;
        int count1 = 0, count2 = 0, cand1 = nums[0], cand2 = nums[0];
        for (int n : nums) {
            if (cand1 == n) {
                count1++;
            } else if (cand2 == n) {
                count2++;
            } else {
                if (count1 == 0) {
                    count1++;
                    cand1 = n;
                } else if (count2 == 0) {
                    count2++;
                    cand2 = n;
                } else {
                    count1--;
                    count2--;
                }
            }
        }
        count1 = count2 = 0;
        for (int n : nums) {
            if (n == cand1)
                count1++;
            else if (n == cand2)
                count2++;
        }
        if (count1 > nums.length/3)
            list.add(cand1);
        if (count2 > nums.length/3)
            list.add(cand2);
        return list;
    }
}

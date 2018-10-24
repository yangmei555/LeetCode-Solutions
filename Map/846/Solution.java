class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        if (hand.length % W != 0)
            return false;
        Arrays.sort(hand);
        // note that we have to use ArrayList rather than LinkedList , 
        // because LinkedList get and set takes O(n) 
        List<Integer> nums = new ArrayList<>(), counts = new ArrayList<>();
        int index = 0;
        while (index < hand.length) {
            int start = index++;
            while (index < hand.length && hand[start] == hand[index])
                index++;
            nums.add(hand[start]);
            counts.add(index - start);
        }
        for (int i = 0; i < nums.size(); i++) {
            if (counts.get(i) != 0) {
                int needs = counts.get(i);
                for (int j = i+1; j < i+W; j++) {
                    if (j == nums.size())
                        return false;
                    if (nums.get(j-1) + 1 != nums.get(j))
                        return false;
                    if (counts.get(j) < needs)
                        return false;
                    counts.set(j, counts.get(j)-needs);
                }
            }
        }
        return true;
    }
}


class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        if (hand.length % W != 0)
            return false;
        Arrays.sort(hand);
        int[] nums = new int[hand.length], counts = new int[hand.length];
        int index = 0, ptr = 0;;
        while (index < hand.length) {
            int start = index++;
            while (index < hand.length && hand[start] == hand[index])
                index++;
            nums[ptr] = hand[start];
            counts[ptr++] = index - start;
        }
        if (ptr < W)
            return false;
        int need = 0;
        for (int i = 0; i < ptr; i++) {
            if (i >= W)
                need -= counts[i-W];
            if (need != 0 && nums[i-1] + 1 != nums[i])
                return false;
            if (counts[i] < need)
                return false;
            int temp = counts[i];
            counts[i] -= need;
            need = temp;
        }
        return need == counts[ptr-W];
    }
}

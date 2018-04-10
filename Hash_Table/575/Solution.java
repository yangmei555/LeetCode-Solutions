class Solution {
    public int distributeCandies(int[] candies) {
        int len = candies.length / 2;
        HashSet<Integer> set = new HashSet<>();
        for (int i : candies) {
            set.add(i);
            if (set.size() == len)
                return len;
        }
        return set.size();
    }
}


class Solution {
    public int distributeCandies(int[] candies) {
        boolean[] index = new boolean[200001];
        int len = candies.length / 2;
        int count = 0;
        for (int i : candies) {
            if (index[i + 100000] == false) {
                index[i + 100000] = true;
                count++;
                if (count == len)
                    return count;
            }
        }
        return count;
    }
}

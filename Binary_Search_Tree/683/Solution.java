class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < flowers.length; i++) {
            Integer floor = set.floor(flowers[i]);
            if (floor != null && flowers[i] - floor == k + 1)
                return i + 1;
            Integer ceiling = set.ceiling(flowers[i]);
            if (ceiling != null && ceiling - flowers[i] == k + 1)
                return i + 1;
            set.add(flowers[i]);
        }
        return -1;
    }
}


class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < flowers.length; i++) {
            int place = flowers[i];
            int index = Collections.binarySearch(list, place);
            index = - index - 1;
            if (index < list.size() && list.get(index) - place == k + 1)
                return i + 1;
            if (index-1 >= 0 && place - list.get(index-1) == k + 1)
                return i + 1;
            list.add(index, place);
        }
        return -1;
    }
}


// search forwards and backwards for k slots 
class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        boolean[] record = new boolean[flowers.length+1];
        record[flowers[0]] = true;
        for (int i = 1; i < flowers.length; i++) {
            int place = flowers[i];
            if (place + k + 1 < record.length) {
                for (int j = place+1; j <= place + k + 1; j++) {
                    if (record[j]) {
                        if (j == place + k + 1)
                            return i + 1;
                        else
                            break;
                    }
                }
            }
            if (place - k - 1 >= 0) {
                for (int j = place-1; j >= place - k - 1; j--) {
                    if (record[j]) {
                        if (j == place - k - 1)
                            return i + 1;
                        else
                            break;
                    }
                }
            }
            record[place] = true;
        }
        return -1;
    }
}


// using deque 
class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        int[] days = new int[flowers.length];
        for (int i = 0; i < flowers.length; i++)
            days[flowers[i]-1] = i + 1;
        int[] deque = new int[flowers.length];
        int p1 = 0, p2 = 0, res = Integer.MAX_VALUE;
        for (int i = 0; i < days.length; i++) {
            if (i >= k + 1) {
                int min = p1 == p2 ? Integer.MAX_VALUE : deque[p1];
                if (min > days[i] && min > days[i-k-1]) 
                    res = Math.min(res, Math.max(days[i-k-1], days[i]));
            }
            while (p1 != p2 && deque[p2-1] > days[i])
                p2--;
            deque[p2++] = days[i];
            if (i >= k && deque[p1] == days[i-k])
                p1++;
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}


// another way of writing deque, storing indices 
class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        int[] days = new int[flowers.length];
        for (int i = 0; i < flowers.length; i++)
            days[flowers[i]-1] = i + 1;
        int[] deque = new int[flowers.length];
        int p1 = 0, p2 = 0, res = Integer.MAX_VALUE;
        for (int i = 0; i < days.length; i++) {
            if (i >= k + 1) {
                int min = p1 == p2 ? Integer.MAX_VALUE : days[deque[p1]];
                if (min > days[i] && min > days[i-k-1]) 
                    res = Math.min(res, Math.max(days[i-k-1], days[i]));
            }
            while (p1 != p2 && days[deque[p2-1]] > days[i])
                p2--;
            deque[p2++] = i;
            if (deque[p1] < i-k+1)
                p1++;
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}


// as long as found an element smaller than its two sides, update the search range 
class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        int[] days = new int[flowers.length];
        for (int i = 0; i < flowers.length; i++)
            days[flowers[i]-1] = i + 1;
        int res = Integer.MAX_VALUE;
        int left = 0, right = k + 1;
        while (right < days.length) {
            int index = left + 1;
            while (index < right && days[index] > days[left] && days[index] > days[right]) 
                index++;
            if (index == right) 
                res = Math.min(res, Math.max(days[left], days[right]));
            left = index;
            right = left + k + 1;
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}

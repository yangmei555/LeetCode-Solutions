class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        int res = 0, index = 0;
        Arrays.sort(heaters);
        Arrays.sort(houses);
        for (int h : houses) {
            int left = index, right = heaters.length;
            while (left < right) {
                int mid = (left + right) / 2;
                if (heaters[mid] < h)
                    left = mid + 1;
                else
                    right = mid;
            }
            index = left;
            if (index == heaters.length || heaters[index] != h) {
                int cand1 = index == heaters.length ? Integer.MAX_VALUE : heaters[index] - h;
                int cand2 = index == 0 ? Integer.MAX_VALUE : h - heaters[index-1];
                int cand = cand1 < cand2 ? cand1 : cand2;
                res = res > cand ? res : cand;
            }
        }
        return res;
    }
}


class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        int res = 0, index = 0;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        for (int h : houses) {
            index = Arrays.binarySearch(heaters, index, heaters.length, h);
            if (index < 0) {
                index = ~index;
                int cand1 = index == heaters.length ? Integer.MAX_VALUE : heaters[index] - h;
                int cand2 = index == 0 ? Integer.MAX_VALUE : h - heaters[index-1];
                int cand = cand1 < cand2 ? cand1 : cand2;
                res = res > cand ? res : cand;
            }
        }
        return res;
    }
}


class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int i = 0, res = 0;
        for (int hou : houses) {
            while (i + 1 < heaters.length && hou - heaters[i] > heaters[i+1] - hou)
                i++;
            int dist = hou > heaters[i] ? hou - heaters[i] : heaters[i] - hou;
            res = res > dist ? res : dist;
        }
        return res;
    }
}

class Solution {
    public int hIndex(int[] citations) {
        if (citations.length == 0)
            return 0;
        int len = citations.length;
        int left = 0, right = len - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (citations[mid] == len-mid)
                return len-mid;
            else if (citations[mid] > len-mid)
                right = mid;
            else
                left = mid+1;
        }
        return citations[left] >= len-left ? len-left : len-left-1;
    }
}


class Solution {
    public int hIndex(int[] citations) {
        if (citations.length == 0)
            return 0;
        int len = citations.length;
        int left = 0, right = len-1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (citations[mid] == len-mid)
                return len-mid;
            else if (citations[mid] > len-mid)
                right = mid-1;
            else
                left = mid+1;
        }
        return len-left;
    }
}

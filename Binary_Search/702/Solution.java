class Solution {
    public int search(ArrayReader reader, int target) {
        int left = 0, right = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int value = reader.get(mid);
            if (value > target)
                right = mid - 1;
            else if (value < target)
                left = mid + 1;
            else
                return mid;
        } 
        return -1;
    }
}


// more efficient binary search 
class Solution {
    public int search(ArrayReader reader, int target) {
        int right = 1;
        while (reader.get(right) < target)
            right *= 2;
        int left = right / 2;
        while (left <= right) {
            int mid = (left + right) / 2;
            int value = reader.get(mid);
            if (value > target)
                right = mid - 1;
            else if (value < target)
                left = mid + 1;
            else
                return mid;
        }
        return -1;
    }
}

class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int count = 0;
        for (int i = citations.length-1; i >= 0; i--) {
            count++;
            if (citations[i] == count) {
                return count;
            } else if (citations[i] < count) {
                return count - 1;
            }
        }
        return count;
    }
}


class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int len = citations.length;
        for (int i = citations.length-1; i >= 0; i--) {
            if (citations[i] == len - i) {
                return len - i;
            } else if (citations[i] < len - i) {
                return len - i - 1;
            }
        }
        return len;
    }
}


class Solution {
    public int hIndex(int[] citations) {
        int len = citations.length;
        int[] index = new int[len + 1];
        for (int c : citations) {
            c = c > len ? len : c;
            index[c]++;
        }
        int i = len + 1;
        for (int count = 0; count < i; count += index[i])
            i--;
        return i;
    }
}

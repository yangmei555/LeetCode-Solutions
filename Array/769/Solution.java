class Solution {
    public int maxChunksToSorted(int[] arr) {
        if (arr.length == 0)
            return 0;
        int next = 0, res = 0, temp = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == next)
                res++;
            if (arr[i] != i) {
                while (arr[i] != i) {
                    next = arr[i]+1 > next ? arr[i]+1 : next;
                    temp = arr[i];
                    arr[i] = arr[temp];
                    arr[temp] = temp;
                }
            } else {
                next = i+1 > next ? i+1 : next;
            }
        }
        return res;
    }
}


class Solution {
    public int maxChunksToSorted(int[] arr) {
        if (arr.length == 0)
            return 0;
        int max = 0, res = 0;
        for (int i = 0; i < arr.length; i++) {
            max = max > arr[i] ? max : arr[i];
            if (i == max)
                res++;
        }
        return res;
    }
}

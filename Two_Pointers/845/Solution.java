class Solution {
    public int longestMountain(int[] A) {
        int res = 0;
        int index = 0;
        while (index < A.length) {
            int temp1 = index++;
            while (index < A.length && A[index-1] < A[index])
                index++;
            if (temp1 + 1 == index)
                continue;
            int temp2 = index - 1;
            while (index < A.length && A[index-1] > A[index])
                index++;
            if (temp2 + 1 == index)
                continue;
            res = Math.max(res, index - temp1);
            index--;
        }
        return res;
    }
}


// a more concise style 
class Solution {
    public int longestMountain(int[] A) {
        int res = 0;
        int index = 0;
        while (index < A.length) {
            int start = index++;
            while (index < A.length && A[index-1] < A[index])
                index++;
            if (start + 1 == index || index == A.length || A[index-1] == A[index])
                continue;
            while (index < A.length && A[index-1] > A[index])
                index++;
            res = Math.max(res, index - start);
            index--;
        }
        return res;
    }
}

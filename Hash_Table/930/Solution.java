class Solution {
    public int numSubarraysWithSum(int[] A, int S) {
        int[] prefix = new int[A.length+1];
        for (int i = 1; i < prefix.length; i++)
            prefix[i] = prefix[i-1] + A[i-1];
        int[] map = new int[A.length+1];
        int res = 0;
        for (int i = 0; i < prefix.length; i++) {
            res += prefix[i] >= S ? map[prefix[i]-S] : 0;
            map[prefix[i]]++;
        }
        return res;
    }
}


// O(1) space solution. this solution expriences lots of bugs at specail cases, such as S == 0 
// or A[] is all 1 array, etc. rewrite lots of times before making it bug free against the OJ. 
class Solution {
    public int numSubarraysWithSum(int[] A, int S) {
        int sum = 0, res = 0;
        int left = 0, right = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i] == 1 ? 1 : 0;
            if (right < i && A[right] == 0)
                right = i;
            if (sum > S) {
                left = right + 1;
                right++;
                while (right < i && A[right] == 0)
                    right++;
                sum--;
            }
            if (right <= i && sum == S) 
                res += right - left + 1;
        }
        return res;
    }
}

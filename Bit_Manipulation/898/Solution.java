class Solution {
    public int subarrayBitwiseORs(int[] A) {
        Set<Integer> set = new HashSet<>();
        int ors = 0, res = 0, cum = 0;
        for (int i = 0; i < A.length; i++) {
            int cand = 0;
            // cum |= A[i];
            for (int j = i; j >= 0; j--) {
                cand |= A[j];
                set.add(cand);
                if ((cand & (cand + 1)) == 0 && cand >= cum)
                    break;
            }
            cum |= A[i];
        }
        return set.size();
    }
}


// sometimes TLE 
class Solution {
    public int subarrayBitwiseORs(int[] A) {
        Set<Integer> set = new HashSet<>(), total = new HashSet<>();
        int res = 0;
        set.add(0);
        for (int a : A) {
            Set<Integer> next = new HashSet<>();
            for (int s : set) {
                next.add(s | a);
                total.add(s | a);
            }
            next.add(a);
            total.add(a);
            set = next;
        }
        return total.size();
    }
}


// dp[j]: A[j] | ... | A[i-1] ( | A[i])
class Solution {
    public int subarrayBitwiseORs(int[] A) {
        Set<Integer> set = new HashSet<>();
        int[] dp = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = i; j >= 0; j--) {
                int update = dp[j] | A[i];
                set.add(update);
                if (update != dp[j]) {
                    dp[j] = update;
                    set.add(dp[j]);
                } else {
                    break;
                }
            }
        }
        return set.size();
    }
}

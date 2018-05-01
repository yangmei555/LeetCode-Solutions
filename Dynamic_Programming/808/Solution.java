class Solution {
    public double soupServings(int N) {
        if (N >= 5000)
            return 1.0;
        Double[][] memo = new Double[N/25+1][N/25+1];
        return helper(N, N, memo);
    }
    
    public double helper(int A, int B, Double[][] memo) {
        if (A <= 0 || B <= 0) {
            if (A <= 0 && B <= 0)
                return 0.5;
            else if (A <= 0)
                return 1;
            else 
                return 0;
        }
        if (memo[A/25][B/25] != null)
            return memo[A/25][B/25];
        memo[A/25][B/25] = 0.25*(helper(A-100,B,memo)+helper(A-75,B-25,memo)+
                            helper(A-50,B-50,memo)+helper(A-25,B-75,memo));
        return memo[A/25][B/25];
    }
}


class Solution {
    public double soupServings(int N) {
        if (N >= 5000)
            return 1.0;
        int quo = N/25 + (N%25 == 0 ? 0 : 1);
        double[][] index = new double[quo+1][quo+1];
        index[0][0] = 0.5;
        double a, b, c, d;
        for (int total = 0; total <= 2 * quo; total++) {
            for (int i = 0; i <= total && i <= quo; i++) {
                int j = total - i;
                if (j > quo)
                    continue;
                if (i-4 <= 0) 
                    a = (j <= 0 ? 0.25 : 1);  
                else
                    a = (j <= 0 ? 0 : index[i-4][j]);
                if (i-3 <= 0)
                    b = (j-1 <= 0 ? 0.25 : 1);
                else
                    b = (j-1 <= 0 ? 0 : index[i-3][j-1]);
                if (i-2 <= 0)
                    c = (j-2 <= 0 ? 0.25 : 1);
                else
                    c = (j-2 <= 0 ? 0 : index[i-2][j-2]);
                if (i-1 <= 0)
                    d = (j-3 <= 0 ? 0.25 : 1);
                else
                    d = (j-3 <= 0 ? 0 : index[i-1][j-3]);
                index[i][j] = 0.25*(a+b+c+d);
            }
        }
        return index[quo][quo];
    }
}


class Solution {
    public double soupServings(int N) {
        if (N >= 5000)
            return 1;
        HashMap<String, Double> memo = new HashMap<>();
        return helper(N, N, memo);
    }
    
    public double helper(int A, int B, HashMap<String, Double> memo) {
        if (A <= 0 || B <= 0) {
            if (A <= 0 && B <= 0)
                return 0.5;
            else if (A <= 0)
                return 1;
            else 
                return 0;
        }
        String str = A + " " + B;
        if (memo.containsKey(str))
            return memo.get(str);
        memo.put(str, 0.25*(helper(A-100,B,memo)+helper(A-75,B-25,memo)+
                            helper(A-50,B-50,memo)+helper(A-25,B-75,memo)));
        return memo.get(str);
    }
}

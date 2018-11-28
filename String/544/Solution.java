class Solution {
    public String findContestMatch(int n) {
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            list.add(new StringBuilder(i + ""));
        helper(list, n);
        return list.get(0).toString();
    }
    
    public void helper(List<StringBuilder> list, int n) {
        if (n == 1)
            return;
        int size = list.size();
        for (int i = 0; i < size/2; i++) {
            StringBuilder s1 = list.get(i);
            StringBuilder s2 = list.get(list.size()-1);
            list.remove(list.size()-1);
            s1.insert(0, '(');
            s1.append("," + s2.toString() + ")");
        }
        helper(list, n/2);
    }
}


class Solution {
    public String findContestMatch(int n) {
        StringBuilder[] list = new StringBuilder[n];
        for (int i = 1; i <= n; i++)
            list[i-1] = new StringBuilder(i + "");
        helper(list, n);
        return list[0].toString();
    }
    
    public void helper(StringBuilder[] list, int n) {
        if (n == 1)
            return;
        for (int i = 0; i < n/2; i++) {
            list[i].insert(0, '(');
            list[i].append("," + list[n-1-i].toString() + ")");
        }
        helper(list, n/2);
    }
}


class Solution {
    public String findContestMatch(int n) {
        String[] list = new String[n];
        for (int i = 1; i <= n; i++)
            list[i-1] = i + "";
        helper(list, n);
        return list[0];
    }
    
    public void helper(String[] list, int n) {
        if (n == 1)
            return;
        for (int i = 0; i < n/2; i++) 
            list[i] = "(" + list[i] + "," + list[n-1-i] + ")";
        helper(list, n/2);
    }
}


class Solution {
    public String findContestMatch(int n) {
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) 
            res[i] = n/(i-(i&(i-1)))+1 - res[i&(i-1)];
        return helper(res, 0, n-1);
    }
    
    public String helper(int[] res, int left, int right) {
        if (left == right)
            return res[left] + "";
        int mid = (left+right)/2;
        return "(" + helper(res, left, mid) + "," + helper(res, mid+1, right) + ")";
    }
}


class Solution {
    public String findContestMatch(int n) {
        int[] res = new int[n];
        res[0] = 1;
        return helper(res, 0, n-1, n);
    }
    
    public String helper(int[] res, int left, int right, int n) {
        if (left == right) {
            if (left != 0)
                res[left] = n/(left-(left&(left-1)))+1 - res[left&(left-1)];
            return res[left] + "";
        }
        int mid = (left+right)/2;
        return "(" + helper(res, left, mid, n) + "," + helper(res, mid+1, right, n) + ")";
    }
}


class Solution {
    public String findContestMatch(int n) {
        int[] teams = new int[n];
        teams[0] = 1;
        for (int i = 1; i < n; i++) {
            int prev = i & (i-1);
            teams[i] = n / (i - prev) + 1 - teams[prev];
        }
        return helper(teams, 0, n-1);
    }
    
    public String helper(int[] teams, int left, int right) {
        if (left == right) {
            return teams[left] + "";
        } else {
            int mid = (left + right) / 2;
            return "(" + helper(teams, left, mid) + "," + helper(teams, mid+1, right) + ")";
        }
    }
}

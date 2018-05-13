class Solution {
    public String fractionAddition(String expression) {
        if (expression.length() == 0)
            return "";
        int nu = 0, de = 1, presign = 1, start = 0, end = 0;
        char[] ch = expression.toCharArray();
        while (end <= ch.length) {
            if (end == ch.length || ch[end] == '+' || ch[end] == '-') {
                if (end != 0) {
                    int temp1 = 0, temp2 = 0, temp3 = 0;
                    for (int j = start; j < end; j++) {
                        if (ch[j] == '/') {
                            temp1 = temp3;
                            temp3 = 0;
                            continue;
                        }
                        temp3 = temp3 * 10 + ch[j] - '0';
                    }
                    temp2 = temp3;
                    nu = nu * temp2 + presign * de * temp1;
                    de *= temp2;
                }
                presign = end < ch.length && ch[end] == '+' ? 1 : -1;
                start = end + 1;
            } 
            end++;
        }
        if (nu == 0)
            return "0/1";
        presign = nu > 0 ? 1 : -1;
        nu *= presign;
        int gcd = helper(nu, de);
        return presign * nu / gcd + "/" + de / gcd;
    }
    
    public int helper(int a, int b) {
        while (a % b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return b;
    }
}


class Solution {
    public String fractionAddition(String expression) {
        if (expression.length() == 0)
            return "";
        int nu = 0, de = 1, presign = 1, start = 0, end = 0;
        char[] ch = expression.toCharArray();
        while (end <= ch.length) {
            if (end == ch.length || ch[end] == '+' || ch[end] == '-') {
                if (end != 0) {
                    int temp1 = 0, temp2 = 0, temp3 = 0;
                    for (int j = start; j < end; j++) {
                        if (ch[j] == '/') {
                            temp1 = temp3;
                            temp3 = 0;
                            continue;
                        }
                        temp3 = temp3 * 10 + ch[j] - '0';
                    }
                    temp2 = temp3;
                    nu = nu * temp2 + presign * de * temp1;
                    de *= temp2;
                    if (nu == 0)
                        de = 1;
                    else {
                        presign = nu > 0 ? 1 : -1;
                        nu *= presign;
                        int gcd = helper(nu, de);
                        nu = presign * nu / gcd;
                        de = de / gcd;
                    }
                }
                presign = end < ch.length && ch[end] == '+' ? 1 : -1;
                start = end + 1;
            } 
            end++;
        }
        return nu + "/" + de;
    }
    
    public int helper(int a, int b) {
        while (a % b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return b;
    }
}

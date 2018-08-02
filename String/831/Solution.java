class Solution {
    public String maskPII(String S) {
        if (S.indexOf('@') != -1) {
            S = S.toLowerCase();
            return S.charAt(0) + "*****" + S.substring(S.indexOf('@')-1);
        } else {
            int digits = 0;
            for (int i = 0; i < S.length(); i++) {
                if (S.charAt(i) >= '0' && S.charAt(i) <= '9')
                    digits++;
            }
            char[] last4 = new char[4];
            int index = 3;
            for (int i = S.length()-1; i >= 0; i--) {
                if (S.charAt(i) >= '0' && S.charAt(i) <= '9') {
                    last4[index] = S.charAt(i);
                    if (--index == -1)
                        break;
                }
            }
            // if (digits == 10) {
            //     return "***-***-" + String.valueOf(last4);
            // } else {
            //     String res = "+";
            //     for (int i = 0; i < digits - 10; i++)
            //         res += '*';
            //     return res + "-***-***-" + String.valueOf(last4);
            // }
            String[] strs = new String[]{"", "+*-", "+**-", "+***-"};
            return strs[digits-10] + "***-***-" + String.valueOf(last4); 
        }
    }
}

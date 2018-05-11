class Solution {
    public String originalDigits(String s) {
        char[] ch = s.toCharArray();
        int[] index = new int[26], res = new int[10];
        for (char c : ch)
            index[c-'a']++;
        String[] digits = new String[]{"zero", "one", "two", "three", "four", "five", 
                                        "six", "seven", "eight", "nine"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digits.length; i++) {
            if (i % 2 == 1)   // i should be zero, two, four, six, eight, which have unique chars
                continue;
            char[] temp = digits[i].toCharArray();
            int min = Integer.MAX_VALUE;
            for (char c : temp)
                min = min < index[c-'a'] ? min : index[c-'a'];
            res[i] = min;
            for (char c : temp)
                index[c-'a'] -= min;
        }
        for (int i = 0; i < digits.length; i++) {
            if (i % 2 == 0)
                continue;
            char[] temp = digits[i].toCharArray();
            int min = Integer.MAX_VALUE;
            for (char c : temp)
                min = min < index[c-'a'] ? min : index[c-'a'];
            res[i] = min;
            for (char c : temp)
                index[c-'a'] -= min;
        }
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i]; j++)
                sb.append(i);
        }
        return sb.toString();
    }
}

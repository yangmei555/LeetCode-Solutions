class Solution {
    public String nextClosestTime(String time) {
        char[] digits = time.toCharArray();
        int len = 1;
        len += (digits[0] == digits[1] ? 0 : 1);
        if (digits[3] != digits[0] && digits[3] != digits[1]) {
            digits[len] = digits[3];
            len++;
        }
        if (digits[4] != digits[0] && digits[4] != digits[1] && digits[4] != digits[2]) {
            digits[len] = digits[4];
            len++;
        }
        char[] res = null, temp = new char[4], cand = Arrays.copyOf(digits, len);
        digits = time.toCharArray();
        digits[2] = digits[3];
        digits[3] = digits[4];
        int num = convert(digits), least = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            if (cand[i] > '2')
                continue;
            temp[0] = cand[i];
            for (int j = 0; j < len; j++) {
                temp[1] = cand[j];
                for (int k = 0; k < len; k++) {
                    if (cand[k] > '5')
                        continue;
                    temp[2] = cand[k];
                    for (int m = 0; m < len; m++) {
                        temp[3] = cand[m];
                        int compare = convert(temp);
                        if (compare == -1)
                            continue;
                        int diff = compare > num ? compare-num : compare+24*60-num;
                        if (diff < least) {
                            least = diff;
                            res = temp.clone();
                        }
                    }
                }
            }
        }
        return res[0] + "" + res[1] + ":" + res[2] + "" + res[3];
    }
    
    public int convert(char[] ch) {
        if ((ch[0]-'0')*10+(ch[1]-'0') >= 24)
            return -1;
        return ((ch[0]-'0')*10+(ch[1]-'0'))*60+((ch[2]-'0')*10+(ch[3]-'0'));
    }
}


class Solution {
    public String nextClosestTime(String time) {
        Set<Character> set = new HashSet<>();
        char[] ch = time.toCharArray();
        set.add(ch[0]);
        set.add(ch[1]);
        set.add(ch[3]);
        set.add(ch[4]);
        char[] cand = new char[set.size()];
        int len = 0;
        for (char c : set) {
            cand[len] = c;
            len++;
        }
        Arrays.sort(cand);
        ch[2] = ch[3];
        ch[3] = ch[4];
        char[] res = Arrays.copyOf(ch, 4);
        int i = cand.length-1;
        while (i >= 0 && cand[i] > ch[3])
            i--;
        res[3] = cand[(i+1)%cand.length];
        if (res[3] > ch[3])
            return res[0] + "" + res[1] + ":" + res[2] + "" + res[3];
        i = cand.length-1;
        while (i >= 0 && cand[i] > ch[2])
            i--;
        if (i+1 != cand.length && cand[i+1] <= '5')
            res[2] = cand[i+1];
        else
            res[2] = cand[0];
        if (res[2] > ch[2])
            return res[0] + "" + res[1] + ":" + res[2] + "" + res[3];
        i = cand.length-1;
        while (i >= 0 && cand[i] > ch[1])
            i--;
        if (i+1 != cand.length && (ch[0] != '2' || cand[i+1] < '4'))
            res[1] = cand[i+1];
        else
            res[1] = cand[0];
        if (res[1] > ch[1])
            return res[0] + "" + res[1] + ":" + res[2] + "" + res[3];
        i = cand.length-1;
        while (i >= 0 && cand[i] > ch[1])
            i--;
        if (i+1 != cand.length && cand[i+1] < '3')
            res[0] = cand[i+1];
        else
            res[0] = cand[0];
        return res[0] + "" + res[1] + ":" + res[2] + "" + res[3];
    }
}


class Solution {
    public String nextClosestTime(String time) {
        char[] ch = new char[4];
        for (int i = 0; i < ch.length; i++)
            ch[i] = time.charAt(i + i/2);
        char globalMin = Character.MAX_VALUE;
        for (char c : ch)
            globalMin = (char)Math.min(globalMin, c);
        for (int i = ch.length-1; i >= 0; i--) {
            char min = Character.MAX_VALUE;
            for (char c : ch) {
                if (c > ch[i])
                    min = (char)Math.min(min, c);
            }
            if (min == Character.MAX_VALUE)
                continue;
            if (i == 2 && min > '5')
                continue;
            if (i == 1 && ch[0] == '2' && min > '3')
                continue;
            if (i == 0 && min > '2')
                continue;
            ch[i] = min;
            for (int j = i+1; j < ch.length; j++)
                ch[j] = globalMin;
            StringBuilder sb = new StringBuilder();
            sb.append(ch[0]).append(ch[1]).append(':').append(ch[2]).append(ch[3]);
            return sb.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(globalMin).append(globalMin).append(':').append(globalMin).append(globalMin);
        return sb.toString();
    }
}

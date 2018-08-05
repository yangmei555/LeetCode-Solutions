class Solution {
    public boolean reorderedPowerOf2(int N) {
        char[] ch = String.valueOf(N).toCharArray();
        Arrays.sort(ch);
        return helper(ch, 0);
    }
    
    public boolean helper(char[] ch, int index) {
        if (index == ch.length) {
            int value = Integer.valueOf(String.valueOf(ch));
            return (value & (value - 1)) == 0;
        } else {
            for (int i = index; i < ch.length; i++) {
                // this method will cause duplication 
                // because after some swaps the array is no longer sorted 
                if (i != index && ch[i-1] == ch[i])
                    continue;
                if (ch[i] == '0' && index == 0)
                    continue;
                char temp = ch[i];
                ch[i] = ch[index];
                ch[index] = temp;
                if (helper(ch, index + 1))
                    return true;
                temp = ch[i];
                ch[i] = ch[index];
                ch[index] = temp;
            }
        }
        return false;
    }
}


class Solution {
    public boolean reorderedPowerOf2(int N) {
        char[] ch = String.valueOf(N).toCharArray();
        char[] build = new char[ch.length];
        boolean[] used = new boolean[ch.length];
        Arrays.sort(ch);
        return helper(ch, build, 0, used);
    }

    // be careful how to generate each permution without duplication
    public boolean helper(char[] ch, char[] build, int index, boolean[] used) {
        if (index == build.length) {
            int value = Integer.valueOf(String.valueOf(build));
            return (value & (value - 1)) == 0;
        } else {
            for (int i = 0; i < ch.length; i++) {
                if (used[i] || (i != 0 && ch[i-1] == ch[i] && !used[i-1]))
                    continue;
                if (index == 0 && ch[i] == '0')
                    continue;
                build[index] = ch[i];
                used[i] = true;
                if (helper(ch, build, index+1, used))
                    return true;
                used[i] = false;
            }
        }
        return false;
    }
}


class Solution {
    public boolean reorderedPowerOf2(int N) {
        char[] ch = String.valueOf(N).toCharArray();
        Arrays.sort(ch);
        return helper(ch, 0);
    }
    
    public boolean helper(char[] ch, int index) {
        if (index == ch.length) {
            int value = Integer.valueOf(String.valueOf(ch));
            return (value & (value - 1)) == 0;
        } else {
            for (int i = index; i < ch.length; i++) {
                if (i != index && ch[index] == ch[i])
                    continue;
                if (ch[i] == '0' && index == 0)
                    continue;
                char temp = ch[i];
                ch[i] = ch[index];
                ch[index] = temp;
                // pass by value, do not pass by reference
                if (helper(ch.clone(), index + 1))
                    return true;
            }
        }
        return false;
    }
}


class Solution {
    public boolean reorderedPowerOf2(int N) {
        int[] distribution = helper(N);
        for (int i = 0; i < 31; i++) {
            int[] ret = helper(1 << i);
            int j = 0;
            while (j < ret.length) {
                if (ret[j] != distribution[j])
                    break;
                j++;
            }
            if (j == ret.length)
                return true;
        }
        return false;
    }
    
    public int[] helper(int N) {
        int[] res = new int[10];
        for (char c : String.valueOf(N).toCharArray())
            res[c-'0']++;
        return res;
    }
}


class Solution {
    public boolean reorderedPowerOf2(int N) {
        int[] distribution = helper(N);
        int n = 1;
        while (true) {
            int len1 = String.valueOf(n).length(), len2 = String.valueOf(N).length();
            if (len1 > len2) {
                break;
            } else if (len1 == len2) {
                int[] ret = helper(n);
                int j = 0;
                while (j < ret.length) {
                    if (ret[j] != distribution[j])
                        break;
                    j++;
                }
                if (j == ret.length)
                    return true;
            }
            n <<= 1;
        }
        return false;
    }
    
    public int[] helper(int N) {
        int[] res = new int[10];
        for (char c : String.valueOf(N).toCharArray())
            res[c-'0']++;
        return res;
    }
}


class Solution {
    public boolean reorderedPowerOf2(int N) {
        char[] ch = String.valueOf(N).toCharArray();
        Arrays.sort(ch);
        int n = 1;
        while (true) {
            char[] temp = String.valueOf(n).toCharArray();
            if (temp.length > ch.length) {
                break;
            } else if (temp.length == ch.length) {
                Arrays.sort(temp);
                if (Arrays.equals(temp, ch))
                    return true;
            }
            n <<= 1;
        }
        return false;
    }
    
    public int[] helper(int N) {
        int[] res = new int[10];
        for (char c : String.valueOf(N).toCharArray())
            res[c-'0']++;
        return res;
    }
}

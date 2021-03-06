class Solution {
    public String getPermutation(int n, int k) {
        int[] fac = new int[n];
        fac[0] = 1;
        for (int i = 1; i < n; i++)
            fac[i] = fac[i-1] * (i+1);
        char[] ch = new char[n];
        char temp = 0;
        for (int i = 0; i < n; i++)
            ch[i] = (char)('1' + i);
        while (k != 0) {
            int i = 0;
            for (; i < fac.length; i++) 
                if (fac[i] > k)
                    break;
            i--;
            k -= fac[i];
            int left = n - i - 1, right = n - 1;
            while (left < right) {
                temp = ch[left];
                ch[left] = ch[right];
                ch[right] = temp;
                left++;
                right--;
            }
            if (k != 0) {
                i = ch.length-2;
                while (i >= 0 && ch[i] > ch[i+1]) 
                    i--;
                int j = i + 1;
                while (j < ch.length && ch[i] < ch[j])
                    j++;
                j--;
                temp = ch[i];
                ch[i] = ch[j];
                ch[j] = temp;
                left = i+1;
                right = n-1;
                while (left < right) {
                    temp = ch[left];
                    ch[left] = ch[right];
                    ch[right] = temp;
                    left++;
                    right--;
                }
            }
        }
        return new String(ch);
    }
}


class Solution {
    public String getPermutation(int n, int k) {
        int[] fac = new int[n];
        fac[0] = 1;
        for (int i = 1; i < n; i++)
            fac[i] = fac[i-1] * (i+1);
        char[] ch = new char[n];
        char temp = 0;
        for (int i = 0; i < n; i++)
            ch[i] = (char)('1' + i);
        k--;
        while (k != 0) {
            int i = ch.length-2;
            while (i >= 0 && ch[i] > ch[i+1]) 
                i--;
            int j = i + 1;
            while (j < ch.length && ch[i] < ch[j])
                j++;
            j--;
            temp = ch[i];
            ch[i] = ch[j];
            ch[j] = temp;
            int left = i+1, right = n-1;
            while (left < right) {
                temp = ch[left];
                ch[left] = ch[right];
                ch[right] = temp;
                left++;
                right--;
            }
            k--;
        }
        return new String(ch);
    }
}


class Solution {
    public String getPermutation(int n, int k) {
        int[] fac = new int[n];
        fac[0] = 1;
        for (int i = 1; i < n; i++)
            fac[i] = fac[i-1] * (i+1);
        List<Integer> digits = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            digits.add(i);
        k--;
        StringBuilder sb = new StringBuilder();
        for (int i = n; i >= 2; i--) {
            int index = k / fac[i-2];
            sb.append(digits.get(index));
            digits.remove(index);
            k %= fac[i-2];
        }
        sb.append(digits.get(0));
        return sb.toString();
    }
}


// use binary indexed tree to achieve O(log n) (can be viewed as constant time) removal 
class Solution {
    public String getPermutation(int n, int k) {
        int[] bit = new int[n+1];
        int fac = 1;
        for (int i = 1; i < n; i++)
            fac *= i;
        k--;
        StringBuilder sb = new StringBuilder();
        for (int i = n-1; i >= 0; i--) {
            int digit = k / fac;
            int pos = helper(bit, digit);
            sb.append(pos);
            k %= fac;
            if (i != 0)
                fac /= i;
            for (int j = pos; j < bit.length; j += j&-j)
                bit[j]++;
        }
        return sb.toString();
    }
    
    // find the last position where (total elements - used elements <= digit), 
    // then return the next position of it 
    public int helper(int[] bit, int digit) {
        int res = 0, sum = 0;
        for (int i = 30; i >= 0; i--) {
            res += 1 << i;
            if (res >= bit.length || res - (sum + bit[res]) > digit)
                res -= 1 << i;
            else
                sum += bit[res];
        }
        return res + 1;
    }
}

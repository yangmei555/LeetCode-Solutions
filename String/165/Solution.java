class Solution {
    public int compareVersion(String version1, String version2) {
        String[] str1 = version1.split("\\.");
        String[] str2 = version2.split("\\.");
        int i = 0;
        while (i < str1.length && i < str2.length) {
            int diff = Integer.parseInt(str1[i]) - Integer.parseInt(str2[i]);
            if (diff > 0)
                return 1;
            else if (diff < 0)
                return -1;
            i++;
        }
        if (i == str1.length && i == str2.length) {
            return 0;
        } else if (i == str1.length) {
            for (int j = i; j < str2.length; j++)
                if (Integer.parseInt(str2[j]) != 0)
                    return -1;
            return 0;
        } else {
            for (int j = i; j < str1.length; j++)
                if (Integer.parseInt(str1[j]) != 0)
                    return 1;
            return 0;
        }
    }
}


class Solution {
    public int compareVersion(String version1, String version2) {
        char[] v1 = version1.toCharArray();
        char[] v2 = version2.toCharArray();
        int n1 = 0, n2 = 0;
        int i = 0, j = 0;
        while (i < v1.length && j < v2.length) {
            if (v1[i] == '.' && v2[j] == '.') {
                if (n1 != n2)
                    return n1 > n2 ? 1 : -1;
                n1 = n2 = 0;
            } else if (v1[i] == '.') {
                return -1;
            } else if (v2[j] == '.') {
                return 1;
            } else {
                if (n1 == 0 && v1[i] == '0' && i < v1.length-1 && v1[i+1] != '.') {
                    while (i < v1.length && v1[i] == '0')
                        i++;
                    if (i != v1.length && v1[i] == '.')
                        i--;
                }
                if (n2 == 0 && v2[j] == '0' && j < v2.length-1 && v2[j+1] != '.') {
                    while (j < v2.length && v2[j] == '0')
                        j++;
                    if (j != v2.length && v2[j] == '.')
                        j--;
                }
                if (i != v1.length)
                    n1 = n1 * 10 + v1[i] - '0';
                if (j != v2.length)
                    n2 = n2 * 10 + v2[j] - '0';
            }
            i++;
            j++;
        }
        if (i == v1.length && j == v2.length) {
            if (n1 > n2)
                return 1;
            else if (n1 < n2)
                return -1;
            else
                return 0;
        } else if (i == v1.length) {
            for (int k = j; k < v2.length; k++) {
                if (v2[k] == '.') {
                    if (n1 != n2)
                        return n1 > n2 ? 1 : -1;
                    else
                        n1 = 0;
                    n2 = 0;
                } else {
                    n2 = n2 * 10 + v2[k] - '0';
                }
            }
            if (n1 == n2)
                return 0;
            else 
                return n1 > n2 ? 1 : -1;
        } else {
            for (int k = i; k < v1.length; k++) {
                if (v1[k] == '.') {
                    if (n1 != n2)
                        return n1 > n2 ? 1 : -1;
                    else
                        n2 = 0;
                    n1 = 0;
                } else {
                    n1 = n1 * 10 + v1[k] - '0';
                }
            }
            if (n1 == n2)
                return 0;
            else 
                return n1 > n2 ? 1 : -1;
        }
    }
}


class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int len = v1.length > v2.length ? v1.length : v2.length;
        for (int i = 0; i < len; i++) {
            int n1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int n2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
            if (n1 != n2)
                return n1 > n2 ? 1 : -1;
        }
        return 0;
    }
}


class Solution {
    public int compareVersion(String version1, String version2) {
        char[] v1 = version1.toCharArray();
        char[] v2 = version2.toCharArray();
        int i = 0, j = 0;
        while (i < v1.length || j < v2.length) {
            int n1 = 0, n2 = 0;
            while (i < v1.length && v1[i] != '.') {
                n1 = n1 * 10 + v1[i] - '0';
                i++;
            }
            while (j < v2.length && v2[j] != '.') {
                n2 = n2 * 10 + v2[j] - '0';
                j++;
            }
            if (n1 != n2)
                return n1 > n2 ? 1 : -1;
            i++;
            j++;
        }
        return 0;
    }
}

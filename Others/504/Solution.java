class Solution {
    public String convertToBase7(int num) {
        if (num == 0)
            return "0";
        boolean neg = num < 0 ? true : false;
        num = num < 0 ? -num : num;
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(num % 7);
            num = (num - num % 7) / 7;
        }
        sb.reverse();
        return (neg ? "-" : "") + sb.toString();
    }
}


class Solution {
    public String convertToBase7(int num) {
        return Integer.toString(num, 7);
    }
}


class Solution {
    public String convertToBase7(int num) {
        if (num / 7 == 0)
            return "" + num;
        boolean neg = num < 0 ? true : false;
        num = neg ? -num : num;
        return ((neg ? "-" : "") + convertToBase7(num/7)) + num % 7;
    }
}

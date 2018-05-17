class Solution {
    public String validIPAddress(String IP) {
        if (IP.indexOf(".") != -1)
            return checkIPv4(IP);
        else 
            return checkIPv6(IP); 
    }
    
    public String checkIPv4(String IP) {
        String[] str = IP.split("\\.");
        if (str.length != 4 || IP.charAt(IP.length()-1) == '.')
            return "Neither";
        for (int i = 0; i < 4; i++) {
            String s = str[i];
            if (s.length() > 3 || s.length() == 0)
                return "Neither";
            int num = 0;
            for (char c : s.toCharArray())
                if (c >= '0' && c <= '9')
                    num = num * 10 + c - '0';
                else
                    return "Neither";
            if (num > 255 || (s.charAt(0) == '0' && s.length() != 1))
                return "Neither";
        }
        return "IPv4";
    }
    
    public String checkIPv6(String IP) {
        String[] str = IP.split(":");
        if (str.length != 8 || IP.charAt(IP.length()-1) == ':')
            return "Neither";
        for (int i = 0; i < 8; i++) {
            String s = str[i];
            if (s.length() == 0 || s.length() > 4)
                return "Neither";
            for (char c : s.toCharArray())
                if (!(c >= '0' && c <= '9') && !(c >= 'a' && c <= 'f') && !(c >= 'A' && c <= 'F'))
                    return "Neither";
        }
        return "IPv6";
    }
}

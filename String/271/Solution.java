public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) 
            sb.append("@"+str.length()+"@"+str);
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        char[] ch = s.toCharArray();
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < ch.length) {
            int len = 0;
            i++;
            while (i < ch.length && ch[i] != '@') {
                len = len * 10 + ch[i] - '0';
                i++;
            }
            i++;
            res.add(s.substring(i, i+len));
            i += len;
        }
        return res;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));


public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) 
            sb.append(str.length()+"@"+str);
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        char[] ch = s.toCharArray();
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < ch.length) {
            int len = 0;
            while (i < ch.length && ch[i] != '@') {
                len = len * 10 + ch[i] - '0';
                i++;
            }
            i++;
            res.add(s.substring(i, i+len));
            i += len;
        }
        return res;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));


public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) 
            sb.append(str.length()).append('@').append(str);
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        char[] ch = s.toCharArray();
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < ch.length) {
            int len = 0;
            while (i < ch.length && ch[i] != '@') {
                len = len * 10 + ch[i] - '0';
                i++;
            }
            i++;
            res.add(s.substring(i, i+len));
            i += len;
        }
        return res;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));

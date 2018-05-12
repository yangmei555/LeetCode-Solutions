public class Codec {

    Map<String, String> map = new HashMap<>();
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        int code = longUrl.hashCode();
        String str = "http://tinyurl.com/" + code;
        map.put(str, longUrl);
        return str;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));


public class Codec {

    Random random = new Random();
    Map<String, String> map = new HashMap<>();
    String alnumstr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    char[] ch = alnumstr.toCharArray();
    int len = 6;
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String coded = generate();
        while (map.containsKey(coded))
            coded = generate();
        String res = "http://tinyurl.com/" + coded;
        map.put(res, longUrl);
        return res;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl);
    }
    
    public String generate() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) 
            sb.append(ch[random.nextInt(62)]);
        return sb.toString();
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));

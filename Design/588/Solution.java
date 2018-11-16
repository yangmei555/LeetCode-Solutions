class FileSystem {
    
    Directory root;
    public FileSystem() {
        root = new Directory("/");
    }
    
    public List<String> ls(String path) {
        List<String> res = new LinkedList<>();
        String[] strs = path.substring(1).split("/");
        Directory dir = root;
        for (int i = 0; i < strs.length-1; i++)
            dir = dir.subDir.get(strs[i]);
        String str = strs.length == 0 ? null : strs[strs.length-1];
        if (dir.files.containsKey(str)) {
            res.add(str);
        } else {
            if (str != null)
                dir = dir.subDir.get(str);
            for (String key : dir.subDir.keySet())
                res.add(key);
            for (String key : dir.files.keySet())
                res.add(key);
            Collections.sort(res);
        }
        return res;
    }
    
    public void mkdir(String path) {
        String[] strs = path.substring(1).split("/");
        if (strs.length != 0) {
            Directory dir = root;
            for (String str : strs) {
                if (!dir.subDir.containsKey(str)) 
                    dir.subDir.put(str, new Directory(str));
                dir = dir.subDir.get(str);
            }
        }
    }
    
    public void addContentToFile(String filePath, String content) {
        String[] strs = filePath.substring(1).split("/");
        Directory dir = root;
        for (int i = 0; i < strs.length-1; i++) {
            if (!dir.subDir.containsKey(strs[i])) 
                dir.subDir.put(strs[i], new Directory(strs[i]));
            dir = dir.subDir.get(strs[i]);
        }
        String fileName = strs[strs.length-1];
        dir.files.putIfAbsent(fileName, new StringBuilder());
        dir.files.get(fileName).append(content);
    }
    
    public String readContentFromFile(String filePath) {
        String[] strs = filePath.substring(1).split("/");
        Directory dir = root;
        for (int i = 0; i < strs.length-1; i++) {
            if (!dir.subDir.containsKey(strs[i])) 
                dir.subDir.put(strs[i], new Directory(strs[i]));
            dir = dir.subDir.get(strs[i]);
        }
        String fileName = strs[strs.length-1];
        return dir.files.get(fileName).toString();
    }
    
    public class Directory {
        String name;
        Map<String, Directory> subDir;
        Map<String, StringBuilder> files;
        public Directory(String name) {
            this.name = name;
            subDir = new HashMap<>();
            files = new HashMap<>();
        }
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */

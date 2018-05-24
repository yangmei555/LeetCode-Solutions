class ValidWordAbbr {
    
    Map<String, Integer> map = new HashMap<>();
    
    public ValidWordAbbr(String[] dictionary) {
        for (String str : dictionary) {
            if (str.length() >= 2) {
                String abbr = str.charAt(0) + "" + (str.length()-2) + "" + str.charAt(str.length()-1);
                map.put(abbr, map.getOrDefault(abbr, 0) + 1);
                map.put(str, map.getOrDefault(str, 0) + 1);
            }
        }
    }
    
    public boolean isUnique(String word) {
        if (word.length() < 2)
            return true;
        String abbr = word.charAt(0) + "" + (word.length()-2) + "" + word.charAt(word.length()-1);
        return !map.containsKey(abbr) || (map.getOrDefault(word, 0) == 1 && map.get(abbr) == 1);
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */


class ValidWordAbbr {
    
    Map<String, String> map = new HashMap<>();
    
    public ValidWordAbbr(String[] dictionary) {
        for (String str : dictionary) {
            if (str.length() >= 2) {
                String abbr = str.charAt(0) + "" + (str.length()-2) + "" + str.charAt(str.length()-1);
                if (map.containsKey(abbr) && !map.get(abbr).equals(str))
                    map.put(abbr, "");
                else
                    map.put(abbr, str);
            }
        }
    }
    
    public boolean isUnique(String word) {
        if (word.length() < 2)
            return true;
        String abbr = word.charAt(0) + "" + (word.length()-2) + "" + word.charAt(word.length()-1);
        return !map.containsKey(abbr) || map.get(abbr).equals(word);
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */

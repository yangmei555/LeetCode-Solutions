class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String[] morse = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....",
                                        "..",".---","-.-",".-..","--","-.","---",".--.",
                                        "--.-",".-.","...","-","..-","...-",".--","-..-",
                                        "-.--","--.."};
        Set<String> set = new HashSet<>();
        for (String w : words) {
            StringBuilder code = new StringBuilder();
            for (char c : w.toCharArray()) 
                code.append(morse[c - 'a']);
            set.add(code.toString());
        }
        return set.size();
    }
}

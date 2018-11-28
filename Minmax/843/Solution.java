/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 */
// this solution is not optimal, notice that, after each guess, the scope of eligible words 
// will narrow down, so maxLen should be recauculated after each guess  
class Solution {
    public void findSecretWord(String[] wordlist, Master master) {
        int[] maxLen = new int[wordlist.length];
        boolean[][][] record = new boolean[wordlist.length][6][wordlist.length];
        char[][] ch = new char[wordlist.length][];
        for (int i = 0; i < ch.length; i++)
            ch[i] = wordlist[i].toCharArray();
        for (int i = 0; i < wordlist.length; i++) {
            int[] count = new int[6];
            int max = 0;
            for (int j = 0; j < wordlist.length; j++) {
                if (i == j)
                    continue;
                int match = 0;
                for (int k = 0; k < ch[i].length; k++) {
                    if (ch[i][k] == ch[j][k])
                        match++;
                }
                record[i][match][j] = true;
                count[match]++;
                max = Math.max(max, count[match]);
            }
            maxLen[i] = max;
        }
        boolean[] cands = new boolean[wordlist.length];
        Arrays.fill(cands, true);
        int guess = -1;
        for (int j = 0; j < maxLen.length; j++) {
            if (guess == -1 || maxLen[guess] > maxLen[j])
                guess = j;
        }
        for (int i = 0; i < 10; i++) {
            int match = master.guess(wordlist[guess]);
            if (match == 6)
                break;
            int possible = 0, next = -1;
            for (int j = 0; j < record[guess][match].length; j++) {
                if (cands[j] &= record[guess][match][j]) {
                    possible++;
                    if (next == -1 || maxLen[next] > maxLen[j])
                        next = j;
                }
            }
            if (possible < 10 - i) {
                for (int j = 0; j < cands.length; j++) {
                    if (cands[j] && master.guess(wordlist[j]) == 6) 
                        break;
                }
                break;
            }
            guess = next;
        }
    }
}


// this is the truly optimal solution 
// notice that, in the getCandidate function, we consider the whole set of words, even if 
// some of them are invalid to be the desired word. because some of the invalid words may 
// partition the candidate words more evenly, so can eliminate more words in the next round 

// good test case : 

// "aaponm"
// ["aazyxw","aayxwv","aaxwvu","aawvut","aavuts","aautsr","aatsrq","aasrqp","aarqpo","aaqpon",
//  "aaponm","aaonml","aanmlk","aamlkj","aalkji","aakjih","aajihg","aaihgf","aahgfe","aagfed",
//  "aafedc","ccwwww","ccssss","ccoooo","cckkkk","ccgggg","cccccc","ccyyyy","ccuuuu","ccqqqq",
//  "ccmmmm","ddwwww","ddssss","ddoooo","ddkkkk","ddgggg","ddcccc","ddyyyy","dduuuu","ddqqqq",
//  "ddmmmm","eewwww","eessss","eeoooo","eekkkk","eegggg","eecccc","eeyyyy","eeuuuu","eeqqqq",
//  "eemmmm","ffwwww","ffssss","ffoooo","ffkkkk","ffgggg","ffcccc","ffyyyy","ffuuuu","ffqqqq",
//  "ffmmmm","ggwwww","ggssss","ggoooo","ggkkkk","gggggg","ggcccc","ggyyyy","gguuuu","ggqqqq",
//  "ggmmmm","hhwwww","hhssss","hhoooo","hhkkkk","hhgggg","hhcccc","hhyyyy","hhuuuu","hhqqqq",
//  "hhmmmm","iiwwww","iissss","iioooo","iikkkk","iigggg","iicccc","iiyyyy","iiuuuu","iiqqqq",
//  "iimmmm","jjwwww","jjssss","jjoooo","jjkkkk","jjgggg","jjcccc","jjyyyy","jjuuuu","jjqqqq"]
// 10

class Solution {
    public void findSecretWord(String[] wordlist, Master master) {
        char[][] ch = new char[wordlist.length][];
        for (int i = 0; i < wordlist.length; i++)
            ch[i] = wordlist[i].toCharArray();
        int[][] record = new int[wordlist.length][wordlist.length];
        for (int i = 0; i < wordlist.length; i++) {
            for (int j = i; j < wordlist.length; j++) {
                int match = 0;
                for (int k = 0; k < ch[i].length; k++) {
                    if (ch[i][k] == ch[j][k])
                        match++;
                }
                record[i][j] = record[j][i] = match;
            }
        }
        Integer[] indices = new Integer[wordlist.length];
        boolean[] tried = new boolean[wordlist.length];
        for (int i = 0; i < indices.length; i++)
            indices[i] = i;
        for (int i = 0; i < 10; i++) {
            int guess = getCandidate(wordlist, indices, record);
            tried[guess] = true;
            int match = master.guess(wordlist[guess]);
            if (match == 6)
                break;
            List<Integer> next = new LinkedList<>();
            for (int j = 0; j < indices.length; j++) {
                if (record[guess][indices[j]] == match && !tried[indices[j]]) 
                    next.add(indices[j]);
            }
            indices = next.toArray(new Integer[0]);
        }
    }
    
    public int getCandidate(String[] wordlist, Integer[] indices, int[][] record) {
        int[] maxLen = new int[wordlist.length];
        int cand = -1;
        for (int i = 0; i < wordlist.length; i++) {
            int[] count = new int[6];
            int max = 0;
            for (int j = 0; j < indices.length; j++) {
                if (i == indices[j])
                    continue;
                int match = record[i][indices[j]];
                count[match]++;
                max = Math.max(max, count[match]);
            }
            maxLen[i] = max;
            if (cand == -1 || maxLen[cand] > maxLen[i])
                cand = i;
        }
        return cand;
    }
}


// a very bad approach, sometimes can pass the judge sometimes can not 
class Solution {
    Random random = new Random();
    public void findSecretWord(String[] wordlist, Master master) {
        List<String> cand = new LinkedList<>();
        for (String word : wordlist)
            cand.add(word);
        while (true) {
            int index = random.nextInt(cand.size());
            int ret = master.guess(cand.get(index));
            if (ret == 6)
                break;
            cand = helper(cand, cand.get(index), ret);
        }
    }
    
    public List<String> helper(List<String> cand, String str, int count) {
        List<String> res = new LinkedList<>();
        for (String word : cand) {
            int match = 0;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == str.charAt(i))
                    match++;
            }
            if (match == count)
                res.add(word);
        }
        return res;
    }
}

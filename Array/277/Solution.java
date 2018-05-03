/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        boolean[] index = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!index[i]) {
                for (int j = i+1; j < n; j++) {
                    if (knows(i, j))
                        index[i] = true;
                    else 
                        index[j] = true;
                    if (!index[j]) {
                        if (knows(j, i))
                            index[j] = true;
                        else
                            index[i] = true;
                    }
                    if (index[i])
                        break;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (!index[i]) {
                int j = 0;
                for ( ; j < n; j++) {
                    if (i != j) {
                        if (!knows(j, i) || knows(i, j))
                            break;
                    }
                }
                if (j == n)
                    return i;
            }
        }
        return -1;
    }
}


/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int[] index = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (knows(i, j)) {
                    index[i] = -1;
                    if (index[j] != -1)
                        index[j]++;
                }
                if (knows(j, i)) {
                    index[j] = -1;
                    if (index[i] != -1)
                        index[i]++;
                }
            }
            if (index[i] == n-1)
                return i;
        }
        return -1;
    }
}


/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int cand = 0;
        for (int i = 0; i < n; i++) {
            if (i != cand) {
                if (knows(cand, i))
                    cand = i;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i != cand) {
                if (!knows(i, cand) || knows(cand, i))
                    return -1;
            }
        }
        return cand;
    }
}


/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int cand = 0;
        for (int i = 0; i < n; i++) {
            if (i != cand) {
                if (knows(cand, i))
                    cand = i;
            }
        }
        for (int i = 0; i < cand; i++) {
            if (!knows(i, cand) || knows(cand, i))
                return -1;
        }
        for (int i = cand + 1; i < n; i++) {
            if (!knows(i, cand))
                return -1;
        }
        return cand;
    }
}

/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
class Solution extends SolBase {
    public int rand10() {
        int a = 2, b = 2;
        while (7 * a + b > 9) {
            a = rand7() - 1;
            b = rand7() - 1;
        }
        return 7 * a + b + 1;
    }
}


/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
class Solution extends SolBase {
    public int rand10() {
        int a = 6, b = 6;
        while (7 * a + b >= 40) {
            a = rand7() - 1;
            b = rand7() - 1;
        }
        return (7 * a + b) % 10 + 1;
    }
}

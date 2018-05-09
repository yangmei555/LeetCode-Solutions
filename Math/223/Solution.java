class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int s1 = (C-A) * (D-B);
        int s2 = (G-E) * (H-F);
        if (E >= C || A >= G || F >= D || H <= B)
            return s1 + s2;
        int a = Math.min(Math.abs(A-G), Math.abs(C-E));
        a = Math.min(a, Math.abs(A-C));
        a = Math.min(a, Math.abs(E-G));
        int b = Math.min(Math.abs(D-F), Math.abs(B-H));
        b = Math.min(b, Math.abs(B-D));
        b = Math.min(b, Math.abs(F-H));
        return s1 + s2 - a * b;
    }
}


class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int s1 = (C-A) * (D-B);
        int s2 = (G-E) * (H-F);
        int left = A > E ? A : E;
        int right = C < G ? C : G;
        int down = B > F ? B : F;
        int up = D < H ? D : H;
        int overlap = 0;
        if (left < right && down < up)
            overlap = (right - left) * (up - down);
        return s1 + s2 - overlap;
    }
}

class Solution {
    public int lastRemaining(int n) {
        return helper(1, n, 1);
    }
    
    public int helper(int start, int end, int gap) {
        if (start == end)
            return start;
        int len = (end - start) / gap + 1;
        return helper(end - len % 2 * gap, start + gap, - gap * 2);
    }
}


class Solution {
    public int lastRemaining(int n) {
        int len = n, head = 1, step = 1;
        boolean left = true;
        while (len != 1) {
            if (left || len % 2 == 1)
                head += step;
            step *= 2;
            len /= 2;
            left = !left;
        }
        return head;
    }
}


// change "head" to "tail", same thought 
class Solution {
    public int lastRemaining(int n) {
        int len = n, tail = n, step = 1;
        boolean left = true;
        while (len != 1) {
            if (!left || len % 2 == 1)
                tail -= step;
            step *= 2;
            len /= 2;
            left = !left;
        }
        return tail;
    }
}


class Solution {
    public int lastRemaining(int n) {
        if (n == 1)
            return 1;
        return (n/2 + 1 - lastRemaining(n/2)) * 2;
    }
}


class Solution {
    public int lastRemaining(int n) {
        if (n == 1)
            return 1;
        if (n == 2 || n == 3)
            return 2;
        if (n % 2 == 1)
            n--;
        if (n % 4 == 0)
            return lastRemaining(n/4) * 4 - 2;
        else
            return lastRemaining(n/4) * 4;
    }
}

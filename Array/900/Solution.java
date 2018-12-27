// this solution needs to use long , otherwise it will cause integer overflow 
class RLEIterator {

    int[] A;
    long total, count;
    int index;
    public RLEIterator(int[] A) {
        this.A = A;
        count = index = 0;
        total = A[0];
    }
    
    public int next(int n) {
        count += n;
        while (index + 2 < A.length && count > total) {
            index += 2;
            total += A[index];
        }
        if (count <= total)
            return A[index+1];
        else
            return -1;
    }
}


// another style 
class RLEIterator {

    int[] A;
    int index, remain;
    public RLEIterator(int[] A) {
        this.A = A;
        index = 0;
        remain = A[0];
    }
    
    public int next(int n) {
        if (index < A.length) {
            while (n > remain) {
                index += 2;
                if (index >= A.length)
                    return -1;
                remain += A[index];
            }
            remain -= n;
            return A[index+1];
        } else {
            return -1;
        }
    }
}


// another style 
class RLEIterator {

    int[] A;
    int index, used;
    public RLEIterator(int[] A) {
        this.A = A;
        index = used = 0;
    }
    
    public int next(int n) {
        while (index < A.length) {
            if (used + n <= A[index]) {
                used += n;
                return A[index+1];
            } else {
                n -= A[index] - used;
                used = 0;
                index += 2;
            }
        }
        return -1;
    }
}

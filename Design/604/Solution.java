class StringIterator {
    
    int index, ptr, count;
    char[] ch;
    public StringIterator(String compressedString) {
        ch = compressedString.toCharArray();
        index = 0;
        ptr = 0;
        count = 0;
    }
    
    public char next() {
        if (count != 0 || index != ch.length) {
            if (count == 0) {
                ptr = index++;
                while (index < ch.length && ch[index] >= '0' && ch[index] <= '9')
                    count = count * 10 + ch[index++] - '0';
            }
            count--;
            return ch[ptr];
        } else {
            return ' ';
        }
    }
    
    public boolean hasNext() {
        return index != ch.length || count != 0;
    }
}

/**
 * Your StringIterator object will be instantiated and called as such:
 * StringIterator obj = new StringIterator(compressedString);
 * char param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */

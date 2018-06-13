class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int index = Arrays.binarySearch(letters, target);
        if (index < 0) {
            index = -index - 1;
            if (index == letters.length)
                index = 0;
            return letters[index];
        } else {
            while (index < letters.length && letters[index] == target)
                index++;
            if (index == letters.length)
                index = 0;
            return letters[index];
        }
    }
}


class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int index = 0;
        while (index < letters.length) {
            if (letters[index] > target)
                break;
            index++;
        }
        return index == letters.length ? letters[0] : letters[index];
    }
}


class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0, right = letters.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (letters[mid] <= target)
                left = mid + 1;
            else
                right = mid;
        }
        return letters[left % letters.length];
    }
}

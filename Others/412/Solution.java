class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> list = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0)
                list.add("FizzBuzz");
            else if (i % 3 == 0)
                list.add("Fizz");
            else if (i % 5 == 0)
                list.add("Buzz");
            else
                list.add(i + "");
        }
        return list;
    }
}


class Solution {
    public List<String> fizzBuzz(int n) {
        if (n == 1) {
            List<String> list = new LinkedList<>();
            list.add("1");
            return list;
        }
        List<String> list = fizzBuzz(n-1);
        if (n % 3 == 0 && n % 5 == 0)
            list.add("FizzBuzz");
        else if (n % 3 == 0)
            list.add("Fizz");
        else if (n % 5 == 0)
            list.add("Buzz");
        else
            list.add(n + "");
        return list;
    }
}

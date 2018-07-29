class Solution {
    public int leastInterval(char[] tasks, int n) {
        // map[ch-'A'] is the earliest time that the next task "ch" can be executed
        int[] map = new int[26];  
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int c : tasks) {
            queue.offer(map[c-'A']);
            map[c-'A'] += n + 1;
        }
        int res = 0;
        while (!queue.isEmpty()) {
            int t = queue.poll();
            // if current time is later than the earliest executed time, use the current time, 
            // otherwise, the current time should be set as that earliest executed time
            res = res > t ? res : t;
            res++;
        }
        return res;
    }
}


public class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c : tasks)
            map[c-'A']++;
        Arrays.sort(map);
        int i = 25;
        while (i >= 0 && map[i] == map[25])
            i--;
        int res1 = tasks.length;
        int res2 = (map[25]-1) * (n+1) + 25 - i;
        return res1 > res2 ? res1 : res2;
    }
}


public class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        int max = 0, count = 0;
        for (char c : tasks) 
            map[c-'A']++;
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        });
        for (int m : map) {
            if (m != 0)
                queue.offer(m);
        }
        int res = 0;
        while (!queue.isEmpty()) {
            int slot = n + 1;
            List<Integer> temp = new LinkedList<>();
            while (!queue.isEmpty() && slot > 0) {
                temp.add(queue.poll() - 1);
                slot--;
                res++;
            }
            for (int t : temp) {
                if (t != 0)
                    queue.offer(t);
            }
            if (queue.isEmpty())
                break;
            else if (slot != 0)
                res += slot;
        }
        return res;
    }
}


class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        int max = 0, count = 0;
        for (char c : tasks) {
            map[c-'A']++;
            if (map[c-'A'] > max) {
                max = map[c-'A'];
                count = 1;
            } else if (map[c-'A'] == max) {
                count++;
            }
        }
        int tasksNeedSchedule = tasks.length - max * count;
        int availableSlots = (max - 1) * (n + 1 - count);
        int idleSlots = availableSlots - tasksNeedSchedule > 0 ? 
                        availableSlots - tasksNeedSchedule : 0;
        return tasks.length + idleSlots;
        
    }
}


// similar idea to the second solution
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] maps = new int[26];
        for (char c : tasks)
            maps[c-'A']++;
        Arrays.sort(maps);
        int res = 0;
        while (maps[25] > 0) {
            int i = 0;
            while (i <= 25 && i < n + 1) {
                if (maps[25-i] == 0)
                    break;
                else
                    maps[25-i]--;
                res++;
                i++;
            }
            Arrays.sort(maps);
            if (maps[25] != 0)
                res += n + 1 - i;
        }
        return res;
    }
}

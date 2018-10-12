class Solution {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[1] - i2[1];
            }
        });
        int end = 0, res = 0, last = 0;
        int[][] record = new int[courses.length][2];
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < courses.length; i++) {
            if (end + courses[i][0] <= courses[i][1]) {
                queue.offer(-courses[i][0]);
                end += courses[i][0];
            } else if (!queue.isEmpty() && queue.peek() < -courses[i][0]) {
                end += courses[i][0] + queue.poll();
                queue.offer(-courses[i][0]);
            }
        }
        return queue.size();
    }
}

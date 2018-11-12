class MedianFinder {

    /** initialize your data structure here. */
    TreeSet<int[]> set;
    int[] pair1, pair2;
    int index;
    public MedianFinder() {
        set = new TreeSet<>(new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                if (i1[0] == i2[0])
                    return i1[1] - i2[1];
                else
                    return i1[0] - i2[0];
            }
        });
        pair1 = null;
        pair2 = null;
        index = 0;
    }
    
    public void addNum(int num) {
        set.add(new int[]{num, index});
        if (pair1 == null) {
            pair1 = new int[]{num, index};
        } else if (pair2 == null) {
            if (num < pair1[0]) {
                pair2 = pair1;
                pair1 = set.lower(pair1);
            } else {
                pair2 = set.higher(pair1);
            }
        } else {
            if (num < pair1[0])
                ;
            else if (num < pair2[0]) 
                pair1 = new int[]{num, index};
            else if (num >= pair2[0]) 
                pair1 = pair2;
            
            pair2 = null;
        }
        index++;
    }
    
    public double findMedian() {
        if (pair2 == null)
            return pair1[0];
        else
            return (pair1[0] + pair2[0]) / 2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */


// a fantastic idea to use two heaps, maintaining the smaller half and larger half of all elements 
class MedianFinder {

    /** initialize your data structure here. */
    Queue<Integer> firstHalf, secondHalf;
    public MedianFinder() {
        // firstHalf = new PriorityQueue<>(new Comparator<Integer>() {
        //     public int compare(Integer i1, Integer i2) {
        //         return i2 - i1;
        //     }
        // });
        
        firstHalf = new PriorityQueue<>(Collections.reverseOrder());
        secondHalf = new PriorityQueue<>();
        
    }
    
    public void addNum(int num) {
        firstHalf.offer(num);
        secondHalf.offer(firstHalf.poll());
        if (secondHalf.size() > firstHalf.size()) 
            firstHalf.offer(secondHalf.poll());
    }
    
    public double findMedian() {
        if (firstHalf.size() == secondHalf.size())
            return (firstHalf.peek() + secondHalf.peek()) / 2.0;
        else
            return firstHalf.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

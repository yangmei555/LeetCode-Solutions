class Twitter {

    Map<Integer, List<Integer>> followers;
    Map<Integer, List<Integer>> followees;
    Map<Integer, List<int[]>> tweets;
    Map<Integer, PriorityQueue<int[]>> timeline;
    int stamp;
    
    /** Initialize your data structure here. */
    public Twitter() {
        stamp = 0;
        followers = new HashMap<>();
        followees = new HashMap<>();
        tweets = new HashMap<>();
        timeline = new HashMap<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        int[] tweet = new int[]{userId, stamp++, tweetId};
        tweets.putIfAbsent(userId, new LinkedList<>());
        tweets.get(userId).add(tweet);
        timeline.putIfAbsent(userId, new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i2[1] - i1[1];
            }
        }));
        timeline.get(userId).offer(tweet);
        if (followees.containsKey(userId)) {
            for (int i : followees.get(userId)) {
                timeline.putIfAbsent(i, new PriorityQueue<>(new Comparator<int[]>() {
                    public int compare(int[] i1, int[] i2) {
                        return i2[1] - i1[1];
                    }
                }));
                timeline.get(i).offer(tweet);
            }
        }
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed 
        must be posted by users who the user followed or by the user herself. Tweets must be ordered 
        from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new LinkedList<>();
        if (timeline.containsKey(userId)) {
            PriorityQueue<int[]> queue = new PriorityQueue(timeline.get(userId));
            int count = 0;
            while (!queue.isEmpty()) {
                res.add(queue.poll()[2]);
                count++;
                if (count == 10)
                    break;
            }
        }
        return res;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId)
            return;
        followers.putIfAbsent(followerId, new LinkedList<>());
        if (followers.get(followerId).contains(followeeId))
            return;
        followers.get(followerId).add(followeeId);
        followees.putIfAbsent(followeeId, new LinkedList<>());
        followees.get(followeeId).add(followerId);
        if (tweets.containsKey(followeeId)) {
            timeline.putIfAbsent(followerId, new PriorityQueue<>(new Comparator<int[]>() {
                public int compare(int[] i1, int[] i2) {
                    return i2[1] - i1[1];
                }
            }));
            for (int[] i : tweets.get(followeeId)) {
                timeline.get(followerId).offer(i);
            }
        }
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followers.containsKey(followerId) && followers.get(followerId).contains(followeeId)) {
            followers.get(followerId).remove((Integer)followeeId);
            if (followers.get(followerId).isEmpty())
                followers.remove(followerId);
            followees.get(followeeId).remove((Integer)followerId);
            if (followees.get(followeeId).isEmpty())
                followees.remove(followeeId);
            if (tweets.containsKey(followeeId)) {
                PriorityQueue<int[]> newq = new PriorityQueue<>(new Comparator<int[]>() {
                    public int compare(int[] i1, int[] i2) {
                        return i2[1] - i1[1];
                    }
                });
                PriorityQueue<int[]> oldq = timeline.get(followerId);
                while (!oldq.isEmpty()) {
                    int[] temp = oldq.poll();
                    if (temp[0] != followeeId)
                        newq.offer(temp);
                }
                timeline.put(followerId, newq);
            }
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */


class Twitter {

    Map<Integer, Set<Integer>> followers;
    Map<Integer, Tweet> tweets;
    int stamp;
    
    class Tweet {
        int stamp, tweetId;
        Tweet next;
        public Tweet(int s, int t) {
            stamp = s;
            tweetId = t;
        }
    }
    
    /** Initialize your data structure here. */
    public Twitter() {
        stamp = 0;
        followers = new HashMap<>();
        tweets = new HashMap<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        Tweet newtweet = new Tweet(stamp++, tweetId);
        Tweet head = tweets.getOrDefault(userId, null);
        newtweet.next = head;
        tweets.put(userId, newtweet);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed 
        must be posted by users who the user followed or by the user herself. Tweets must be ordered 
        from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new LinkedList<>();
        PriorityQueue<Tweet> queue = new PriorityQueue(new Comparator<Tweet>() {
            public int compare(Tweet t1, Tweet t2) {
                return t2.stamp - t1.stamp;
            }
        });
        if (tweets.containsKey(userId))
            queue.offer(tweets.get(userId));
        if (followers.containsKey(userId)) {
            for (int i : followers.get(userId)) {
                if (tweets.containsKey(i))
                    queue.offer(tweets.get(i));
            }
        }
        int count = 0;
        while (!queue.isEmpty() && count < 10) {
            Tweet t = queue.poll();
            res.add(t.tweetId);
            if (t.next != null)
                queue.offer(t.next);
            count++;
        }
        return res;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId)
            return;
        followers.putIfAbsent(followerId, new HashSet<>());
        followers.get(followerId).add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followers.containsKey(followerId)) 
            followers.get(followerId).remove((Integer)followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */

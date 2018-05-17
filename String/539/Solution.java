class Solution {
    public int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints, new Comparator<String>() {   
            // no need to write a comparator actually
            public int compare(String s1, String s2) {
                String[] str1 = s1.split(":");
                String[] str2 = s2.split(":");
                return Integer.valueOf(str1[0])*60+Integer.valueOf(str1[1]) - 
                        Integer.valueOf(str2[0])*60-Integer.valueOf(str2[1]);
            }
        });
        int res = Integer.MAX_VALUE;
        System.out.println(timePoints);
        for (int i = 0; i < timePoints.size(); i++) {
            String s1 = timePoints.get(i);
            String s2 = i == timePoints.size()-1 ? timePoints.get(0) : timePoints.get(i+1);
            String[] str1 = s1.split(":");
            String[] str2 = s2.split(":");
            int temp = 0;
            if (i != timePoints.size()-1)
                temp = Integer.valueOf(str2[0])*60+Integer.valueOf(str2[1]) - 
                        Integer.valueOf(str1[0])*60-Integer.valueOf(str1[1]);
            else
                temp = 1440 + Integer.valueOf(str2[0])*60+Integer.valueOf(str2[1]) - 
                        Integer.valueOf(str1[0])*60-Integer.valueOf(str1[1]);
            res = res < temp ? res : temp;
        }
        return res;
    }
}


class Solution {
    public int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints, new Comparator<String>() {
            // no need to write a comparator actually
            public int compare(String s1, String s2) {
                char[] ch1 = s1.toCharArray();
                char[] ch2 = s2.toCharArray();
                return ((ch1[0]-'0')*10+(ch1[1]-'0'))*60+((ch1[3]-'0')*10+(ch1[4]-'0')) - 
                        ((ch2[0]-'0')*10+(ch2[1]-'0'))*60-((ch2[3]-'0')*10+(ch2[4]-'0'));
            }
        });
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < timePoints.size(); i++) {
            String s1 = timePoints.get(i);
            String s2 = i == timePoints.size()-1 ? timePoints.get(0) : timePoints.get(i+1);
            char[] ch1 = s1.toCharArray();
            char[] ch2 = s2.toCharArray();
            int temp = 0;
            if (i != timePoints.size()-1)
                temp = ((ch2[0]-'0')*10+(ch2[1]-'0'))*60+((ch2[3]-'0')*10+(ch2[4]-'0')) - 
                        ((ch1[0]-'0')*10+(ch1[1]-'0'))*60-((ch1[3]-'0')*10+(ch1[4]-'0'));
            else
                temp = 1440 + ((ch2[0]-'0')*10+(ch2[1]-'0'))*60+((ch2[3]-'0')*10+(ch2[4]-'0')) - 
                                ((ch1[0]-'0')*10+(ch1[1]-'0'))*60-((ch1[3]-'0')*10+(ch1[4]-'0'));
            res = res < temp ? res : temp;
        }
        return res;
    }
}


class Solution {
    public int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints, new Comparator<String>() {
            // no need to write a comparator actually
            public int compare(String s1, String s2) {
                int n1 = ((s1.charAt(0)-'0')*10+(s1.charAt(1)-'0'))*60+
                            ((s1.charAt(3)-'0')*10+(s1.charAt(4)-'0'));
                int n2 = ((s2.charAt(0)-'0')*10+(s2.charAt(1)-'0'))*60+
                            ((s2.charAt(3)-'0')*10+(s2.charAt(4)-'0'));
                return n1 - n2;
            }
        });
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < timePoints.size(); i++) {
            String s1 = timePoints.get(i);
            String s2 = i == timePoints.size()-1 ? timePoints.get(0) : timePoints.get(i+1);
            int n1 = ((s1.charAt(0)-'0')*10+(s1.charAt(1)-'0'))*60+
                            ((s1.charAt(3)-'0')*10+(s1.charAt(4)-'0'));
            int n2 = ((s2.charAt(0)-'0')*10+(s2.charAt(1)-'0'))*60+
                        ((s2.charAt(3)-'0')*10+(s2.charAt(4)-'0'));
            int temp = i == timePoints.size()-1 ? 1440 + n2 - n1 : n2 - n1;
            res = res < temp ? res : temp;
        }
        return res;
    }
}


class Solution {
    public int findMinDifference(List<String> timePoints) {
        List<Integer> list = new ArrayList<>();
        for (String s : timePoints) {
            char[] ch = s.toCharArray();
            int n = ((ch[0]-'0')*10+(ch[1]-'0'))*60+((ch[3]-'0')*10+(ch[4]-'0'));
            list.add(n);
        }
        Collections.sort(list);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            int n1 = list.get(i);
            int n2 = i == timePoints.size()-1 ? list.get(0) : list.get(i+1);
            int temp = i == timePoints.size()-1 ? 1440 + n2 - n1 : n2 - n1;
            res = res < temp ? res : temp;
        }
        return res;
    }
}


class Solution {
    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() > 24*60)
            return 0;
        boolean[] index = new boolean[24*60];
        for (String s : timePoints) {
            char[] ch = s.toCharArray();
            int n = ((ch[0]-'0')*10+(ch[1]-'0'))*60+((ch[3]-'0')*10+(ch[4]-'0'));
            if (index[n])
                return 0;
            else
                index[n] = true;
        }
        int res = Integer.MAX_VALUE;
        int pre = -1, first = 0;
        for (int i = 0; i < index.length; i++) {
            if (index[i]) {
                if (pre == -1) {
                    first = pre = i;
                } else {
                    res = res < i - pre ? res : i - pre;
                    pre = i;
                }
            }
        }
        res = res < 1440 + first - pre ? res : 1440 + first - pre;
        return res;
    }
}

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list = new LinkedList<>();
        for (int a : arr)
            list.add(a);
        Collections.sort(list, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                int dist1 = i1 < x ? x - i1 : i1 - x;
                int dist2 = i2 < x ? x - i2 : i2 - x;
                if (dist1 != dist2)
                    return dist1 - dist2;
                else
                    return i1 - i2;
            }
        });
        List<Integer> res = list.subList(0, k);
        Collections.sort(res);
        return res;
    }
}


class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int[] ano = new int[arr.length];
        for (int i = 0; i < arr.length; i++) 
            ano[i] = arr[i] > x ? arr[i] - x : x - arr[i];
        int dist = select(ano.clone(), 0, arr.length-1, k, x);
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < ano.length; i++) {
            if (ano[i] < dist) 
                res.add(arr[i]);
        }
        for (int i = 0; i < ano.length; i++) {
            if (ano[i] == dist) {
                int index = Collections.binarySearch(res, arr[i]);
                index = index >= 0 ? index : -index-1;
                res.add(index, arr[i]);
                if (res.size() == k)
                    break;
            }
        }
        return res;
    }
    
    public int select(int[] arr, int left, int right, int k, int x) {
        if (left == right)
            return arr[left];
        int mid = (left + right) / 2, temp = 0;
        temp = arr[mid];
        arr[mid] = arr[right];
        arr[right] = temp;
        int j = left-1;
        for (int i = left; i <= right; i++) {
            if (arr[i] <= arr[right]) {
                j++;
                temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        int order = j - left + 1;
        if (order == k)
            return arr[j];
        else if (order < k)
            return select(arr, j+1, right, k-order, x);
        else
            return select(arr, left, j-1, k, x);
    }
}


class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int[] map = new int[20001];
        for (int a : arr) {
            int dist = a > x ? a - x : x - a;
            map[dist]++;
        }
        int count = 0, i = 0;
        while (count <= k && i < map.length) {
            count += map[i];
            i++;
        }
        i--;
        List<Integer> res = new LinkedList<>();
        for (int a : arr) {
            int dist = a > x ? a - x : x - a;
            if (dist < i)
                res.add(a);
        }
        if (res.size() == k)
            return res;
        for (int a : arr) {
            int dist = a > x ? a - x : x - a;
            if (dist == i) {
                int index = Collections.binarySearch(res, a);
                index = index >= 0 ? index : -index-1;
                res.add(index, a);
                if (res.size() == k)
                    return res;
            }
        }
        return res;
    }
}


class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] == x) {
                left = mid;
                break;
            } else if (arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        right = left;
        left--;
        List<Integer> res = new LinkedList<>();
        int count = 0;
        while (count++ < k) {
            int dist1 = left >= 0 ? x - arr[left] : Integer.MAX_VALUE;
            int dist2 = right < arr.length ? arr[right] - x : Integer.MAX_VALUE;
            if (dist1 <= dist2) {
                left--;
            } else {
                right++;
            }
        }
        left++;
        for (int i = 0; i < k; i++)
            res.add(arr[left + i]);
        return res;
    }
}


class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length - k;
        while (left < right) {
            int mid = (left + right) / 2;
            if (x - arr[mid] > arr[mid+k] - x)
                left = mid + 1;
            else
                right = mid;
        }
        List<Integer> res = new ArrayList<>(k);
        for (int i = left; i < left + k; i++)
            res.add(arr[i]);
        return res;
    }
}

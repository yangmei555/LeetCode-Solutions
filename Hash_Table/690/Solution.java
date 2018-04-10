/*
// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
*/
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        HashMap<Integer, Employee> map = new HashMap<>();
        for (Employee e : employees) {
            map.put(e.id, e);
        }
        return helper(map, id);
    }

    public int helper(HashMap<Integer, Employee> map, int id) {
        Employee e = map.get(id);
        int ret = 0;
        for (int i : e.subordinates) {
            ret += helper(map, i);
        }
        return ret + e.importance;
    }
}

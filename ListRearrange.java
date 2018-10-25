
/*
 小贴士： 
 1. arraylist 如果remove（idx） 就会返回obj  如果remove（obj）就会返回true or false denoting where it's exist
 2. In your example, it is because you can't have a List of a primitive type. In other words, List<int> is not possible. You can, however, have a List<Integer>.
 Code Example:
 Integer[] spam = new Integer[] { 1, 2, 3 };
 Arrays.asList(spam);
*/
import java.util.*;

public class ListRearrange {
    public static void main(String[] args) {
        Integer[] a = new Integer[] { 1, 2, 3 };
        Integer[] b = new Integer[] { 2, 3, 1 };
        Integer[] c = new Integer[] { 7, 8, 9 };
        List<Integer> aa = Arrays.asList(a);
        List<Integer> bb = Arrays.asList(b);
        List<Integer> cc = Arrays.asList(c);
        System.out.println(listRearrange(aa, bb, cc));
    }

    public static List<Integer> listRearrange(List<Integer> a, List<Integer> b, List<Integer> c) {
        // assume input: a and b have same size
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < a.size(); i++) {
            int key = a.get(i);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(i);
        }
        Integer[] res = new Integer[b.size()];
        for (int i = 0; i < b.size(); i++) {
            int key = b.get(i);
            List<Integer> val = map.get(key);
            int old = val.remove(val.size() - 1);
            res[i] = c.get(old);
        }
        return Arrays.asList(res);
    }
}
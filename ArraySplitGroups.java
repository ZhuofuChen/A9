
// 给定一个数组，将其分成两个subgroup使得二者的和相等
// follow up： 如果是分成k个group怎么做？
// DFS PENDING...
import java.util.*;

public class ArraySplitGroups {
    public static void main(String[] args) {
        int[] a = new int[] { 1, 2, 3, 4, 8, 7, 9, 10 };
        System.out.println(splitArray(a));
    }

    // optimization
    public static List<List<Integer>> splitArray(int[] a) {
        int sum = 0;
        for (int ele : a) {
            sum += ele;
        }
        sum /= 2;
        Set<Integer> path = new HashSet<>();
        dfs(a, 0, path, sum); // finish fill path
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (!path.contains(i)) {
                x.add(a[i]);
            } else {
                y.add(a[i]);
            }
        }
        List<List<Integer>> ret = new ArrayList<>();
        ret.add(x);
        ret.add(y);
        return ret;
    }

    public static boolean dfs(int[] a, int idx, Set<Integer> path, int t) {
        if (t == 0) {
            return true;
        } else if (t < 0) {
            return false;
        }
        for (int i = idx; i < a.length; i++) {
            path.add(i);
            boolean temp = dfs(a, i + 1, path, t - a[i]);
            if (temp == true)
                return true;
            path.remove(i);
        }
        return false;
    }

    // public static List<List<Integer>> splitArray(int[] a) {
    // int sum = 0;
    // for (int ele : a) {
    // sum += ele;
    // }
    // sum /= 2;
    // List<Integer> res = new ArrayList<>();
    // List<Integer> path = new ArrayList<>();
    // dfs(a, 0, path, res, sum); // finish fill path
    // List<Integer> x = new ArrayList<>();
    // List<Integer> y = new ArrayList<>();
    // Set<Integer> set = new HashSet<>();
    // set.addAll(res);
    // for (int i = 0; i < a.length; i++) {
    // if (!set.contains(i)) {
    // x.add(a[i]);
    // } else {
    // y.add(a[i]);
    // }
    // }
    // List<List<Integer>> ret = new ArrayList<>();
    // ret.add(x);
    // ret.add(y);
    // return ret;
    // }

    // public static boolean dfs(int[] a, int idx, List<Integer> path, List<Integer>
    // res, int t) {
    // if (t == 0) {
    // res.addAll(path);
    // return true;
    // } else if (t < 0) {
    // return false;
    // }
    // for (int i = idx; i < a.length; i++) {
    // path.add(i);
    // boolean temp = dfs(a, i + 1, path, res, t - a[i]);
    // if (temp == true)
    // return true;
    // path.remove(path.size() - 1);
    // }
    // return false;
    // }
}
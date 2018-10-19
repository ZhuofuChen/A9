import java.util.*;

public class LeastTxn {
    public static void main(String[] args) {
        int n = 3;
        int[][] events = new int[][] { { 1, 10 }, { 2, 10 }, { 3, 5 }, { 1, 20 } };
        LeastTxn sol = new LeastTxn();
        List<int[]> res = sol.leastTxn(n, events);
        for (int[] ele : res) {
            System.out.println(ele[0] + " " + ele[1] + " " + ele[2]);
        }
    }

    // @Param n people
    // @Param events
    public List<int[]> leastTxn(int n, int[][] events) {
        int[] debts = new int[n + 1];
        int sum = 0;
        for (int[] e : events) {
            int per = e[0];
            int val = e[1];
            debts[per] -= val;
            sum += val;
        }
        int avg = sum / n;
        for (int i = 1; i < debts.length; i++) { // not 0 based key 3
            debts[i] += avg;
            System.out.print(debts[i] + " ");
        }
        System.out.println("is the debt array");
        List<int[]> path = new ArrayList<>();
        List<int[]> gpath = new ArrayList<>();
        Integer[] gcnt = new Integer[1];
        bt(debts, 1, path, 0, gcnt, gpath); // same as above start from 1
        return gpath;
    }

    public void bt(int[] debts, int idx, List<int[]> path, int cnt, Integer[] gcnt, List<int[]> gpath) { // key 2
        while (idx < debts.length && debts[idx] == 0)
            idx++;
        if (idx == debts.length) {
            if (gcnt[0] == null || cnt < gcnt[0]) {
                gcnt[0] = cnt;
                gpath.clear(); // Key 1
                gpath.addAll(path);
            }
            return;
        }
        int d = debts[idx];
        for (int i = idx + 1; i < debts.length; i++) {
            if (debts[i] * d < 0) {
                debts[i] += d;
                debts[idx] = 0;
                path.add(new int[] { idx, i, d });
                bt(debts, idx + 1, path, cnt + 1, gcnt, gpath);
                debts[i] -= d;
                debts[idx] = d;
                path.remove(path.size() - 1);
            }
        }
    }

}
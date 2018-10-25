import java.util.*;

public class Debt {

    // 还是写递归吧 递归比较好
    public static void main(String[] args) {
        Debt sol = new Debt();
        int[] debts = new int[] { -15, 5, 10 };
        List<int[]> res = sol.debt(debts);
        for (int[] d : res) {
            System.out.println(d[0] + " paied " + d[1] + " " + d[2] + " Dollars");
        }
        System.out.println();
        int[] debts2 = new int[] { -15, 5, 10 };
        List<int[]> path = new ArrayList<>();
        sol.bt(debts2, 0, path);
        for (int[] d : path) {
            System.out.println(d[0] + " paied " + d[1] + " " + d[2] + " Dollars");
        }
    }

    public void bt(int[] debts, int idx, List<int[]> path) {
        while (idx < debts.length && debts[idx] == 0)
            idx++;
        if (idx == debts.length)
            return;
        int d = debts[idx];
        for (int i = idx + 1; i < debts.length; i++) {
            if (debts[i] * d < 0) {
                debts[i] += d;
                debts[idx] = 0;// Not useful under this case
                path.add(new int[] { idx, i, d });
                bt(debts, idx + 1, path);
                break;
            }
        }
    }

    public List<int[]> debt(int[] debts) {
        List<int[]> res = new ArrayList<>();
        int idx = 0;
        while (idx < debts.length) {
            while (idx < debts.length && debts[idx] == 0) {
                idx++;
            }
            for (int i = idx + 1; i < debts.length; i++) {
                int d = debts[idx];
                if (debts[i] * d < 0) {
                    res.add(new int[] { idx, i, debts[idx] });
                    debts[i] += d;
                    debts[idx] = 0;
                    break;
                }
            }
            idx++;
        }
        return res;
    }
}
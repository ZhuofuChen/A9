import java.util.*;

// 坐标去点题，地里好像有人发过。给一堆点的坐标[(x,y)...]， 如果两个点的任意一个轴坐标相同（x或y轴共线）就可以去掉其中一个，
//一直这样消点直到无法继续。问最后最少剩下多少个点。
public class IntersectionEliminate {
    // DFS O（N） 【+ O(N))】
    // UF O(NlogN) [+ logN]

    public static void main(String[] args) {
        int[][] points = new int[][] { { 0, 2 }, { 1, 0 }, { 1, 3 }, { 2, 1 }, { 2, 2 }, { 1, 2 } };
        System.out.println(eliminateDFS(points, 4, 4));
        System.out.println(eliminateUF(points, 4, 4));
    }

    // 你也可以参考 eliminate Island 那题 做一个板子，然后把点都镶嵌上去 as 1
    // 然后iterate那个板子， 这里我不打算这么做，我打算直接根据set （which contains all points）
    // 来迭代直到set中无点
    public static int eliminateDFS(int[][] points, int m, int n) {
        Set<Integer> set = new HashSet<>();
        Map<Integer, List<int[]>> rowMap = new HashMap<>();
        Map<Integer, List<int[]>> colMap = new HashMap<>();
        for (int[] p : points) {
            int row = p[0];
            int col = p[1];
            if (!rowMap.containsKey(row)) {
                rowMap.put(row, new ArrayList<>());
            }
            if (!colMap.containsKey(col)) {
                colMap.put(col, new ArrayList<>());
            }
            rowMap.get(row).add(p);
            colMap.get(col).add(p);
            // important diff
            set.add(row * n + col);
        }
        int cnt = 0;
        while (!set.isEmpty()) {
            dfs(set.iterator().next(), set, rowMap, colMap, n);
            cnt++;
        }
        return cnt;
    }

    public static void dfs(int cur, Set<Integer> set, Map<Integer, List<int[]>> rowMap,
            Map<Integer, List<int[]>> colMap, int n) {
        int r = cur / n;
        int c = cur % n;
        if (!set.contains(r * n + c)) {
            return;
        }
        set.remove(cur);
        for (int[] next : rowMap.get(r)) {
            dfs(next[0] * n + next[1], set, rowMap, colMap, n);
        }
        for (int[] next : colMap.get(c)) {
            dfs(next[0] * n + next[1], set, rowMap, colMap, n);
        }
    }

    // if there is not m, n input. get it by yourself, [0,0] to the rightmost and
    // downmost
    public static int eliminateUF(int[][] points, int m, int n) {
        Map<Integer, List<int[]>> rowMap = new HashMap<>();
        Map<Integer, List<int[]>> colMap = new HashMap<>();
        for (int[] p : points) {
            int row = p[0];
            int col = p[1];
            if (!rowMap.containsKey(row)) {
                rowMap.put(row, new ArrayList<>());
            }
            if (!colMap.containsKey(col)) {
                colMap.put(col, new ArrayList<>());
            }
            rowMap.get(row).add(p);
            colMap.get(col).add(p);
        }
        UnionFind uf = new UnionFind(points, m, n);
        for (List<int[]> val : rowMap.values()) {
            for (int i = 1; i < val.size(); i++) {
                int[] l = val.get(i - 1);
                int[] r = val.get(i);
                uf.union(l[0] * n + l[1], r[0] * n + r[1]);
            }
        }
        for (List<int[]> val : colMap.values()) {
            for (int i = 1; i < val.size(); i++) {
                int[] l = val.get(i - 1);
                int[] r = val.get(i);
                uf.union(l[0] * n + l[1], r[0] * n + r[1]);
            }
        }
        return uf.cnt;
    }

    public static class UnionFind {
        int[] parent;
        int[] rank;
        int cnt;

        public UnionFind(int[][] points, int m, int n) {
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = -1;
            }
            for (int[] p : points) {
                int num = p[0] * n + p[1];
                parent[num] = num;
            }
            cnt = points.length;
        }

        public void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa == pb) {
                return;
            }
            cnt--;
            if (rank[pa] < rank[pb]) {
                parent[pa] = pb;
            } else {
                parent[pb] = pa;
                if (rank[pa] == rank[pb]) {
                    rank[pa]++;
                }
            }
        }

        public int find(int a) {
            while (a != parent[a]) {
                parent[a] = parent[parent[a]];
                a = parent[a];
            }
            return a;
        }
    }
}

// https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=451316&extra=&page=1
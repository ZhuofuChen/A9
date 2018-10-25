public class CollectingCandy {
    // 先写test case， 确定正确率额 再转成dp
    // rt lt 都是 r行决定的 Not r + 1行决定
    // 这一行的最后一个位置 是 下一行的第一个位置
    // dp hint，由下至上 dp r,c = dp (r + 1, lt) + Distance(c->rt->lt), dp(r + 1, rt) +
    // Distance(c->lt->rt)

    public int collect(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        Integer lastRow = null;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    lastRow = i + 1;
                    break;
                }
            }
            if (lastRow != null)
                break;
        }
        return dfs(grid, 0, 0, lastRow);
    }

    public int dfs(int[][] grid, int r, int c, int lastRow) {
        int m = grid.length;
        int n = grid[0].length;
        if (r == lastRow)
            return -1;
        int rt = c;
        int lt = c;
        int idx = c;
        while (idx < c) {
            if (grid[r][idx] == 1)
                rt = idx;
        }
        idx = c; // re-init
        while (idx >= 0) {
            if (grid[r][idx] == 1)
                lt = idx;
        }
        return Math.min(1 + c - lt + (rt - lt) + dfs(grid, r + 1, rt, lastRow),
                1 + rt - c + rt - lt + dfs(grid, r + 1, lt, lastRow));
    }

    // optimization 可以加map 来记忆
    // 也可以用dp 来做
}
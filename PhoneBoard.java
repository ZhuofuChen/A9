public class PhoneBoard {

    public static void main(String[] args) {
        System.out.println(neighborNumber(25));
    }

    public static int neighborNumber(int digits) {
        int[][] b = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { -1, 0, -1 } };
        int m = b.length;
        int n = b[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res += dfs(b, i, j, digits - 1);
            }
        }
        return res;
    }

    static int[][] dirs = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public static int dfs(int[][] b, int r, int c, int digits) {
        int m = b.length;
        int n = b[0].length;
        if (r < 0 || r >= m || c < 0 || c >= n || b[r][c] == -1) {
            return 0;
        }
        if (digits == 0) {
            return 1;
        }
        int res = 0;
        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            res += dfs(b, nr, nc, digits - 1);
        }
        return res;
    }
}
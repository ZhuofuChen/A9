public class SortArray {
    public static void main(String[] args) {
        int[] a = new int[] { -1, 0, 2 };
        int[] res = sortArray(a);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }

    public static int[] sortArray(int[] a) {
        int[] res = new int[a.length];
        int l = 0;
        int r = a.length - 1;
        int idx = a.length - 1;
        while (l < r) {
            if (a[l] * a[l] > a[r] * a[r]) {
                res[idx--] = a[l] * a[l];
                l++;
            } else {
                res[idx--] = a[r] * a[r];
                r--;
            }
        }
        return res;
    }
}
public class MergeSort{
    public static void main(String[] args) {
        MergeSort sol = new MergeSort();
        int[] test = new int[]{1,5,996,3,7};
        sol.mergeSort(test);
        for (int i : test) {
            System.out.print(i + " ");
        }
    }

    public int[] mergeSort(int[] a) {
        if (a == null || a.length == 0) return a;
        mergeSortHelper(a, 0, a.length - 1);
        return null;
    }

    public void mergeSortHelper(int[] a, int l, int r) {
        if (l == r) return;
        int m = l + (r-l)/ 2;
        mergeSortHelper(a, l, m);
        mergeSortHelper(a, m + 1, r);
        int[] temp = new int[r - l + 1];
        //merge
        int idx = 0;
        int xIdx = l;
        int yIdx = m + 1;
        while (xIdx <= m || yIdx <= r) {
            Integer xNum = xIdx <= m ? a[xIdx] : null;
            Integer yNum = yIdx <= r ? a[yIdx] : null;
            if (xNum == null) {
                temp[idx++] = a[yIdx++];
            } else if (yNum == null) {
                temp[idx++] = a[xIdx++];
            } else if (a[xIdx] < a[yIdx]) {
                temp[idx++] = a[xIdx++];
            } else {
                temp[idx++] = a[yIdx++];
            }
        }
        idx = 0;
        for (int i = l; i <= r; i++) {
            a[i] = temp[idx++];
        }
    }
}
import java.util.*;

public class FindNextLargest{
    public static void main(String[] args) {
        int[] test = new int[]{15, 2, 7, 3};
        int[] res = findNextLargest(test);
        for (int i : res) {
            System.out.print(i + "#");
        }
    }

    public static int[] findNextLargest(int[] input) {
        int[] res = new int[input.length];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < input.length; i++) {
            int c = input[i];
            while (!stack.isEmpty() && input[stack.peek()] < c) {
                res[stack.pop()] = i;
                // System.out.println(i);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            res[stack.pop()] = -1;
        }
        return res;
    }
}
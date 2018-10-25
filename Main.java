import java.util.*;

public class Main {
    public static void main(String[] args) {
        // String a = "hello";
        // String b = "el";
        // System.out.println(a.indexOf(b));
        // conclusion: change the obj in the head will not trigger re-arrangement
        PriorityQueue<D> heap = new PriorityQueue<>((c, d) -> c.val - d.val);
        D hello = new D("hello", 1);
        D world = new D("world", 2);
        heap.offer(hello);
        heap.offer(world);
        world.val = 0;
        while (!heap.isEmpty()) {
            System.out.println(heap.poll().s);
        }
    }

    public static class D {
        String s;
        int val;

        public D(String s, int val) {
            this.s = s;
            this.val = val;
        }
    }
}
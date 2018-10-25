import java.util.Map;

// 两个原则   一个是不能有环  visited contains  解决duplicate  
// 二个是 visited 到所有点   用size == n来确保
public class ValidTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isValidTree(List<TreeNode> nodes) {
        TreeNode root = null;
        Map<TreeNode, Integer> map = new HashMap<>();
        for (TreeNode node : nodes) {
            if (node.left != null) {
                map.put(node.left, map.getOrDefault(node.left, 0) + 1);
            }
            if (node.right != null) {
                map.put(node.right, map.getOrDefault(node.right, 0) + 1);
            }
        }
        for (TreeNode n : nodes) {
            if (!map.cotnainsKey(n)) {
                root = n;
                break;
            }
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (visited.contains(cur))
                return false;
            visited.add(curr);
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        return visisted.szie() == nodes.size();
    }
}
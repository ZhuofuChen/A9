public class LeafNodeSUm {
    // 4. 给了个tree的题目，先求所有leaf node内容的和，用recursion和iterative写.
    // follow up O(1)space做
    public static void main(String[] args) {
        // writing test case ... pending...
    }

    public static int sumLeftNode(TreeNode root) {
        Int sum = 0;
        while (root != null) {
            if (root.left == null && root.right == null) {
                sum += root.val;
                root = root.parent;
            } else {
                if (root.left != null && !root.lv) {
                    root = root.left;
                    root.lv = true;
                } else if (root.right != null && !root.rv) {
                    root = root.right;
                    root.rv = true;
                } else {
                    root = root.parent;
                }
            }
        }
        return sum;
    }

    // iterative
    public static int sumLeftNode(TreeNode root) {
        if (root == null)
            return 0;
        int sum = 0;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.left == null && cur.right == null) {
                sum += cur.val;
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return sum;
    }

    // recursion
    public static int sumLeftNode(TreeNode root) {
        if (root == null)
            return 0;
        return helper(root);
    }

    public static int helper(TreeNode root) {
        if (root.left == null && root,right == null) {
	        return root.val;
        }
        int sum = 0;
        if (root.left != null) {
            sum += helper(root.left);
        }
        if (root.right != null) {
            Sum += helper(root.right);
        }
        return sum;
    }
}

// https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=451316&extra=&page=1
//https://leetcode.com/problems/same-tree/description/
//第二轮：给定两颗树，判断它们是否有相同的shape
//follow up：如果允许树的节点拥有任意数目的父节点，如何判断？  => 一样的做法，这次用Map来记录traverse过的node，以防重复计算 O（2^n）=>O（n^2）
public class SameTree {
    /**
     * Definition for a binary tree node. public class TreeNode { int val; TreeNode
     * left; TreeNode right; TreeNode(int x) { val = x; } }
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // follow up
    public boolean isSameTreeAdvanced(TreeNode p, TreeNode q) {
        Map<TreeNode, Map<TreeNode, Boolean>> map = new HashMap<>();
        return helper(p, q, map);
    }

    // This solution is wrong... Pending
    public boolean helper(TreeNode p, TreeNode q, Map<TreeNode, Map<TreeNode, Boolean>> map) {
        if (map.containsKey(p) && map.get(p).containsKey(q)) {
            return map.get(p).get(q);
        }
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        Boolean res = p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        if (!map.containsKey(p)) {
            map.put(p, new HashMap());
        }
        map.get(p).put(q, res);

        return res;
    }
}
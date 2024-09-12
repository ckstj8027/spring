package Tree.postorder;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (root.val == p.val || root.val == q.val) return root;
        else if (left != null && right != null) return root;
        else if (left != null) return left;
        else if (right != null) return right;
        else return null;
    }
}

public class lowestCommonAncestor{
    public static void main(String[] args) {
        // 이진 트리 생성
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        Solution solution = new Solution();

        // 두 노드 p와 q 지정
        TreeNode p = root.left;
        TreeNode q = root.right;

        // 최소 공통 조상 찾기
        TreeNode lca = solution.lowestCommonAncestor(root, p, q);

        // 결과 출력
        System.out.println("최소 공통 조상의 값: " + lca.val);
    }
}


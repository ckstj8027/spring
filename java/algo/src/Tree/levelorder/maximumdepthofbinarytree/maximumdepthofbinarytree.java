package Tree.levelorder.maximumdepthofbinarytree;
import java.util.Queue;
import java.util.LinkedList;
import java.lang.Math;
// TreeNode 클래스 정의
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) { this.val = val; }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

// Solution_L 클래스 정의
class Solution_L {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int maxDepth = 0;
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 1));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> pair = queue.poll();
            TreeNode currentNode = pair.getLeft();
            int currentDepth = pair.getRight();
            maxDepth = Math.max(maxDepth, currentDepth);
            if (currentNode.left != null) {
                queue.offer(new Pair<>(currentNode.left, currentDepth + 1));
            }
            if (currentNode.right != null) {
                queue.offer(new Pair<>(currentNode.right, currentDepth + 1));
            }
        }

        return maxDepth;
    }

    class Pair<L, R> {
        L left;
        R right;

        private Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }

        public L getLeft() {
            return this.left;
        }

        public R getRight() {
            return this.right;
        }
    }
}

// Solution_p 클래스 정의

class Solution_p {
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        int max_depth = Math.max(left,right) + 1;
        return max_depth;
    }
}

public class maximumdepthofbinarytree {
    public static void main(String[] args) {
        // 이진 트리 생성
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        // Solution_L 객체 생성 및 실행
        Solution_L solution_L = new Solution_L();
        int maxDepth_L = solution_L.maxDepth(root);
        System.out.println("이진 트리의 최대 깊이 (Level Order): " + maxDepth_L);

        // Solution_P 객체 생성 및 실행
        Solution_p solution_p = new Solution_p();
        int maxDepth_P = solution_p.maxDepth(root);
        System.out.println("이진 트리의 최대 깊이 (Post Order): " + maxDepth_P);
    }
}

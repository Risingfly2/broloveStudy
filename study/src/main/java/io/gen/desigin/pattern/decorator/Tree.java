package io.gen.desigin.pattern.decorator;

import java.util.*;

public class Tree {

    public class TreeNode {
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


    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
//    ------------------------------------------------



    public List<Integer> inorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }


    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = null;
        stack.push(root);
        while (!stack.isEmpty()){
            cur = stack.pop();
            res.add(cur.val);
            if (cur.right != null){
                stack.push(cur.right);
            }
            if (cur.left != null){
                stack.push(cur.left);
            }
        }
        return res;
    }


    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null){
            return res;
        }
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            res.addFirst(cur.val);
            if (cur.left != null){
                stack.push(cur.left);
            }
            if (cur.right != null){
                stack.push(cur.right);
            }
        }
        return res;
    }


    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }


    public boolean isBalanced(TreeNode root) {
        if (root == null){
            return true;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int max;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        max = Math.max(max, left + right);
        diameterOfBinaryTree(root.left);
        diameterOfBinaryTree(root.right);
        return max;
    }


    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return null;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }


    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null){
            return root2;
        }
        if (root2 == null){
            return root1;
        }
        TreeNode root = new TreeNode(root1.val + root2.val);
        root.left = mergeTrees(root1.left, root2.left);
        root.right = mergeTrees(root1.right, root2.right);
        return root;
    }


    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null){
            return false;
        }
        int tmp = targetSum - root.val;
        if (root.left == null && root.right == null && tmp == 0){
            return true;
        }
        return hasPathSum(root.left, tmp) || hasPathSum(root.right, tmp);
    }

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null){
            return 0;
        }
        return getPath(root, targetSum) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum);
    }

    private int getPath(TreeNode node, int targetSum){
        if (node == null){
            return 0;
        }
        return (node.val == targetSum ? 1 : 0) + getPath(node.left, targetSum - node.val) + getPath(node.right, targetSum - node.val);
    }


    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null){
            return false;
        }
        return isSame(s,t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isSame(TreeNode s, TreeNode t){
        if (s == null && t == null){
            return true;
        }
        if (s == null || t == null){
            return false;
        }
        if (s.val != t.val){
            return false;
        }
        return isSame(s.left, t.left) && isSame(s.right, t.right);
    }

    public int minDepth(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (left == 0 || right == 0){
            return left + right + 1;
        }
        return Math.min(left, right) + 1;
    }

    public int sumOfLeftLeaves(TreeNode root){
        if (root == null){
            return 0;
        }
        if (isLeaf(root.left)){
            return root.left.val + sumOfLeftLeaves(root.right);
        }
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    private boolean isLeaf(TreeNode node){
        if (node == null){
            return false;
        }
        return node.left == null && node.right == null;
    }


    private int path;
    public int longestUnivaluePath(TreeNode root) {
        if (root == null){
            return 0;
        }
        dfs(root);
        return path;
    }

    private int dfs(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        int leftPath = root.left != null && root.left.val == root.val ? left + 1 : 0;
        int rightPath = root.right != null && root.right.val == root.val ? right + 1 : 0;
        path = Math.max(path, leftPath + rightPath);
        return Math.max(leftPath, rightPath);
    }


    public int rob(TreeNode root){
        if (root == null){
            return 0;
        }
        int val1 = root.val;
        if (root.left != null){
            val1 += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null){
            val1 += rob(root.right.left) + rob(root.right.right);
        }
        int val2 = rob(root.left) + rob(root.right);
        return Math.max(val2, val1);
    }

    public List<Double> averageOfLevels(TreeNode root){
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
            res.add(sum/size);
        }
        return res;
    }

    public int findBottomLeftValue(TreeNode root) {
        if (root == null){
            return -1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            root = queue.poll();
            if (root.right != null) queue.add(root.right);
            if (root.left != null) queue.add(root.left);
        }
        return root.val;
    }

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null){
            return null;
        }
        if (root.val < low){
            return trimBST(root.right, low, high);
        }
        if (root.val > high){
            return trimBST(root.left, low, high);
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }


    int count = 0;
    int number = 0;
    public int kthSmallest(TreeNode root, int k) {
        count = k;
        helper(root);
        return number;
    }

    public void helper(TreeNode root){
        if (root.left != null){
            helper(root.left);
        }
        count--;
        if (count == 0){
            number = root.val;
        }
        if (root.right != null){
            helper(root.right);
        }
    }

    private int sum;
    public TreeNode convertBST(TreeNode root) {
        if (root == null){
            return null;
        }
        help(root);
        return root;
    }

    public void help(TreeNode root){
        if (root == null) return;
        help(root.right);
        sum += root.val;
        root.val = sum;
        helper(root.left);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
        return root;
    }




    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q){
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        TreeNode right = lowestCommonAncestor1(root.right, p, q);
        if (left == null && right == null) return null;
        if (left != null && right != null) return root;
        return left == null ? right : left;
    }


    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0){
            return null;
        }
        return convert(nums, 0, nums.length - 1);
    }

    private TreeNode convert(int[] nums, int start, int end){
        if (start > end){
            return null;
        }
        int mid = start + (end - start) / 2;

        TreeNode node = new TreeNode(nums[mid]);
        node.left = convert(nums, start, mid - 1);
        node.right = convert(nums, mid + 1, end);
        return node;
    }

    public TreeNode sortedListToBST(ListNode head){
        if (head == null){
            return null;
        }
        if (head.next == null){
            return new TreeNode(head.val);
        }
        ListNode pre = getThePreMidNode(head);
        ListNode mid = pre.next;
        pre.next = null;
        TreeNode root = new TreeNode(mid.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);
        return root;
    }

    private ListNode getThePreMidNode(ListNode head){
        ListNode fast = head.next;
        ListNode slow = head;
        ListNode pre = head;
        while (fast != null && fast.next != null){
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return pre;
    }


    public boolean findTarget(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        inOrder(root, nums);
        int left = 0, right = nums.size() - 1;
        while (left < right){
            int sum = nums.get(left) + nums.get(right);
            if (sum == k){
                return true;
            }else if (sum < k){
                left++;
            }else {
                right--;
            }
        }
        return false;
    }

    private void inOrder(TreeNode root, List<Integer> nums){
        if (root == null){
            return;
        }
        inOrder(root.left, nums);
        nums.add(root.val);
        inOrder(root.right, nums);
    }

    public boolean findTargetII(TreeNode root, int k){
        HashSet<Integer> set = new HashSet<>();
        return dfs(root, set, k);
    }
    private boolean dfs(TreeNode root, HashSet<Integer> set, int k){
        if (root == null) return false;
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
        return dfs(root.left, set, k) || dfs(root.right, set, k);
    }

    private int min = Integer.MAX_VALUE;
    private TreeNode pre;
    public int getMinimumDifference(TreeNode root){
        if (root == null){
            return 0;
        }
        getMin(root);
        return min;
    }
    private void getMin(TreeNode root){
        if (root == null) return;
        if (root.left != null){
            getMin(root.left);
        }
        if (pre != null){
            min = Math.min(min, root.val - pre.val);
        }
        pre = root;
        if (root.right != null){
            getMin(root.right);
        }
    }


    public int[] findMode(TreeNode root) {
        if (root == null){
            return new int[0];
        }
        List<Integer> nums = new ArrayList<>();
        inOrder(root, nums);
        List<Integer> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        nums.forEach(x -> {
            if (set.contains(x)){
                res.add(x);
            }
            set.add(x);
        });
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        if (res.size() == 0){
            arr = new int[nums.size()];
            for (int i = 0; i < nums.size(); i++) {
                arr[i] = nums.get(i);
            }
        }
        return arr;
    }
}



package io.gen.desigin.pattern.decorator;

import java.util.List;
import java.util.Stack;

public class BSTIterator {
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

    private Stack<TreeNode> stack = new Stack<>();
    public BSTIterator(TreeNode root) {
        pushAllLeft(root);
    }

    public int next() {
        TreeNode tmp = stack.pop();
        pushAllLeft(tmp.right);
        return tmp.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void pushAllLeft(TreeNode node){
        for (; node != null; stack.push(node
        ), node = node.left);
    }
}

package problems.problem1660;

import datastructures.java.TreeNode;

import java.util.*;

class Solution {
    public TreeNode correctBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<TreeNode> currentLevel = new ArrayList<>();
        Map<Integer, TreeNode> nodeToParent = new HashMap<>();
        currentLevel.add(root);

        while (!currentLevel.isEmpty()) {
            Set<Integer> valuesAtLevel = new HashSet<>();
            List<TreeNode> nextLevel = new ArrayList<>();
            for (TreeNode node : currentLevel) {
                valuesAtLevel.add(node.val);
                TreeNode left = node.left;
                if (left != null) {
                    nextLevel.add(left);
                    nodeToParent.put(left.val, node);
                }

                TreeNode right = node.right;
                if (right != null) {
                    nextLevel.add(right);
                    nodeToParent.put(right.val, node);
                }
            }

            for (TreeNode node : currentLevel) {
                if (node.right != null && valuesAtLevel.contains(node.right.val)) {
                    TreeNode parent = nodeToParent.get(node.val);
                    if (parent.left != null && parent.left.val == node.val) {
                        parent.left = null;
                        return root;
                    }
                    if (parent.right != null && parent.right.val == node.val) {
                        parent.right = null;
                        return root;
                    }
                }
            }

            currentLevel = nextLevel;
        }
        return root;
    }
}

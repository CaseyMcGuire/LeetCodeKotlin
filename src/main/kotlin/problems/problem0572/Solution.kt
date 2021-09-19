package problems.problem0572

import datastructures.TreeNode

class Solution {
  fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
    if (equals(root, subRoot)) {
      return true
    }
    if (root?.left != null && isSubtree(root.left, subRoot)) {
      return true
    }
    if (root?.right != null && isSubtree(root.right, subRoot)) {
      return true
    }
    return false
  }

  fun equals(root: TreeNode?, subRoot: TreeNode?): Boolean {
    if (root == null && subRoot == null) {
      return true
    }
    if (root != null && subRoot == null || root == null && subRoot != null) {
      return false
    }
    if (root!!.`val` != subRoot!!.`val`) {
      return false
    }
    return equals(root.left, subRoot.left) && equals(root.right, subRoot.right)
  }
}
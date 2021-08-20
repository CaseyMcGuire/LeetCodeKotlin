package problems.problem0100

import datastructures.TreeNode

class Solution {
  fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
    if (p == null && q == null) {
      return true
    }
    if (p == null && q != null || p != null && q == null) {
      return false
    }
    if (p?.`val` != q?.`val`) {
      return false
    }
    return isSameTree(p?.right, q?.right) && isSameTree(p?.left, q?.left)
  }
}
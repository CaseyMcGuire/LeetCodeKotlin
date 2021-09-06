package problems.problem0235

import datastructures.TreeNode

class Solution {
  fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    if (root == null || p == null || q == null) {
      return null
    }
    return recurse(root, p!!, q!!)
  }

  fun recurse(root: TreeNode?, p: TreeNode, q: TreeNode): TreeNode? {
    if (root == null) {
      return null
    }

    if (root.`val` == p.`val` || root.`val` == q.`val`) {
      return root
    }

    if (root.`val` > p.`val` && root.`val` > q.`val`) {
      return recurse(root.left, p, q)
    }
    else if (root.`val` < p.`val` && root.`val` < q.`val`) {
      return recurse(root.right, p, q)
    }
    else {
      return root
    }

  }
}
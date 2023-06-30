package problems.problem0700

import datastructures.TreeNode

class Solution {
  fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
    var curNode = root
    while (curNode != null) {
      if (curNode.`val` == `val`) {
        break
      }
      if (curNode.`val` > `val`) {
        curNode = curNode.left
      }
      else {
        curNode = curNode.right
      }
    }
    return curNode
  }
}
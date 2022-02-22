package problems.problem0515

import datastructures.TreeNode

class Solution {
  fun largestValues(root: TreeNode?): List<Int> {

    val largestValuePerRow = mutableListOf<Int>()
    fun recurse(curNode: TreeNode?, row: Int) {
      if (curNode == null) {
        return
      }

      if (largestValuePerRow.size == row) {
        largestValuePerRow.add(curNode.`val`)
      }
      else if (largestValuePerRow[row] < curNode.`val`){
        largestValuePerRow[row] = curNode.`val`
      }

      recurse(curNode.right, row + 1)
      recurse(curNode.left, row + 1)
    }
    recurse(root, 0)
    return largestValuePerRow
  }
}
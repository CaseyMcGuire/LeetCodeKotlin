package problems.problem0894

import datastructures.TreeNode

class Solution {
  fun allPossibleFBT(n: Int): List<TreeNode?> {

    fun recurse(n: Int): MutableList<TreeNode>? {
      if (n == 0) {
        return mutableListOf()
      }
      else if (n == 1) {
        return mutableListOf(TreeNode(0))
      }
      else if (n == 2) {
        // can't create a full tree with only two nodes
        return null
      }

      val trees = mutableListOf<TreeNode>()
      var curN = n - 1
      for (i in 0 until curN) {
        val leftSubtrees = recurse(i)
        val rightSubtrees = recurse(curN - i)
        if (leftSubtrees == null || rightSubtrees == null) {
          continue
        }
        for (leftSubtree in leftSubtrees) {
          for (rightSubtree in rightSubtrees) {
            val root = TreeNode(0)
            root.left = leftSubtree
            root.right = rightSubtree
            trees.add(root)
          }
        }
      }
      return trees
    }
    return recurse(n) ?: emptyList()

  }
}

fun main(args: Array<String>) {
  println(Solution().allPossibleFBT(7))
}
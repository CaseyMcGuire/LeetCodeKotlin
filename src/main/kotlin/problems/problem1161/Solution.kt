package problems.problem1161

import datastructures.TreeNode

class Solution {
  fun maxLevelSum(root: TreeNode?): Int {
    if (root == null) {
      return -1
    }

    val levelToSum = mutableMapOf<Int, Int>()
    fun recurse(node: TreeNode?, level: Int) {
      if (node == null) {
        return
      }

      levelToSum.merge(level, node.`val`) { cur, acc -> cur + acc }
      recurse(node.left, level + 1)
      recurse(node.right, level + 1)
    }

    recurse(root, 1)

    // find largest sum
    var largestEntries = mutableListOf<Map.Entry<Int, Int>>()
    var largestValue = Integer.MIN_VALUE
    for (entry in levelToSum.entries) {
      val sum = entry.value
      val level = entry.key
      if (sum > largestValue) {
        largestEntries = mutableListOf<Map.Entry<Int, Int>>(entry)
        largestValue = sum
      }
      else if (sum == largestValue) {
        largestEntries.add(entry)
      }
    }
    largestEntries.sortBy { it.key }
    return largestEntries[0].key
  }
}
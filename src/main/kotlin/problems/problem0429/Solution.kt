package problems.problem0429

import datastructures.Node

class Solution {
  fun levelOrder(root: Node?): List<List<Int>> {
    if (root == null) {
      return emptyList()
    }
    val levels = mutableListOf<List<Int>>()
    var curLevel = mutableListOf<Node>(root)

    while (curLevel.isNotEmpty()) {
      val nextLevel = mutableListOf<Node>()
      val curValues = mutableListOf<Int>()
      for (node in curLevel) {
        curValues.add(node.`val`)
        for (child in node.children) {
          if (child != null) {
            nextLevel.add(child)
          }
        }
      }

      levels.add(curValues)
      curLevel = nextLevel
    }

    return levels
  }
}
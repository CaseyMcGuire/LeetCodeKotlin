package problems.problem0102

import datastructures.TreeNode
import java.util.*

class Solution {
  fun levelOrder(root: TreeNode?): List<List<Int>> {
    if (root == null) {
      return emptyList()
    }
    val levels = mutableListOf<List<Int>>()
    var currentLevelInt = mutableListOf<Int>()
    var currentLevel = LinkedList<TreeNode>()
    currentLevel.add(root)
    var nextLevel = LinkedList<TreeNode>()
    while (true) {
      val currentNode = currentLevel.removeFirst()
      if (currentNode.left != null) {
        nextLevel.add(currentNode.left!!)
      }
      if (currentNode.right != null) {
        nextLevel.add(currentNode.right!!)
      }
      currentLevelInt.add(currentNode.`val`)
      if (currentLevel.isEmpty() && nextLevel.isEmpty()) {
        levels.add(currentLevelInt)
        currentLevelInt = mutableListOf()
        break
      }
      else if (currentLevel.isEmpty()) {
        levels.add(currentLevelInt)
        currentLevel = nextLevel
        nextLevel = LinkedList()
        currentLevelInt = mutableListOf()
      }
    }
    return levels
  }
}
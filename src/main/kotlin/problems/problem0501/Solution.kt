package problems.problem0501

import datastructures.TreeNode
import java.util.*
import kotlin.collections.HashMap

class Solution {
  fun findMode(root: TreeNode?): IntArray {
    if (root == null) {
      return IntArray(0)
    }
    var currentHighestFrequency = 0
    var highestFrequencyElements = mutableListOf<Int>()
    val frequencyMap = HashMap<Int, Int>()
    val nodes = ArrayDeque<TreeNode>()
    nodes.add(root)

    while (!nodes.isEmpty()) {
      val currentNode = nodes.pop()
      val (left, right) = Pair(currentNode.left, currentNode.right)
      if (left != null) {
        nodes.push(left)
      }
      if (right != null) {
        nodes.push(right)
      }

      val frequency = frequencyMap.merge(currentNode.`val`, 1) { cur, iter -> cur + iter}!!
      if (frequency > currentHighestFrequency) {
        currentHighestFrequency = frequency
        highestFrequencyElements = mutableListOf(currentNode.`val`)
      }
      else if (frequency == currentHighestFrequency) {
        highestFrequencyElements.add(currentNode.`val`)
      }
    }
    return highestFrequencyElements.toIntArray()
  }
}
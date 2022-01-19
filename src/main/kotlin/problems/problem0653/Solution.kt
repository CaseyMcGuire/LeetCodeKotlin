package problems.problem0653

import datastructures.TreeNode
import java.util.*

class Solution {
  fun findTarget(root: TreeNode?, k: Int): Boolean {
    val frequencyMap = createFrequencyMap(root)
    if (frequencyMap.isEmpty()) {
      return false
    }
    for (entry in frequencyMap.entries) {
      val elem = entry.key
      val remainder = k - elem
      val other = frequencyMap[remainder]
      if (other == null) {
        continue
      }
      if (remainder == elem) {
        if (entry.value >= 2) {
          return true
        }
      }
      else {
        return true
      }
    }
    return false
  }

  private fun createFrequencyMap(root: TreeNode?): Map<Int, Int> {
    val queue = LinkedList<TreeNode?>()
    queue.addLast(root)
    val frequencyMap = mutableMapOf<Int, Int>()
    while (queue.isNotEmpty()) {
      val elem = queue.removeFirst()
      if (elem == null) {
        continue
      }
      frequencyMap.merge(elem.`val`, 1) { cur, acc -> cur + acc }
      queue.addLast(elem.left)
      queue.addLast(elem.right)
    }
    return frequencyMap
  }
}
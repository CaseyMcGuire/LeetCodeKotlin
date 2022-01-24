package problems.problem1022

import datastructures.TreeNode
import java.util.*

class Solution {
  fun sumRootToLeaf(root: TreeNode?): Int {
    val binaryNumbers = mutableListOf<String>()
    val currentNumber = LinkedList<Int>()

    fun dfs(node: TreeNode?) {
      if (node == null) {
        return
      }
      currentNumber.addLast(node.`val`)
      if (node.left == null && node.right == null) {
        binaryNumbers.add(currentNumber.joinToString(""))
      }
      else {
        dfs(node.left)
        dfs(node.right)
      }
      currentNumber.removeLast()
    }
    dfs(root)
    return binaryNumbers.map { binaryToInt(it) }.sum()
  }

  private fun binaryToInt(binary: String): Int {
    var power = 0
    var sum = 0
    for (i in binary.indices.reversed()) {
      if (binary[i] == '1') {
        sum += Math.pow(2.0, power.toDouble()).toInt()
      }
      power++
    }
    return sum
  }
}
package problems.problem0536

import datastructures.TreeNode
import java.util.*

class Solution {
  fun str2tree(s: String): TreeNode? {
    if (s.isEmpty()) {
      return null
    }
    val stack = ArrayDeque<TreeNode>()
    val num = getNextNum(0, s)
    var i = num.toString().length
    val root = TreeNode(num)
    stack.push(root)
    while (i < s.length) {
      if (s[i] == '(') {
        i++
      }
      else if (s[i] == ')') {
        stack.pop()
        i++
      }
      else {
        val nextNum = getNextNum(i, s)
        val node = TreeNode(nextNum)
        val previousNode = stack.peek()
        if (previousNode.left == null) {
          previousNode.left = node
        }
        else {
          previousNode.right = node
        }
        stack.push(node)
        i += nextNum.toString().length
      }
    }
    return root
  }

  private fun getNextNum(i: Int, s: String): Int {
    var cur = i
    val builder = StringBuilder()
    while (cur < s.length && s[cur] != '(' && s[cur] != ')') {
      builder.append(s[cur])
      cur++
    }
    return builder.toString().toInt()
  }
}

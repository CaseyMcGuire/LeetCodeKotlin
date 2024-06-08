package problems.problem1047

import java.util.*

class Solution {
  fun removeDuplicates(s: String): String {
    val stack = ArrayDeque<Char>()
    for (c in s) {
      stack.addLast(c)
      if (stack.size >= 2) {
        val top = stack.removeLast()
        val secondTop = stack.removeLast()
        if (top != secondTop) {
          stack.addLast(secondTop)
          stack.addLast(top)
        }
      }
    }
    return stack.joinToString("")
  }
}
package problems.problem0844

import java.util.*

class Solution {
  fun backspaceCompare(s: String, t: String): Boolean {
    fun computeWithBackspaces(str: String): String {
      val strStack = ArrayDeque<Char>()
      for (c in str.toCharArray()) {
        if (c == '#') {
          if (strStack.isNotEmpty()) {
            strStack.pop()
          }
        }
        else {
          strStack.push(c)
        }
      }
      return strStack.joinToString("")
    }
    return computeWithBackspaces(s) == computeWithBackspaces(t)
  }
}
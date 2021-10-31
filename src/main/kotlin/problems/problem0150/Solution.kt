package problems.problem0150

import java.util.*

class Solution {
  fun evalRPN(tokens: Array<String>): Int {
    val stack = ArrayDeque<Int>()
    for (token in tokens) {
      if (token == "+" || token == "-" || token == "*" || token == "/") {
        val first = stack.pop()
        val second = stack.pop()
        val result = when (token) {
          "+" -> second + first
          "-" -> second - first
          "*" -> second * first
          "/" -> second / first
          else -> throw RuntimeException()
        }
        stack.push(result)
      }
      else {
        stack.push(token.toInt())
      }
    }
    return stack.pop()
  }
}
package problems.problem0020

import java.util.*

class Solution {
  fun isValid(s: String): Boolean {
    val stack = ArrayDeque<Char>()
    for (c in s.toCharArray()) {
      if (c == '(' || c == '[' || c == '{') {
        stack.push(c)
      }
      else {
        if (stack.isEmpty()) {
          return false
        }
        val matches = when (c) {
          '}' -> stack.pop() == '{'
          ']' -> stack.pop() == '['
          ')' -> stack.pop() == '('
          else -> false
        }
        if (!matches) {
          return false
        }
      }
    }
    return stack.isEmpty()
  }
}
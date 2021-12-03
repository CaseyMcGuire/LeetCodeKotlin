package problems.problem0301

import java.util.*

class Solution {
  fun removeInvalidParentheses(s: String): List<String> {
    val queue = LinkedList<String>()
    val validStrings = mutableListOf<String>()
    queue.addFirst(s)
    val visited = mutableSetOf<String>()
    while (queue.isNotEmpty()) {
      val str = queue.removeFirst()
      if (!visited.add(str)) {
        continue
      }
      if (validStrings.isNotEmpty() && validStrings[0].length > str.length) {
        break
      }
      if (isValidParens(str)) {
        validStrings.add(str)
      }
      else {
        for (i in str.indices) {
          queue.addLast(str.removeAt(i))
        }
      }
    }

    return validStrings.toSet().toList()

  }

  fun String.removeAt(i: Int): String {
    if (i == 0) {
      return this.substring(1)
    }
    else if (i == this.length - 1) {
      return this.substring(0, this.length - 1)
    }
    else {
      return this.substring(0, i) + this.substring(i + 1)
    }
  }

  fun isValidParens(s: String): Boolean {
    val stack = ArrayDeque<Char>()
    for (c in s) {
      if (c == ')') {
        if (stack.isEmpty()) {
          return false
        }
        else {
          stack.pop()
        }
      }
      else if (c == '(') {
        stack.push(c)
      }
    }
    return stack.isEmpty()
  }
}
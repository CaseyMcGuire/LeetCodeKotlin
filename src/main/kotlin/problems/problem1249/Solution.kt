package problems.problem1249

import java.util.*

class Solution {
  fun minRemoveToMakeValid(s: String): String {
    val stack = ArrayDeque<ParenIndex>()
    val indicesToRemove = mutableSetOf<Int>()
    for (i in s.indices) {
      val paren = s[i]
      val pi = ParenIndex(paren, i)
      if (paren == ')') {
        if (stack.isEmpty()) {
          indicesToRemove.add(i)
        }
        else {
          stack.pop()
        }
      }
      else if (paren == '(') {
        stack.push(pi)
      }

    }

    for (pi in stack) {
      indicesToRemove.add(pi.index)
    }

    val builder = StringBuilder()
    for (i in s.indices) {
      if (indicesToRemove.contains(i)) {
        continue
      }
      builder.append(s[i])
    }
    return builder.toString()
  }
}

data class ParenIndex(val paren: Char, val index: Int)
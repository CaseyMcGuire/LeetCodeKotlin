package problems.problem0227

import java.util.*

class Solution {
  fun calculate(s: String): Int {
    val sFiltered = s.filter { it != ' ' }
    val tokens = mutableListOf<String>()
    var cur = StringBuilder()
    for (i in sFiltered.indices) {
      val token = sFiltered[i]
      if (token == '+' || token == '-' || token == '*' || token == '/') {
        tokens.add(cur.toString())
        cur = StringBuilder()
        tokens.add(token.toString())
      }
      else {
        cur.append(token)
      }
    }
    tokens.add(cur.toString())

    var i = 1
    val evaluated = LinkedList<String>()
    evaluated.addFirst(tokens[0])
    while (i < tokens.size) {
      val cur = tokens[i]
      if (cur == "*" || cur == "/") {
        val last = evaluated.removeLast().toInt()
        val next = tokens[i + 1].toInt()
        val result = if (cur == "/") {
          last / next
        }
        else {
          last * next
        }
        evaluated.addLast(result.toString())
      }
      else {
        evaluated.addLast(cur)
        evaluated.addLast(tokens[i + 1])
      }
      i += 2
    }

    var result = evaluated.removeFirst().toInt()
    while (evaluated.isNotEmpty()) {
      val operand = evaluated.removeFirst()
      if (operand == "-") {
        result -= evaluated.removeFirst().toInt()
      }
      else {
        result += evaluated.removeFirst().toInt()
      }
    }

    return result
  }
}
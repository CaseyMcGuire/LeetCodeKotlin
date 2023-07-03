package problems.problem0224

import java.util.*

class Solution {
  fun calculate(s: String): Int {
    var i = 0
    fun recurse(): String {
      val curNums = LinkedList<String>()

      var curNum = StringBuilder()
      while (true) {

        if (i >= s.length) {
          if (curNum.isNotEmpty()) {
            curNums.addLast(curNum.toString())
          }
          break
        }
        if (s[i] == '-') {
          if (curNum.isNotEmpty()) {
            curNums.addLast(curNum.toString())
            curNum = StringBuilder()
          }
          curNums.addLast(s[i].toString())
          i++
        }
        else if (s[i] == '+') {
          if (curNum.isNotEmpty()) {
            curNums.addLast(curNum.toString())
            curNum = StringBuilder()
          }
          curNums.addLast(s[i].toString())
          i++
        }
        else if (s[i] == '(') {
          i++ // move past opening paren
          val evaluated = recurse()
          i++ // move past closing paren
          curNums.addLast(evaluated)
        }
        else if (s[i] == ')') {
          if (curNum.isNotEmpty()) {
            curNums.addLast(curNum.toString())
          }
          break
        }
        else if (s[i] == ' ') {
          if (curNum.isNotEmpty()) {
            curNums.addLast(curNum.toString())
            curNum = StringBuilder()
          }
          i++
        }
        else {
          curNum.append(s[i])
          i++
        }
      }

      var sum = if (curNums[0] == "-") {
        curNums.removeFirst()
        -curNums.removeFirst().toInt()
      }
      else {
        curNums.removeFirst().toInt()
      }

      while (curNums.isNotEmpty()) {
        val operand = curNums.removeFirst()
        val second = curNums.removeFirst()
        if (operand == "-") {
          sum = sum - second.toInt()
        }
        else if (operand == "+") {
          sum = sum + second.toInt()
        }
        else {
          throw RuntimeException()
        }
      }

      return sum.toString()
    }
    return recurse().toInt()
  }
}
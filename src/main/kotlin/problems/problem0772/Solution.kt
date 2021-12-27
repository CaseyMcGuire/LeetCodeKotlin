package problems.problem0772

class Solution {
  fun calculate(s: String): Int {
    var i = 0
    fun evaluateExpression(): Int {
      val elems = mutableListOf<String>()
      while (i < s.length) {
        var curChar = s[i]
        if (curChar == ')') {
          i++
          break
        }
        else if (curChar == '(') {
          i++
          val evaluatedSubexpression = evaluateExpression()
          elems.add(evaluatedSubexpression.toString())
          continue
        }
        var isDigit = Character.isDigit(curChar)
        if (!isDigit) {
          elems.add(curChar.toString())
        }
        else {
          val prev  = if (elems.isEmpty()) null else elems[elems.size - 1].toIntOrNull()
          if (prev != null) {
            val newNum = "$prev$curChar"
            elems.removeAt(elems.size - 1)
            elems.add(newNum)
          }
          else {
            elems.add(curChar.toString())
          }
        }
        i++
      }

      return evaluateSubexpression(elems)
    }

    return evaluateExpression()
  }

  private fun evaluateSubexpression(expression: MutableList<String>): Int {
    val firstEval = mutableListOf<String>()
    // first evaluate multiplication and division
    var j = 1
    firstEval.add(expression[0])
    while (j < expression.size) {
      val operator = expression[j]
      if (operator == "+" || operator == "-") {
        firstEval.add(operator)
        firstEval.add(expression[j + 1])
      }
      else {
        val prev = firstEval[firstEval.size - 1]
        val evaluated = evaluate(prev, expression[j + 1], operator)
        firstEval.removeAt(firstEval.size - 1)
        firstEval.add(evaluated.toString())
      }
      j += 2
    }

    var evaluated = firstEval[0].toInt()
    j = 1
    while (j < firstEval.size) {
      val operator = firstEval[j]
      evaluated =  evaluate(evaluated.toString(), firstEval[j + 1], operator)
      j += 2
    }
    return evaluated
  }

  private fun evaluate(first: String, second: String, operator: String): Int {
    val firstInt = first.toInt()
    val secondInt = second.toInt()
    return when(operator) {
      "+" -> firstInt + secondInt
      "-" -> firstInt - secondInt
      "*" -> firstInt * secondInt
      "/" -> firstInt / secondInt
      else -> throw IllegalArgumentException("Unknown operator: $operator")
    }
  }
}
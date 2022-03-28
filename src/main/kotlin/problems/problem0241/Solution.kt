package problems.problem0241

class Solution {
  fun diffWaysToCompute(expression: String): List<Int> {
    val tokens = tokenize(expression)
    return compute(tokens, 0, tokens.size - 1)
  }

  private fun compute(expression: List<String>, start: Int, end: Int): List<Int> {
    val length = end - start + 1
    if (length == 1) {
      return listOf(expression[start].toInt())
    }
    if (length == 3) {
      return listOf(evaluate(expression[start].toInt(), expression[start + 1], expression[start + 2].toInt()))
    }
    val values = mutableListOf<Int>()
    for (i in start..end - 2 step 2) {
      val operand = expression[i + 1]
      val leftValues = compute(expression, start, i)
      val rightValues = compute(expression, i + 2, end)
      for (rightValue in rightValues) {
        for (leftValue in leftValues) {
          val evaluation = evaluate(leftValue, expression[i + 1], rightValue)
          values.add(evaluation)
        }
      }
    }
    return values
  }

  private fun evaluate(first: Int, operand: String, second: Int): Int {
    return when(operand) {
      "+" -> first + second
      "-" -> first - second
      "*" -> first * second
      else -> throw IllegalArgumentException("unknown operand: $operand")
    }
  }


  private fun tokenize(expression: String): List<String> {
    var i = 0
    var builder = StringBuilder()
    val tokens = mutableListOf<String>()
    while (i < expression.length) {
      if (expression[i] == '-' || expression[i] == '+' || expression[i] == '*') {
        tokens.add(builder.toString())
        tokens.add(expression[i].toString())
        builder = StringBuilder()
      }
      else {
        builder.append(expression[i])
      }
      i++
    }
    tokens.add(builder.toString())
    return tokens
  }

}
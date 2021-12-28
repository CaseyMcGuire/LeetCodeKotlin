package problems.problem0736

import java.util.*

class Solution {
  fun evaluate(expression: String): Int {
    if (expression.length == 1) {
      return expression.toInt()
    }
    var i = 0
    val variables = mutableMapOf<String, ArrayDeque<Int>>()
    val expr = expression
    fun recurse(): Int {
      if (expr[i] == '(' || expr[i] == ')' || expr[i] == ' ') {
        i++
        return recurse()
      }

      val (type, offset) = getTypeAndOffset(i, expr)
      i += offset
      return when (type) {
        Operator.EXPR -> {
          val sub = expr.substring(i - offset, i).trim()
          sub.toIntOrNull() ?: variables.getLastNum(sub)
        }
        Operator.LET -> {
          // process variables and then expression
          val variablesAssigned = ArrayDeque<String>()
          while(!isLastExpressionInLet(expr, i)) {
            val variableBuilder= StringBuilder()
            var j = i
            while (j < expr.length && expr[j] != ' ') {
              variableBuilder.append(expr[j])
              j++
            }
            i = j + 1
            if (i < expr.length && expr[i] == '(') {
              i++
            }
            val value = recurse()
            val variableName = variableBuilder.toString()
            variablesAssigned.push(variableName)
            variables.addNum(variableName, value)
            while (i < expr.length && (expr[i] == ')' || expr[i] == ' ')) {
              i++
            }
          }
          val evaluated = recurse()
          variablesAssigned.forEach { variables.removeLastNum(it) }
          evaluated
        }
        Operator.ADD -> {
          val first = recurse()
          val second = recurse()
          first + second
        }
        Operator.MULT -> {
          val first = recurse()
          val second = recurse()
          first * second
        }
      }
    }
    return recurse()
  }

  private fun isLastExpressionInLet(s: String, i: Int): Boolean {
    // an expression either starts with a left brace is the last element in a
    if (s[i] == '(') {
      return true
    }
    var j = i
    while (j < s.length && s[j] != ')') {
      if (s[j] == ' ') {
        return false
      }
      j++
    }
    return true
  }

  private fun MutableMap<String, ArrayDeque<Int>>.addNum(s: String, num: Int) {
    val deque = this[s] ?: ArrayDeque<Int>()
    deque.push(num)
    this[s] = deque
  }

  private fun MutableMap<String, ArrayDeque<Int>>.getLastNum(s: String): Int {
    val deque = this[s] ?: throw RuntimeException()
    return deque.peek()
  }

  private fun MutableMap<String, ArrayDeque<Int>>.removeLastNum(s: String) {
    val deque = this[s] ?: throw RuntimeException()
    deque.pop()
  }

  private fun getTypeAndOffset(i: Int, expression: String): Pair<Operator, Int> {
    val builder = StringBuilder()
    var index = i
    while (index < expression.length && expression[index] != ' ' && expression[index] != ')') {
      builder.append(expression[index])
      index++
    }
    return when (builder.toString()) {
      "let" -> Pair(Operator.LET, 4)
      "mult" -> Pair(Operator.MULT, 5)
      "add" -> Pair(Operator.ADD, 4)
      else -> {
        return Pair(Operator.EXPR, builder.length)
      }
    }
  }
}

enum class Operator {
  EXPR,
  LET,
  MULT,
  ADD
}

fun main(args: Array<String>) {
  println(Solution().evaluate("(let x 1 y 2 x (add x y) (add x y))"))
  println(Solution().evaluate("(let x 2 (add (let x 3 (let x 4 x)) x))"))
  println(Solution().evaluate("(let y 2 x (let q 2 z y 2) x)"))
}
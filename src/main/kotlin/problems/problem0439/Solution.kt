package problems.problem0439

class Solution {
  fun parseTernary(expression: String): String {
    var i = 0
    fun recurse(): Char {
      val isConditional = (expression[i] == 'T' || expression[i] == 'F') && i < expression.length - 1 && expression[i + 1] != ':'
      if (isConditional) {
        val isTrue = expression[i] == 'T'
        i += 2
        val leftValue = recurse()
        val rightValue = recurse()
        if (isTrue) {
          return leftValue
        }
        else {
          return rightValue
        }
      }
      else {
        val c = expression[i]
        // go past number and ':'
        i += 2
        return c
      }
    }
    return recurse().toString()
  }
}